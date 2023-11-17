package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.management.MemoryNotificationInfo;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 테스트를 실행할 때마다 memberRepository, memberService 를 생성한다.

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given 무언가 주어졌는데
        Member member = new Member();
        member.setName("Spring");
        // when 이것을 실행했을 때
        Long saveId = memberService.join(member);
        // then 이런 결과가 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        // memberService.findOne(saveId); 를 먼저 치고 Ctrl + Alt + V(Extract Variable)
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // when
// 1. try-catch문을 사용 : 이거 때문에 try-catch 를 사용하는 건 애매하다. 2번을 권장한다.
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail(); // "테스트 예외가 발생해야 합니다."
//        } catch (IllegalStateException e) {
//            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // 2. assertThrow
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // () -> memberService.join(member2)); 이 로직을 실행할 건데,
        // IllegalStateException 이 예외가 터져야 한다.

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then


    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}