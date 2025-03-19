package hyeseo.boot.control;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import hyeseo.boot.domain.Human;
import hyeseo.boot.domain.HumanList;
import hyeseo.boot.domain.ToDo;

import java.util.ArrayList;
import java.util.Calendar;

@RequestMapping("test")
@Controller
public class TestController {

    @RequestMapping("")
    public void m00(){
        pln("#m00(): default URL");
    }

    @RequestMapping("base1") //test/base1
    public void m01(){
        pln("#m01(): Get, Post, Put, Delete, Head, Patch 지원");
    }
    @RequestMapping(value = "base2", method = RequestMethod.GET)
    public void m02(){
        pln("#m02(): Get방식만 지원");
    }
    @RequestMapping(value = "base3", method = {RequestMethod.GET, RequestMethod.POST})
    public void m03(){
        pln("#m03(): Get과 Post방식만 지원");
    }
    @GetMapping("param1")
    public void m04(Human human){ //GET방식을 지원하는 것은 HEAD방식도 함께 지원함
        pln("#m04(): human: " + human);
    }
    @GetMapping("param2")
    public void m05(@RequestParam("na") String name, @RequestParam("a") int age){
        pln("#m05(): name: " + name + ", age: " + age);
        Human man = new Human(name, age);
        pln("#m05() man: " + man);
    }
    @GetMapping("param3")
    public void m06(@RequestParam("names") ArrayList<String> names){
        pln("#m06() names: " + names);
    }
    @GetMapping("param4") //test/param4
    public void m07(@RequestParam("ns") ArrayList<String> names){
        pln("#m07() names: " + names);
    }
    @GetMapping("param5") //test/param7
    public void m08(@RequestParam("names") String[] names){
        pln("#m08() names: " + names);
        for(String name: names){ pln("name:  " + name); }
    }
    @GetMapping("param6") //test/param6
    public void m09(HumanList list){
        pln("#m09() list: " + list);
    }
    @GetMapping("/param7") //전역
    public void m10(Human human, @RequestParam("page") int page){
        pln("#m10(): human: " + human + ", page: " + page);
    }
    @GetMapping("/param8") //전역
    public void m11(ToDo dto){
        pln("#m11(): dto: "+ dto);

        java.util.Date d = dto.getCdate();
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int dm = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR);
        int mi = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);

        pln(y + "년 " + m + "월 " + dm + "일 "+ h + ":"+mi+":" + s);
    }

    @GetMapping("json1") //JSON 문자열 직접 생성(JSON 응답을 직접 조작)
    //헤더 설정이 필요한 경우(캐싱, 인증 관련 헤더 추가)
    //개발자가 직접 json 형식을 유지해야함
    // HttpHeaders를 직접 설정해야함
    public ResponseEntity<String> m12(){
        String msg = "{\"name\":\"홍길동\", \"age\":20}"; //JSON
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
    }

    @GetMapping("json2") //ResponseBody를 쓰면 json
    //객체를 반환하면 Spring이 JSON으로 자동 변환
    //대부분의 일반적인 REST API에서는 이 방식을 권장
    //Spring이 JackSon을 사용하여 변환
    //Spring이 자동으로 설정 (ResponseBody)
    public @ResponseBody Human m13(){
        Human man = new Human("진달래" ,18);
        return man;
    }

    private void pln(String str){
        System.out.println(str);
    }
}