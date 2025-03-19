package hyeseo.boot.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 루트 경로 /에 매핑
    @GetMapping("/")
    public String index() {
        return "index"; // src/main/resources/static/index.html 파일을 반환
    }
}
