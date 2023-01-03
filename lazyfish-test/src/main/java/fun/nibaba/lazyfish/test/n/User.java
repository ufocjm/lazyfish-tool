package fun.nibaba.lazyfish.test.n;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Data
public class User {

//    private List<Role> roles;

    @UserName("name")
    private String id;

    private String name;

//    private User user;

    //    private Integer age;
//
//    private boolean sex;
//
    private List<User> childUsers;


    private Map<String, User> childUserMap;

}
