package sujin.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sujin.springboot.domain.Address;
import sujin.springboot.domain.Member;
import sujin.springboot.dto.MemberForm;
import sujin.springboot.dto.Message;
import sujin.springboot.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 이동
    @GetMapping("/members/login")
    public String loginPage(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "login";
    }

    //로그인
    @PostMapping("/members/login")
    public ModelAndView login(@Valid MemberForm form, BindingResult result, HttpSession session, ModelAndView mav){

        Boolean pwdCheck = memberService.login(form);

        if(pwdCheck){
            Member member = memberService.findOne(form.getMember_id());
            session.setAttribute("member", member);
            mav.addObject("data", new Message("로그인 완료", "/"));
            mav.setViewName("message");
            return mav;
        }else{

            mav.addObject("data", new Message("없는 아이디거나 비밀번호가 다름", "/members/login"));
            mav.setViewName("message");
            return mav;
        }
    }

    //로그아웃
    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    //회원가입 이동
    @GetMapping("/members/new")
    public String newMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "newMemberForm";
    }

    //회원가입
    @PostMapping("/members/new")
    public String join(@Valid MemberForm form, BindingResult result){

        if(result.hasErrors()){
            return "newMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setMember_id(form.getMember_id());
        member.setMember_password(form.getMember_password());
        member.setMember_name(form.getMember_name());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }


}
