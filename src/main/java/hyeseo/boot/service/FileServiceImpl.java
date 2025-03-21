package hyeseo.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hyeseo.boot.domain.FileUp;
import hyeseo.boot.repository.FileRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @org.springframework.beans.factory.annotation.Value("${file.dir}")
    private String fileDir;
    private final FileRepository fileRepository;

    //(1) 파일 업로드
    @Override
    public long saveFile(MultipartFile mf) throws IOException {
        File dirStore = new File(fileDir);
        if (!dirStore.exists()) dirStore.mkdirs();

        if(mf.isEmpty()) {
            return -1;
        }
        String origName = mf.getOriginalFilename(); //원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString(); //파일 이름으로 쓸 uuid생성
        String extension = origName.substring(origName.lastIndexOf(".")); //확장자 추출
        String savedName = uuid + extension; //uuid와 확장자 결합
        String savedPath = fileDir + savedName; //저장될 파일의 물리적 경로

        FileUp fileUp = FileUp.builder()
                .orgnm(origName)
                .savednm(savedName)
                .savedpath(savedPath)
                .build();
        mf.transferTo(new File(savedPath)); //uuid를 파일명으로 서버측에 저장
        FileUp savedFile = fileRepository.save(fileUp); //DB에 insert

        return savedFile.getId();
    }

    //(2) 파일 리스팅
    @Override
    public List<FileUp> getFileUpAll() {
        List<FileUp> fileUps =  fileRepository.findAll();
        return fileUps;
    }

    //(3) 파일 다운로드
    @Override
    public FileUp getFileUp(long file_id) {
        FileUp fileUp = fileRepository.findById(file_id).orElse(null);
        return fileUp;
    }
}