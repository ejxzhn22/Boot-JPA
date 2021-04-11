package sujin.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sujin.springboot.domain.Member;
import sujin.springboot.domain.board.Board;
import sujin.springboot.repository.BoardRepository;
import sujin.springboot.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //글 등록
    @Transactional
    public void saveBoard(Board board, Long member_no) {

        Member member = memberRepository.findByNo(member_no);
        board.setMember(member);
        boardRepository.save(board);
    }

    //qna list 가져오기
    public List findQna(){
        return boardRepository.findQna();
    }

    //notice list 가져오기
    public List findNotice(){
        return boardRepository.findNotice();
    }

    //글 하나가져오기
    public Board findOne(Long board_no){
        return boardRepository.findOne(board_no);
    }

    //글 수정
    @Transactional
    public void updateBoard(Long board_no, String board_title, String board_content) {

        Board board = boardRepository.findOne(board_no);
        board.setBoard_title(board_title);
        board.setBoard_content(board_content);
    }

    //글 삭제
    @Transactional
    public void deleteBoard(Long board_no){
        boardRepository.deleteBoard(board_no);
    }
}
