package sujin.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sujin.springboot.domain.board.Board;
import sujin.springboot.domain.board.Qna;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    //글 등록
    public void save(Board board){

        em.persist(board);
    }

    //qna 리스트 가져오기
    public List<Board> findQna() {
        List list = em.createQuery("select q from Qna q ")
                .getResultList();

        return list;
    }

    //notice 리스트 가져오기
    public List<Board> findNotice() {
        List list = em.createQuery("select n from Notice n ")
                .getResultList();

        return list;
    }

    //글 하나가져오기
    public Board findOne(Long board_no) {
        return em.find(Qna.class, board_no);
    }

    //글 삭제
    public void deleteBoard(Long board_no){
        Board board = em.find(Board.class, board_no);
        em.remove(board);
    }
}
