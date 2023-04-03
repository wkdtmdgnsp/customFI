package project.customFI.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import project.customFI.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(@Param("userId") String userId);
}
