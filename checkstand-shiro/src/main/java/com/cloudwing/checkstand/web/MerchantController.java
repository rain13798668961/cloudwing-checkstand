package com.cloudwing.checkstand.web;

import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.merchant.service.MerchantService;
import com.cloudwing.checkstand.merchant.vo.MerchantQueryVo;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.user.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/mch")
public class MerchantController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<PageInfo<MerchantQueryVo>> list(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User user = TokenMananger.getToken();
        BaseResult<PageInfo<MerchantQueryVo>> baseResult = merchantService.listPage(user, page, limit);
        return baseResult;
    }
}
