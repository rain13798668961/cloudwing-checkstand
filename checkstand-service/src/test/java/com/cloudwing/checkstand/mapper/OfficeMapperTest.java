package com.cloudwing.checkstand.mapper;

import com.cloudwing.checkstand.common.utils.FastJsonUtils;
import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.office.mapper.OfficeMapper;
import com.cloudwing.checkstand.user.dto.RadioDto;
import com.cloudwing.checkstand.user.vo.UserEmpowerVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OfficeMapperTest extends BaseTest {

    @Autowired
    private OfficeMapper officeMapper;

    @Test
    public void test() {

        List<RadioDto> l = officeMapper.listCodeAndNameByUserId(1);
        System.out.println(l.size());
        for (RadioDto d : l) {
            System.out.println(FastJsonUtils.toJSONString(d));
        }
    }

    @Test
    public void listByCompanyId() {
        List<Office> offices = officeMapper.listByCompanyId(1);
        for (Office office : offices) {
            System.out.println(office.toString());
        }
    }

    @Test
    public void listUserEmpowerVosByUserIdAndCompanyIdTest() {
        List<UserEmpowerVo> userEmpowerVos = officeMapper.listUserEmpowerVosByUserIdAndCompanyId(14, 1);
        for (UserEmpowerVo userEmpowerVo : userEmpowerVos) {
            System.out.println(userEmpowerVo.toString());
        }
    }
}
