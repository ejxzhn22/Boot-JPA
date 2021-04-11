package sujin.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sujin.springboot.domain.Member;
import sujin.springboot.domain.board.Board;
import sujin.springboot.domain.board.Notice;
import sujin.springboot.domain.board.Qna;
import sujin.springboot.dto.BoardForm;
import sujin.springboot.service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //공지사항 이동
    @GetMapping("/board/notice")
    public String notice(Model model){

        List notice = boardService.findNotice();
        model.addAttribute("list",notice);
        return "notice";
    }

    //qna
    @GetMapping("/board/qna")
    public String qna(Model model){
        List qna = boardService.findQna();

        model.addAttribute("list", qna);
        return "qna";
    }

    //1:1
    @GetMapping("/board/personal")
    public String personal() {

        return "personal";
    }

    //공지사항 작성
    @GetMapping("/board/newNotice")
    public String newNotice(Model model, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        BoardForm form = new BoardForm();
        form.setBoard_writer(member.getMember_id());

        model.addAttribute("boardForm", form);
        return "newNoticeForm";
    }
    //qna 작성
    @GetMapping("/board/newQna")
    public String newQna(Model model, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        BoardForm form = new BoardForm();
        form.setBoard_writer(member.getMember_id());

        model.addAttribute("boardForm", form);
        return "newQnaForm";
    }
    //1:1 작성
    @GetMapping("/board/newPersonal")
    public String newPersonal(Model model, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        BoardForm form = new BoardForm();
        form.setBoard_writer(member.getMember_id());

        model.addAttribute("boardForm", form);
        return "newPersonalForm";
    }

    //공지사항
    @PostMapping("/board/newNotice")
    public String createNotice(BoardForm form) {

        Notice notice = new Notice();
        notice.setBoard_title(form.getBoard_title());
        notice.setBoard_content(form.getBoard_content());

        boardService.saveBoard(notice, 0L);

        return "redirect:/";
    }

    //qna
    @PostMapping("/board/newQna")
    public String createQna(BoardForm form, HttpSession session) {

        Qna qna = new Qna();
        qna.setBoard_title(form.getBoard_title());
        qna.setBoard_content(form.getBoard_content());
        qna.setBoard_writer(form.getBoard_writer());

        Member member = (Member)session.getAttribute("member");
        boardService.saveBoard(qna, member.getMember_no());
        return "redirect:/board/qna";
    }

    //1:1
    @PostMapping("/board/newPersonal")
    public String createPersonal(BoardForm form) {


        return "redirect:/";
    }

    //글 상세 페이지
    @GetMapping("/board/{board_no}")
    public String detailBoard(@PathVariable Long board_no, Model model) {
        Board board = boardService.findOne(board_no);
        model.addAttribute("board", board);
        Member member = board.getMember();
        log.info("member_id: "+member.getMember_id());
        model.addAttribute("member", member);

        return "detailBoard";
    }

    // 글 수정
    @GetMapping("/board/{board_no}/edit")
    public String editBoard(@PathVariable Long board_no, Model model) {

        Board board = boardService.findOne(board_no);
        Member member = board.getMember();

        model.addAttribute("boardForm", board);
        model.addAttribute("member", member);

        return "editBoardForm";
    }

    //글수정
    @PostMapping("/board/{board_no}/edit")
    public String edit(@PathVariable Long board_no, String board_title, String board_content){

        boardService.updateBoard(board_no,board_title,board_content);
        return "redirect:/";
    }

    //글삭제
    @GetMapping("/board/{board_no}/delete")
    public String delete(@PathVariable Long board_no){

        boardService.deleteBoard(board_no);
        return "redirect:/";
    }
}
