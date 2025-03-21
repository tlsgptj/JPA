package hyeseo.boot.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private List<BookDTO> books;
}