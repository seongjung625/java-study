package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private EntityManager em;

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public SpringConfig(EntityManager em, MemberRepository memberRepository){
        this.em = em;

        this.memberRepository = memberRepository;
    }



    @Bean // 스프링 빈으로 등록
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }


    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


    /*
    // JPA
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);

    }
    */




}

/*
@Configuration
public class SpringConfig{

     @Bean // 스프링 빈으로 등록
     public MemberService memberService(){
            return new MemberService(memberRepository());
     }

     @Bean
     public MemberRepository memberRepository(){
           return new MemoryMemberRepository()
     }

 }


 */
