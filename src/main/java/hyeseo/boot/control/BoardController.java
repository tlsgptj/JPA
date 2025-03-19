package hyeseo.boot.control;

import hyeseo.boot.domain.Board;
import hyeseo.boot.mapper.BoardMapper;
import hyeseo.boot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "board/list";
    }

    @GetMapping("/detail/{seq}")
    public String detailBoard(@PathVariable("seq") Long seq, Model model) {
        model.addAttribute("board", boardService.getBoardBySeq(seq));
        return "board/detail";
    }

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new Board());
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/edit/{seq}")
    public String updateForm(@PathVariable("seq") Long seq, Model model) {
        model.addAttribute("board", boardService.getBoardBySeq(seq));
        return "board/update";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Board board) {
        boardService.updateBoard(board);
        return "redirect:/board/detail/" + board.getSeq();
    }

    @GetMapping("/delete/{seq}")
    public String delete(@PathVariable("seq") Long seq) {
        boardService.deleteBoard(seq);
        return "redirect:/board/list";
    }

}
