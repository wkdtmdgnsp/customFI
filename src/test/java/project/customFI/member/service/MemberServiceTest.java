package project.customFI.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.customFI.member.domain.Member;
import project.customFI.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void 회원가입() {
        // given
        Member member = new Member("qwaw12", "1234");

        // when
        Long saveId = memberService.join(member);

        // then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }
}