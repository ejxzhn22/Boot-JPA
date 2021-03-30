package sujin.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    private Long item_no;
    private String item_name;
    private int item_price;
    private int item_quantity;
}
