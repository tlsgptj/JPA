package hyeseo.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long seq;
    private String writer;
    private String email;
    private String subject;
    private String content;
    private LocalDateTime rdate;
    private LocalDateTime udate;
}
