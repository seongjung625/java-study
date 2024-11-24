package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Spring containter 생성 후, Controller 애노테이션이 있으면 MemberController 객체를 생성해서 Spring에 넣어둠.
// Controller라는 애노테이션이 있으면 Spring이 뜰 때 생성을 한다음 컨테이너가 관리.
// MemberController는 Spring Container가 뜰 때 생성.
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // memberService는 spring이 spring container에 있는 멤버 서비스를 가져다가 연결을 시켜줌.
    // 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객체를 가져다가 딱 넣어줌.
    // 의존관계라고 부름.

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 기본적으로 url을 치는건 GetMapping임. 조회 할 때 주로 씀.
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터를 for문같은데에 넣어서 전달할때 씀.
    public String create(MemberForm form){ // MemberForm 클래스를 가면 private String name이 있음. createMemberForm.html 파일에 있는 name이라는 값이 들어감.
        Member member = new Member();
        member.setName(form.getName()); // 입력.

        System.out.println("member = "+member.getName()); // 점심먹고 위에 member.setName부터 다시보기.

        memberService.join(member); // 입력하고나서 입력값 중복 체크.

        return "redirect:/"; // localhost:8080으로 이동.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}

