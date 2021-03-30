package sujin.springboot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sujin.springboot.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //회원가입
    public void save(Member member){
        em.persist(member);
    }

    //id로 조회 -> 결과 리스트
    public List<Member> findById(String member_id) {
        return em.createQuery("select m from Member m where m.member_id=:member_id", Member.class)
                .setParameter("member_id", member_id)
                .getResultList();
    }

    public Member findOne(String member_id){
        return em.createQuery("select m from Member m where m.member_id=:member_id", Member.class)
                .setParameter("member_id", member_id)
                .getSingleResult();
    }


    //no로 조회
    public Member findByNo(Long member_no) {
        return em.find(Member.class,member_no);
    }

    //전체 멤버 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
