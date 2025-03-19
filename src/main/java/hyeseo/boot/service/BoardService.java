package hyeseo.boot.service;

import hyeseo.boot.domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();
    Board getBoardBySeq(Long seq);
    void insertBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Long seq);
}
