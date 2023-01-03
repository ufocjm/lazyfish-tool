package fun.nibaba.lazyfish.test.controller;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private List<Role> roleList;

    private String id;

    private String roleName;


}
