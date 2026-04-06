package training.web;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class Hello {

    public static void main(String[] args)
    {
        SpringApplication.run(Hello.class, args);
    }

    @GetMapping("/")
    public String index()
    {
        return "Hello Spring boot web application.";
    }

    @GetMapping("/test")
    public Point test()
    {
        Point data = new Point(3, 4);
        return data;
    }

    //處理來自 echo?name="參數值"
    @GetMapping("/echo")
    public String echo(@RequestParam String name)
    {
        return "Hello " + name;
    }

    //處理來自 add?n1=整數&n2=整數
    @GetMapping("/add")
    public Map add(@RequestParam int n1,@RequestParam int n2)
    {
        return Map.of("Ans:",n1+n2);
    }

    //處理來自 user/路徑值
    @GetMapping("/user/{name}")
    public String reply(@PathVariable String name)
    {
        return "Hello " + name;
    }
}