package hyeseo.boot.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MethodController {
    @GetMapping("templete.do")
    public String m1(@RequestParam(name="na", required = false, defaultValue = "벚꽃") String name, Model model) {
        System.out.println("m1()" + name);
        model.addAttribute("name", name);
        return "template";
    }

    @ResponseBody
    @GetMapping("string.do")
    public String m2(@RequestParam(name="na") String name) {
        System.out.println("@m2() name" + name);
        return name;
    }
    @ResponseBody
    @GetMapping("json.do")
    public Book m3(@RequestParam("title") String title, @RequestParam("price") int price) {
        System.out.println("@m3 title: " + title + ", price: " + price);
        return new Book(title, price);
    }

    class Book{
        private String title;
        private int price;
        Book(String title, int price) {
            this.title = title;
            this.price = price;
        }
        public String getTitle() {
            return title;
        }

        public int getPrice() {
            return price;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
