package fun.nibaba.lazyfish.test.n;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import fun.nibaba.lazyfish.trans.fields.ITransHandle;
import fun.nibaba.lazyfish.trans.fields.TransModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        UserNameTransProcessor userNameTransProcessor = new UserNameTransProcessor();


        TransModel transModel = new TransModel(User.class);
        transModel.scan(Lists.newArrayList(userNameTransProcessor));
        transModel.valid();

        Object o = getObject();

        List<ITransHandle> transHandles = Lists.newLinkedList();
        transModel.buildHandles(transHandles, userNameTransProcessor.getClassType(), o);
        List<Object> keys = transHandles.stream().map(ITransHandle::getKey).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(keys);
        Map<Object, Object> transMap = userNameTransProcessor.getTransMap(keys);
        for (ITransHandle handle : transHandles) {
            handle.setValue(transMap);
        }

        System.out.println(o);
    }

    private static Object getObject() {
        User user = new User();
        user.setId(IdUtil.simpleUUID());


        User childUser = new User();
        childUser.setId(IdUtil.simpleUUID());
        user.setChildUsers(Lists.newArrayList(childUser));

//        User user1 = new User();
//        user1.setId(IdUtil.simpleUUID());
//        user.setUser(user1);
//        Map<String, User> map = Maps.newHashMap();
//        User childUser1= new User();
//        childUser1.setId(IdUtil.simpleUUID());
//        map.put(childUser.getId(), childUser1);
//        user.setChildUserMap(map);
        return user;

//        if (RandomUtil.randomBoolean()) {
//            User user = new User();
//            user.setId(IdUtil.simpleUUID());
//
//            User childUser = new User();
//            childUser.setId(IdUtil.simpleUUID());
//            user.setChildUser(childUser);
//            return user;
//        } else {
//            List<User> users = Lists.newArrayList();
//            int randomInt = RandomUtil.randomInt(100);
//            for (int i = 0; i < randomInt; i++) {
//                User user = new User();
//                user.setId(IdUtil.simpleUUID());
//                users.add(user);
//
//                User childUser = new User();
//                childUser.setId(IdUtil.simpleUUID());
//                user.setChildUser(childUser);
//            }
//            return users;
//        }
    }


}
