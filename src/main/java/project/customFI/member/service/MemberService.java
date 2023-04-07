package project.customFI.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.customFI.member.domain.Member;
import project.customFI.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private  final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByUserId(member.getUserId());
        if (findMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Transactional
    public void update(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        member.passwordChange(password);
    }

    @Transactional
    public void delete(String userId) {
        memberRepository.deleteByUserId(userId);
    }
}
