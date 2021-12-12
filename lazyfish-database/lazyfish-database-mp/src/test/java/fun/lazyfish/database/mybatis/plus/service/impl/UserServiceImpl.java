package fun.lazyfish.database.mybatis.plus.service.impl;

import fun.lazyfish.database.mybatis.plus.entity.User;
import fun.lazyfish.database.mybatis.plus.mapper.UserMapper;
import fun.lazyfish.database.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author chenjiamin
 * @date 2021/5/27 4:40 下午
 */
@Service
public class UserServiceImpl extends NibabaServiceImpl<UserMapper, User> implements UserService {


}
