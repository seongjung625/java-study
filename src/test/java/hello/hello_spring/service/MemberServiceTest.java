package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {
    // shift+f10은 이전에 실행했던걸 실행해줌.

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // 객체를 새로 생성해서 MemberService에 있는 MemoryMemberRepository와 다름. 같은 것을 참조 해야 하는데 어떻게 해야하는지 생각.

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); // 메서드 실행이 끝나면 Database의 값들을 다 날려줌.
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);
        /*
        name이 hello -> findByName 메서드를 참조해서 member 객체에 이름이 hello인 member 객체를 반환.
        중복하면 예외처리로감.

        validateDuplicateMember 메서드 호출이 끝나면 save 메서드를 호출해서
        ID가 1L이고 Name이 hello인 store 맵을 생성함.
        이러한 상황에서 member 객체 반환 {1L="hello"}
        객체를 반환 후, getId 메서드를 호출하면  1L 결론은 1L.
         */

        //then
        Member findMember = memberService.findOne(saveId).get();
        /*
        1L이라는 Id를 받아서 findById를 호출.
        객체는 당연히 Optional 클래스에 member형.
        get()을 통해 member만 리턴.
         */

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        /*
        hello와  hello가 일치하는가? true.
         */
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //  assertThrows가 내부적으로 "람다식 안의 메서드를 실행해 본 후" 예외가 발생하는지를 확인하는 방식

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}