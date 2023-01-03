package fun.nibaba.lazyfish.test.controller;

import cn.hutool.core.util.ReflectUtil;
import fun.nibaba.lazyfish.test.test.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RequestMapping(value = "/test")
@RestController
public class TestController {

    @GetMapping
    public R<Object> a(@RequestParam(value = "a", required = false) String a) {
        return new R<>(Test.getObject());
    }

    public static void main(String[] args) {
        R<String> r = new R<>("asdfmadlshjflkdsajflkjdas");
        Field[] fields = ReflectUtil.getFields(r.getClass());
        System.out.println(1);
    }

//    @GetMapping
//    public String a(@RequestParam(value = "a", required = false) String a) {
//        return "asdfmadlshjflkdsajflkjdas";
//    }

}
