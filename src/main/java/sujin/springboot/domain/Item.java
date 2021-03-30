package sujin.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_no;

    private String item_name;
    private int item_price;
    private int item_quantity;



}
