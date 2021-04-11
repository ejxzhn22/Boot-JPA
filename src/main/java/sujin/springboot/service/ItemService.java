package sujin.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sujin.springboot.domain.CartItem;
import sujin.springboot.domain.Item;
import sujin.springboot.domain.Member;
import sujin.springboot.repository.ItemRepository;
import sujin.springboot.repository.MemberRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //상품 등록
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //상품 수정
    @Transactional
    public void updateItem(Long item_no, String item_name, int item_price, int item_quantity){
        Item findItem = itemRepository.findOne(item_no);

        findItem.setItem_name(item_name);
        findItem.setItem_price(item_price);
        findItem.setItem_quantity(item_quantity);

    }

    //상품 삭제
    @Transactional
    public void deleteItem(Long item_no){
        log.info("delete service");
        itemRepository.deleteItem(item_no);
    }

    //상품 전체 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    //상품 단일 조회
    public Item findOne(Long item_no) {
        return itemRepository.findOne(item_no);
    }

    //장바구니 담기
    @Transactional
    public Long cart(Long member_no, Long item_no, int count) {

        //엔티티조회
        Member member = memberRepository.findByNo(member_no);
        Item item = itemRepository.findOne(item_no);

        Boolean cart = itemRepository.duplicateCart(member, item);
        log.info("cart? = "+String.valueOf(cart));
        if(cart) {
            //장바구니 상품 생성
            CartItem cartItem = CartItem.createCartItem(member, item, item.getItem_price(), count);

            //장바구니 저장
            itemRepository.cartSave(cartItem);
            return cartItem.getCart_item_no();
        }else{
            CartItem cartItem = (CartItem) itemRepository.fineOne(member, item);
            cartItem.setCart_count(cartItem.getCart_count()+ count);

            return cartItem.getCart_item_no();
        }

    }

    //장바구니 가져오기
    public List findCart(Member member) {
        return itemRepository.cartList(member);
    }

    //장바구니 가져오기
    public List findCartTest(Member member) {
        return itemRepository.cartListTest(member);
    }
}
