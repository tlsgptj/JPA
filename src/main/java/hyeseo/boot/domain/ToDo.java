package hyeseo.boot.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data //getter와 setter를 자동으로 생성해줌
public class ToDo {
    private String subject;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private java.util.Date Cdate;
}
