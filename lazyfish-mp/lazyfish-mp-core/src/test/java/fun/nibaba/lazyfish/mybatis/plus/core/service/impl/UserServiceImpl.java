package fun.nibaba.lazyfish.mybatis.plus.core.service.impl;

import fun.nibaba.lazyfish.mybatis.plus.entity.User;
import fun.nibaba.lazyfish.mybatis.plus.core.mapper.UserMapper;
import fun.nibaba.lazyfish.mybatis.plus.core.service.UserService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:40 下午
 */
@Service
public class UserServiceImpl extends LazyServiceImpl<UserMapper, User> implements UserService {


}
