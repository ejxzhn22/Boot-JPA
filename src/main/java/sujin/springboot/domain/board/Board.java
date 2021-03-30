package sujin.springboot.domain.board;

import lombok.Getter;
import lombok.Setter;
import sujin.springboot.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="dtype")
@Getter
public abstract class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_no;

    private String board_title;
    private String board_content;
    private LocalDateTime board_date;
    private int board_del;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="answer_id")
    private Answer answer;


    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }


}
