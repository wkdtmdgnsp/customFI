package project.customFI.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.customFI.member.domain.Member;
import project.customFI.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("중복회원 예외 테스트")
    void 중복_회원_예외() {
        // given
        Member member1 = new Member("qwaw12", "1234");
        Member member2 = new Member("qwaw12", "1234");
        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> {
            memberService.join(member2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("회원 조회테스트")
    void 회원조회() {
        // given
        Member member = new Member("qwaw12", "1234");
        memberService.join(member);
        // when
        Member findMember = memberService.findByUserId(member.getUserId());
        // then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("회원수정 테스트")
    void 회원수정() {
        // given
        Member member = new Member("qwaw12", "1234");
        memberService.join(member);
        // when
        memberService.update(member.getUserId(), "123456");
        // then
        Member findMember = memberService.findByUserId("qwaw12");
        assertThat(findMember.getPassword()).isEqualTo("123456");
    }
}