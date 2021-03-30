package sujin.springboot.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sujin.springboot.domain.Item;
import sujin.springboot.domain.Member;
import sujin.springboot.dto.ItemForm;
import sujin.springboot.dto.MemberForm;
import sujin.springboot.service.ItemService;
import sujin.springboot.service.MemberService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ItemService itemService;
    private final MemberService memberService;

    //회원관리이동
    @GetMapping("/admin/member")
    public String member(Model model) {
        log.info("admin member");

        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/admin/adminMember";
    }

    //상품관리 이동
    @GetMapping("/admin/item")
    public String item(Model model) {
        log.info("admin item");
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "/admin/adminItem";
    }

    @GetMapping("admin/newItem")
    public String newItemForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        log.info("admin newItemForm");
        return "admin/newItemForm";
    }

    @GetMapping("/admin/order")
    public String order() {
        log.info("admin order");
        return "/admin/adminOrder";
    }

    @GetMapping("/admin/notice")
    public String notice() {
        log.info("admin notice");
        return "/admin/adminNotice";
    }

    @GetMapping("/admin/qna")
    public String qna() {
        log.info("admin qna");
        return "/admin/adminQna";
    }

    @GetMapping("/admin/personal")
    public String personal() {
        log.info("admin personal");
        return "/admin/adminPersonal";
    }
}
