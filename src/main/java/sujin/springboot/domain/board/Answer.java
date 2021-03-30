package sujin.springboot.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_no;

    private String answer_content;
    private LocalDateTime answer_date;

}
