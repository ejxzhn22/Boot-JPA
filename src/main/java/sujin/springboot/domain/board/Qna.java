package sujin.springboot.domain.board;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Q")
public class Qna extends Board{

    private String board_writer;
    private int board_answer;
}
