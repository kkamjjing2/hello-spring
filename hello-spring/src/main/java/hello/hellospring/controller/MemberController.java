package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring 컨테이너라는 Spring 통이 생기는데, @Controller 라는 애너테이션을 적으면 MemberController 라는 객체를 생성해서 Spring 에 널어두고 관리한다.
// Spring 컨테이너에서 Spring Bean 이 관리된다고 표현한다.

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    // @Autowired : 생성자에서 쓰면, MemberController 가 생성될 때 Spring 컨테이너에 있는, Spring Bean 에 있는  MemberService 객체를 가져다가 연결해준다.
    // 이게 바로 Dependency Injection 이다. 의존 관계를 주입하는 거다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
