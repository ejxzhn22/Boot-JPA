package sujin.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_item_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_no")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_no")
    private Member member;

    private int cart_price;
    private int cart_count;


    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getCartItems().add(this);
    }

    //== 생성 메서드 ==//
    public static CartItem createCartItem(Member member,Item item, int cart_price, int cart_count){
        CartItem cartItem = new CartItem();
        cartItem.setMember(member);
        cartItem.setItem(item);
        cartItem.setCart_price(cart_price);
        cartItem.setCart_count(cart_count);

        return cartItem;
    }
}
