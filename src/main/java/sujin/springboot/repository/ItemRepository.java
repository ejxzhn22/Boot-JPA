package sujin.springboot.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import sujin.springboot.domain.CartItem;
import sujin.springboot.domain.Item;
import sujin.springboot.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 등록

    public void save(Item item ) {
        em.persist(item);
    }

    //상품 조회
    public Item findOne(Long item_no){
        return em.find(Item.class,item_no);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    //상품 삭제
    public void deleteItem(Long item_no) {
        log.info("repository delete");
        Item item = em.find(Item.class, item_no);
        em.remove(item);

    }

    //장바구니 저장
    public void cartSave(CartItem cartItem) {
        em.persist(cartItem);
    }

    //장바구니 리스트
    public List cartList(Member member) {
        List resultList = em.createQuery("select c from CartItem c join fetch c.member join fetch c.item where c.member=:member")
                            .setParameter("member", member)
                            .getResultList();
        return resultList;
    }



}
