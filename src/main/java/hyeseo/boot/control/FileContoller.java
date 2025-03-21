package hyeseo.boot.control;

import hyeseo.boot.domain.FileUp;
import hyeseo.boot.service.FileService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("file")
@RequiredArgsConstructor
@Controller
public class FileContoller {
    private final FileService fileService;

    @GetMapping("upload.do")
    public String upload() {
        return "file/form";
    }

    @PostMapping("upload.do")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("files") List<MultipartFile> files) throws IOException {
        long fileSize = file.getSize();
        fileService.saveFile(file);

        for (MultipartFile multipartFile : files) {
            fileService.saveFile(multipartFile);
        }
        return "redirect:upload.do";
    }

    @GetMapping("list.do")
    public String list(Model model) {
        List<FileUp> fileUps = fileService.getFileUpAll();
        model.addAttribute("fileUps", fileUps);
        return "file/list";

    }

    @GetMapping("images/{file_id}")
    @ResponseBody
    public UrlResource getImageResource(@PathVariable("file_id") long file_id, Model model)
            throws IOException {
        FileUp fileUp = fileService.getFileUp(file_id);
        return new UrlResource("file:"+fileUp.getSavedpath());
    }

    @GetMapping("attach/{file_id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("file_id") long file_id)
            throws IOException {
        FileUp fileUp = fileService.getFileUp(file_id);
        UrlResource resource = new UrlResource("file:"+fileUp.getSavedpath());
        String encodedFileName = UriUtils.encode(fileUp.getOrgnm(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body((Resource) resource);
    }

    
}
