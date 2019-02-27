package com.cloudwing.checkstand.mapper;

import cn.hutool.core.bean.BeanUtil;
import com.cloudwing.checkstand.company.mapper.CompanyMapper;
import com.cloudwing.checkstand.company.service.impl.CompanyManager;
import com.cloudwing.checkstand.permission.service.RoleService;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.mapper.UserMapper;
import com.cloudwing.checkstand.user.mapper.UserOfficeMapper;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserMapperTest extends  BaseTest{
    private static final Logger log = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOfficeMapper userOfficeMapper;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private RoleService roleService;

    @Test
    public void testFindByAccountAndPassword() {
        User user  = userMapper.findByAccountAndPassword("test","123456");
        System.out.println(user.getName());
    }

    @Test
    public void getUserQueryVoById() {
        UserQueryVo userQueryVoById = userMapper.getUserQueryVoById(1);
        System.out.println(userQueryVoById.toString());
    }

    @Test
    public void listUserQueryVoTest() {
        List<UserQueryVo> userQueryVos = userMapper.listUserQueryVo();
        System.out.println(userQueryVos);
    }

    @Test
    public void listPageTest() {
        PageHelper.startPage(2, 10);
        List<User> userList = userMapper.selectAll();
        System.out.println(((Page)userList).getTotal());
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println("分页数据: " + pageInfo);
    }

    @Test
    public void selectAllTest() {
        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void pageInfoTest() {
        PageHelper.startPage(1, 2).setOrderBy("id desc");
        final PageInfo<User> userPageInfo = new PageInfo<>(this.userMapper.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);
    }

    @Test
    public void listUserQueryVoByCompanyId() {
        List<UserQueryVo> userQueryVos = userMapper.listUserQueryVoByCompanyId(2);
        System.out.println(userQueryVos.size());
//        for (UserQueryVo userQueryVo : userQueryVos) {
//            System.out.println(userQueryVo.toString());
//        }
    }

    @Test
    public void listByUserIdSelectOfficeIdTest() {
        List<User> users = userMapper.listByUserIdSelectOfficeId(2);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void listUserQueryVoByUidTest() {
        Integer[] oids = userOfficeMapper.selectOidByUid(2);
        Integer[] uids = userOfficeMapper.selectUidByOid(oids);
        List<UserQueryVo> userQueryVos = userMapper.listUserQueryVoByUid(uids);
        for (UserQueryVo userQueryVo : userQueryVos) {
            System.out.println(userQueryVo.toString());
        }
    }

    /**
     * 将数据库表Bean转换为返回前端时的Bean
     * @param userList
     * @return
     */
    public  List<UserQueryVo> userToUserQueryVo (List<User> userList) {
        List<UserQueryVo> userQueryVoList = new ArrayList<>();
        if (null != userList && userList.size() != 0) {
            for (User user : userList) {
                UserQueryVo userQueryVo = BeanUtil.toBean(user, UserQueryVo.class);
                userQueryVo.setCompanyName(companyManager.getCompanyNameById(userQueryVo.getCompanyId()));
//                userQueryVo.setPassword(null);// 密码不返回
                userQueryVo.setDescription(roleService.roleDescriptionByUserId(userQueryVo.getId()));
                userQueryVoList.add(userQueryVo);
            }
            return userQueryVoList;
        }
        return null;
    }
}
