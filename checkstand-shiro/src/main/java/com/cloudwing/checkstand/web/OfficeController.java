package com.cloudwing.checkstand.web;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.office.dto.OfficeQueryDto;
import com.cloudwing.checkstand.office.entity.Office;
import com.cloudwing.checkstand.office.service.OfficeService;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/office")
public class OfficeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "/list", method=RequestMethod.POST)
    public BaseResult<PageInfo<Office>> officeList(OfficeQueryDto officeQueryDto) {
        log.info("------------invoke--- /office/list");
        User token = TokenMananger.getToken();

        return officeService.listByConditions(token,officeQueryDto);
    }

    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public BaseResult<Object> createOffice(Office office) {
        log.info("------------invoke--- /office/create");
        User token = TokenMananger.getToken();

        return officeService.createOffice(token, office);
    }

    @RequestMapping(value = "/mch/list", method=RequestMethod.POST)
    public BaseResult<Object> mchList(@RequestParam(value = "officeCode")String officeCode) {

        User token = TokenMananger.getToken();
        return officeService.listRelatedMchByOfficeId(token, officeCode);
    }

    @RequestMapping(value = "/mch/relate", method=RequestMethod.POST)
    public BaseResult<Object> mchRelate() {

        return null;
    }

}
