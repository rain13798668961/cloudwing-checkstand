package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.user.mapper.UserOfficeMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by cjf on 2019/1/29.
 */
public class UserOfficeMapperTest extends BaseTest {
    @Autowired
    private UserOfficeMapper userOfficeMapper;

    @Test
    public void selectOidByUidTest() {
        Integer[] integers = userOfficeMapper.selectOidByUid(4);
        for (int i = 0; i < integers.length; i ++) {
            System.out.println(integers[i]);
        }
    }

    @Test
    public void selectUidByOidTest() {
        Integer[] oids = userOfficeMapper.selectOidByUid(4);
        Integer[] uids = userOfficeMapper.selectUidByOid(oids);
        for (int i = 0; i < uids.length; i ++) {
            System.out.println(uids[i]);
        }
    }

    @Test
    public void removeByUserIdAndOfficeIdTest() {
        Integer result = userOfficeMapper.removeByUserIdAndOfficeId(15, 9);
        System.out.println(result);
    }

    @Test
    public void saveByUserIdAndOfficeIdTest() {
        Integer result = userOfficeMapper.saveByUserIdAndOfficeId(14, 1);
        System.out.println(result);
    }
}
