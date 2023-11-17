package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // key 는 Long id 니까 Long 을 적고, value 는 Member member 니까 Member 를 적는다.
    // 현업에서는 동시성 문제가 있을 수 있어서 store 처럼 공유되는 변수일 때는
    // concurrent HashMap 을 써야 하는데 이번 프로젝트는 간단하므로 단순하게 HashMap을 사용한다.

    private static long sequence = 0L;
    // 0, 1, 2 이렇게 키값을 생성하기 위해 선언했다.
    // 실무에서는 동시성 문제를 고려해서 Atomic Long 등등을 해야 하는데 단순하게 했다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    // setId 할 때 먼저 sequence 값을 하나 올릴 거다.
    // sequence 값을 하나 올린 다음 store 에 put 으로
    // member.getId() 해서 Id 가져와서 넣고 member 객체도 넣어준다.

    // store 에 객체를 넣기 전에 member 에 id 값을 세팅하고,
    // save 하기 전에 이름은 넘어온 상태다. 왜냐하면
    // name 은 고객이 회원가입 할 때 적는 이름이고, id 는 시스템에 저장할 때 시스템이 정해주는 번호이기 때문이다.
    // 그래서 id 값을 따로 세팅한 다음 store 에 저장해야 한다. 그러면 map 에 저장된다.
    // 마지막으로 저장된 member 를 반환한다.

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    // id 값은 store 에서 꺼내면 된다.
    // null 이 반환될 가능성이 있으니까 Optional 로 감싼다.
    // 이렇게 감싸서 반환하면 null 이 반환돼도 클라이언트에서 뭔가 할 수 있다.

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 루프를 돌면서 member 에 있는 name 과 파라미터로 넘어온 name 이 같은지 확인한다.
        // 이름이 같은 경우 반환한다.
        // .findAny(); 의 결과가 자동으로 Optional 로 반환된다.
        // 만약 끝까지 돌렸는데 없으면 Optional 에 null 을 포함해서 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    // store 에 있는 values() 는 멤버들이다. 멤버가 모두 반환된다.
    // 실무할 때 루프 돌리기도 편해서 List 를 많이 쓴다.

    public void clearStore() {
        store.clear();
    }
}