package hyeseo.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;

    public static <T, U> PageResponseDTO<U> of(Page<T> page, List<U> content) {
        PageResponseDTO<U> pageResponseDTO = new PageResponseDTO<>();
        pageResponseDTO.setContent(content);
        pageResponseDTO.setPageNo(page.getNumber());
        pageResponseDTO.setPageSize(page.getSize());
        pageResponseDTO.setTotalElements(page.getTotalElements());
        pageResponseDTO.setTotalPages(page.getTotalPages());
        pageResponseDTO.setLast(page.isLast());
        pageResponseDTO.setFirst(page.isFirst());
        return pageResponseDTO;
    }
}