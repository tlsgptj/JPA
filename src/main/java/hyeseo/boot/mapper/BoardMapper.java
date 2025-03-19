package hyeseo.boot.mapper;

import hyeseo.boot.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    List<Board> getAllBoards();
    Board getBoardBySeq(Long seq);
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Long seq);

}
