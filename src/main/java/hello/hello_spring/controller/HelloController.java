package hello.hello_spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러는 메서드를 호출하는곳
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 /hello라고 들어오면 이 메서드를 호출한다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // key와 value
        return "hello"; // hello.html로 가라는 의미
        // templates/hello.html로 가라는 의미
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ // 외부에서 name을 받아옴.
        // http://localhost:8080/hello-mvc?name=spring!!!!!! 이렇게 해줘야 name이라는 parameter에 값이 들어옴.
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // JSON방식으로 리턴. (key,value)로 이루어진 구조임.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
