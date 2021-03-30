package sujin.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "아이디를 입력하세요")
    private String member_id;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String member_password;
    private String member_name;

    private String city;
    private String street;
    private String zipcode;

}
