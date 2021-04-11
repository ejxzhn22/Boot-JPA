package sujin.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sujin.springboot.domain.CartItem;
import sujin.springboot.domain.Item;
import sujin.springboot.domain.Member;
import sujin.springboot.dto.ItemForm;
import sujin.springboot.dto.Message;
import sujin.springboot.service.ItemService;
import sujin.springboot.service.MemberService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;

    //상품등록
    @PostMapping("/items/newItem")
    public String create(ItemForm form){

        Item item = new Item();
        item.setItem_name(form.getItem_name());
        item.setItem_price(form.getItem_price());
        item.setItem_quantity(form.getItem_quantity());

        itemService.saveItem(item);
        return "redirect:/admin/item";
    }

    //상품수정 페이지
    @GetMapping("/items/{item_no}/edit")
    public String updateItemForm(@PathVariable("item_no") Long item_no, Model model){
        Item item = itemService.findOne(item_no);

        ItemForm form = new ItemForm();
        form.setItem_name(item.getItem_name());
        form.setItem_price(item.getItem_price());
        form.setItem_quantity(item.getItem_quantity());

        model.addAttribute("form", form);

        return"/admin/updateItemForm";

    }

    //상품수정
    @PostMapping("/items/{item_no}/edit")
    public String updateItem(@PathVariable Long item_no, ItemForm form){

        log.info("포스트 수정 item_no={}", item_no);
        itemService.updateItem(item_no, form.getItem_name(), form.getItem_price(), form.getItem_quantity());

        return "redirect:/admin/item";
    }

    // 상품 삭제
    @GetMapping("/items/{item_no}/delete")
    public String deleteItem(@PathVariable Long item_no){
        log.info("삭제");
        itemService.deleteItem(item_no);

        return"redirect:/admin/item";
    }

    //SHOP 이동
    @GetMapping("/items")
    public String item(Model model) {
        log.info("shop");
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "shop";
    }

    //상품상세 페이지
    @GetMapping("/items/{item_no}/detail")
    public String detailItem(@PathVariable Long item_no, Model model){
        Item item = itemService.findOne(item_no);

        ItemForm form = new ItemForm();
        form.setItem_no(item_no);
        form.setItem_name(item.getItem_name());
        form.setItem_price(item.getItem_price());
        form.setItem_quantity(item.getItem_quantity());

        model.addAttribute("form", form);

        return "detailItemForm";
    }

    //장바구니 담기
    @PostMapping("/items/{item_no}/detail")
    public String cart(@PathVariable Long item_no, int item_quantity, HttpSession session){

        Member member = (Member) session.getAttribute("member");

        itemService.cart(member.getMember_no(),item_no,item_quantity);

        return "redirect:/items/carts";
    }

    //장바구니 이동
    @GetMapping("/items/carts")
    public String cartList(HttpSession session, Model model){
        log.info("장바구니 이동 ");
        Member member = (Member)session.getAttribute("member");

        log.info("member 조회");
        Member findMember = memberService.findOneByNo(member.getMember_no());
        log.info("장바구니 리스트 조회");
        List cartItems = itemService.findCart(findMember);
        log.info("장바구니 리스트 테스트");
        List test = itemService.findCartTest(findMember);



        model.addAttribute("items",cartItems);

        return "cart";
    }


}
