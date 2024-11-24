package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository(); // repository라는 참조변수는 MemoryMemberRepository 클래스에 있는 메서드들 참조 가능.




    @AfterEach
    public void afterEach(){
         repository.clearStore(); // Test를 할 때 메서드가 임의로 실행되는데 순서에 관계없이 메서드가 실행되도 오류가 안나게 하기 위한 코드.
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring"); // ID는 존재하지않고 Name을 spring으로 초기화.

        repository.save(member); // ID 1L을 키값으로 갖고 spring을 value 값으로 가지는 store 맵이 생성됨. save 메서드의 반환값은 멤버 객체임.

        Member result = repository.findById(member.getId()).get(); // ID가 1L이므로 1L에 해당하는 value 값이 반환됨. value는 member 객체다.

        assertThat(member).isEqualTo(result); // member와 result가 일치하는지 알아보기 위함.
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // 위에 save 메서드에 있는 줄과 똑같으므로 설명 생략.

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result =repository.findByName("spring1").get(); // findByName은 매개변수 name(=spring1)과 객체의 Name이 같은 객체 하나를 반환.

        assertThat(result).isEqualTo(member1); // member와 result가 일치하는지 알아보기 위함.
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 두 객체가 result에 저장.
        assertThat(result.size()).isEqualTo(2); // 두개의 객체가 있으므로 길이는 2 2와 2는 같으니까 True.
    }


}
