package sujin.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sujin.springboot.dto.MemberForm;
import sujin.springboot.domain.Member;
import sujin.springboot.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(Member member){
        //중복확인
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getMember_no();
    }

    //중복확인 메서드
    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findById(member.getMember_id());

        if(!findMember.isEmpty()){
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //로그인
    public Boolean login(MemberForm form) {
        List<Member> findMembers = memberRepository.findById(form.getMember_id());

        if(findMembers == null){
            return false;
        }else if (findMembers.get(0).getMember_password().equals(form.getMember_password())){
            return true;
        }else {
            return false;
        }
    }

    //아이디로 조회
    public Member findOne(String member_id) {
        return memberRepository.findOne(member_id);
    }
    //회원전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 no로 조회
    public Member findOneByNo(Long member_no){
        return memberRepository.findByNo(member_no);
    }
}
