package sujin.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {

    private String board_title;
    private String board_content;
    private String board_writer;
}
