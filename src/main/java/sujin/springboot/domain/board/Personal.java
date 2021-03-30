package sujin.springboot.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
@Getter @Setter
public class Personal extends Board{

    private String board_writer;
    private int board_answer;
}
