package sujin.springboot.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Q")
@Setter @Getter
public class Qna extends Board{

    private String board_writer;

}
