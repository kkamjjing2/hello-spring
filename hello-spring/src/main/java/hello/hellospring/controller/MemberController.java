package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring 컨테이너라는 Spring 통이 생기는데, @Controller 라는 애너테이션을 적으면 MemberController 라는 객체를 생성해서 Spring 에 널어두고 관리한다.
// Spring 컨테이너에서 Spring Bean 이 관리된다고 표현한다.

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 1. DI 에는 세 가지 주입 방법이 있다.
// 아래는 필드 주입 방식이다. 위아래 코드 대신 적을 수 있다. 필드 주입 방식은 별로 좋지 않다.
// @Autowired private MemberService memberService;

//    @Autowired
//    // @Autowired : 생성자에서 쓰면, MemberController 가 생성될 때 Spring 컨테이너에 있는, Spring Bean 에 있는  MemberService 객체를 가져다가 연결해준다.
//    // 이게 바로 Dependency Injection 이다. 의존 관계를 주입하는 거다.
//    // 2. 여기서는 생성자를 통해서 MemberService 를 MemberController 에 주입했다(생성자 주입).
    // 생성하는 시점에 적고 그 다음에 변경하지 못하게 막을 수 있어서 Setter 주입보다 좋다.
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

// 3. Setter 주입
//  단점 : 누군가 MemberController 를 호출했을 때 Public 으로 열려 있어야 한다. 노출이 되는 거다.
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

}
