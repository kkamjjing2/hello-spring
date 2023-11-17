package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


// MemoryMemberRepository 를 테스트 할 것이기 때문에 테스트 할 클래스 이름을 그대로 적어 주고 Test 만 뒤에 붙인다. 이게 관례다.
// 다른 데서 이 클래스를 가져다 쓰지 않을 것이기 때문에 public class 라고 적지 않고 그냥 class 라고 적어도 된다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 모든 테스트는 순서랑 상관없이 메서드 별로 다 따로 동작하게 설계해야 한다.
    // 테스트 코드를 순서에 의존적으로 설계하면 않으면 에러가 날 수 있다.
    // 콜백 메서드를 만들어서 테스트 하나가 끝나면 해당 데이터를 깔끔하게 클리어 해야 한다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        Assertions.assertEquals(member, result); // import org.junit.jupiter.api.Assertions;
        // Assertions.assertEquals(member, null); // 실패 테스트 예시
        // repository 에 저장된 member 와, result 에 저장된 객체가 같은지 확인한다.

        assertThat(result).isEqualTo(member); // import org.assertj.core.api.Assertions; 테스트를 좀 더 쉽게 하게 해준다.
        // Assertions.assertThat(member).isEqualTo(null); // 실패 테스트 예시
    }


    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("junghwan");
        repository.save(member1);

        Member member2 = new Member(); // 위에 코드 복붙해서 member1 부분에서 Shift + F6 하면 이름 편하게 바꿀 수 있다.
        member2.setName("kkamjjing");
        repository.save(member2);

        // junghwan 과 kkamjjing 이라는 회원이 가입됐다.

        Member result = repository.findByName("junghwan").get(); // 이렇게 끝에 get() 을 쓰면 Optional 을 한 번 까서 쓸 수 있다.
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("junghwan1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("kkamjjing1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}