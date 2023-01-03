package fun.nibaba.lazyfish.test.controller;

import lombok.Data;

import java.util.List;

@Data
public class R<T> {

    public R(T data) {
        this.data = data;
    }

    private T data;

    private List<String> strs;

}
