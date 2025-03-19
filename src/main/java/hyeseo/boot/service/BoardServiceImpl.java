package hyeseo.boot.service;

import hyeseo.boot.domain.Board;
import hyeseo.boot.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> getAllBoards() {
        return boardMapper.getAllBoards();
    }

    @Override
    public Board getBoardBySeq(Long seq) {
        return boardMapper.getBoardBySeq(seq);
    }

    @Override
    public void insertBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(Long seq) {
        boardMapper.deleteBoard(seq);
    }
}
