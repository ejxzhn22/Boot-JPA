package sujin.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import sujin.springboot.domain.board.Board;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_no;

    @Column(nullable = false)
    private String member_id;
    private String member_password;
    private String member_name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItems;



}
