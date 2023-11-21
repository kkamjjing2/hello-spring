package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService { // ctrl + shift + T :

    private final MemberRepository memberRepository;

    // Controller 에서와 마찬가지로 의존성 주입
    public MemberService(MemberRepository memberRepository) { // constructor : alt + insert
        this.memberRepository = memberRepository;
    }
    // MemoryMemberRepository 가 구현체로 있다. 그래서 MemoryMemberRepository 를 서비스에 주입한다.

    // 회원 가입
    public Long join(Member member) {
        // 비즈니스 로직에 같은 이름이 있는 중복 회원은 안 된다는 규칙을 정했다.
        validateDuplicateMember(member); // 중복 회원 검증 메서드 (shift + alt + M)
        // findByName 메서드는 반환 타입이 Optional 이다.
        // result 에 이미 같은 값이 있으면, "이미 존재하는 회원입니다." 를 던진다.
        // ifPresent 는 result 에 null 이 아니라 어떤 값이 있으면 로직이 동작하게 한다.
        // 과거에는 if (result == null) ~ 이런 식으로 코딩했지만
        // 지금은 null 일 가능성이 있으면 Optional 로 한 번 감싸서 반환하고,
        // 감쌌으니까 ifPresent 같은 것을 쓸 수 있으며, 꺼낼 때는 get 으로 꺼낸다.
        // Optional 은 꺼내서 바로 반환하는 게 좋지 않다.
        memberRepository.save(member);
        return member.getId();
    }
    // join 메서드 : 중복 회원 검증 후 통과하면 저장소에 저장하고 아이디를 반환한다.

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    // 회원가입을 하면 id를 반환한다.


    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Optional<Member> findOne(Long memberId) {
        return  memberRepository.findById(memberId);
    }
}
