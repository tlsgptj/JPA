package hyeseo.boot.service;

import org.springframework.web.multipart.MultipartFile;
import hyeseo.boot.domain.FileUp;
import java.io.IOException;
import java.util.List;

public interface FileService {
    //(1) 파일 업로드
    long saveFile(MultipartFile mf) throws IOException;

    //(2) 파일 리스팅
    List<FileUp> getFileUpAll();

    //(3) 파일 다운로드
    FileUp getFileUp(long file_id);
}