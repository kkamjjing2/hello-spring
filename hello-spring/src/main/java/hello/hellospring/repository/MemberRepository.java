package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // repository 에  4가지 기능을 만들었다.
    Member save(Member member);
    // 1. save 메서드 : 회원을 저장소에 저장하는 기능
    Optional<Member> findById(Long id);
    // 2. findById 메서드 : 저장소에서 findById 로 id 값을 찾아오는 기능

    // Optional 을 쓰는 이유 : 메서드로 파라미터 값을 가져오는데 null 일 수도 있다.
    // 값이 없으면 null로 반환될 수 있는데 요즘에는 null 을 처리하는 방법에서
    // null을 그대로 반환하는 방법 대신 Optional 로 감싸서 반환하는 방법을 선호한다.
    Optional<Member> findByName(String name);
    // 3. findByName 메서드 : 저장소에서 findByName 으로 name 값을 찾아오는 기능
    List<Member> findAll();
    // 4. findAll 메서드 : 지금까지 저장한 모든 회원 리스트를 반환한다.

}
