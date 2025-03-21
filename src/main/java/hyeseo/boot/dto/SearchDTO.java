package hyeseo.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private String keyword;
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "id";
    private String sortDir = "asc";
}