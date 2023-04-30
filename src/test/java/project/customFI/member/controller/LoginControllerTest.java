package project.customFI.member.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Session;
import project.customFI.member.domain.Member;
import project.customFI.member.repository.MemberRepository;
import project.customFI.member.service.MemberService;

import javax.servlet.http.HttpSession;

@SpringBootTest
public class LoginControllerTest {

    @Autowired
    LoginController loginController;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 성공 테스트")
    void 로그인_성공 (HttpSession session) {
        // given
        Member member = new Member("qwaw12", "1234");
        memberService.join(member);
        // when
        loginController.login("qwaw12", "1234");
        Object loginMember = session.getAttribute("loginMember");
        // then
        Assertions.assertThat(loginMember).isEqualTo(member);
    }
}
