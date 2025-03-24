package hyeseo.boot.control;

import hyeseo.boot.domain.Board;
import hyeseo.boot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardRestfulController {
    @Autowired
    private BoardService boardService;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    // 특정 게시글 조회
    @GetMapping("/{seq}")
    public ResponseEntity<Board> getBoardBySeq(@PathVariable("seq") Long seq) {
        Board board = boardService.getBoardBySeq(seq);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        boardService.insertBoard(board);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    // 게시글 수정
    @PutMapping("/{seq}")
    public ResponseEntity<Board> updateBoard(@PathVariable("seq") Long seq, @RequestBody Board board) {
        Board existingBoard = boardService.getBoardBySeq(seq);
        if (existingBoard != null) {
            board.setSeq(seq);
            boardService.updateBoard(board);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("seq") Long seq) {
        Board existingBoard = boardService.getBoardBySeq(seq);
        if (existingBoard != null) {
            boardService.deleteBoard(seq);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}