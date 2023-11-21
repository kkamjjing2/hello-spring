package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을 때는 extends다.
// 인터페이스는 다중 상속이 되므로 JpaRepository 와 MemberRepository 둘 다 받을 수 있다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
// SpringDataJpaMemberRepository 는 JpaRepository 를 받고 있다. 그러면 스프링 데이터 JPA 가 스프링 빈에 자동으로 등록을 하고, 구현체를 자동으로 만들어준다.