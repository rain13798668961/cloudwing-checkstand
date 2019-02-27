package com.cloudwing.checkstand.web;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;
import com.cloudwing.checkstand.common.exception.ValidateException;
import com.cloudwing.checkstand.common.result.BaseResult;
import com.cloudwing.checkstand.shiro.token.manager.TokenMananger;
import com.cloudwing.checkstand.user.entity.User;
import com.cloudwing.checkstand.user.service.UserService;
import com.cloudwing.checkstand.user.vo.UserEmpowerVo;
import com.cloudwing.checkstand.user.vo.UserQueryVo;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<PageInfo<UserQueryVo>> list(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User user = TokenMananger.getToken();
        BaseResult<PageInfo<UserQueryVo>> baseResult = userService.listPage(user, page, limit);
        return baseResult;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(@RequestParam("id") Integer id, @RequestParam("status") String status) {
        try {
            validateUpdate(id, status);
        } catch (ValidateException e) {
//            log.info("user--->update校验出错: " + e.getErrMsg());
            return new BaseResult(BaseStatusEnum.ParamError.getStatus(), BaseStatusEnum.ParamError.getMsg(), null);
        }
        return userService.updateStatusById(id, status);
    }

    @RequestMapping(value = "/operate", method = RequestMethod.POST)
    public BaseResult operate(@RequestParam("userId") Integer userId, @RequestParam("companyId") Integer companyId, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        BaseResult<PageInfo<UserEmpowerVo>> pageInfoBaseResult = userService.listOperate(userId, companyId, page, limit);
        return pageInfoBaseResult;
    }

    @RequestMapping(value = "/operate/update", method = RequestMethod.POST)
    public BaseResult operate(@RequestParam("userId") Integer userId, @RequestParam("checked") boolean checked, @RequestParam("officeId")Integer officeId) {
        return userService.operateUpdateByOfficeIdAndChecked(userId, officeId, checked);
    }

    /**
     * 校验更新接口的请求参数
     * @param id
     * @param status
     */
    public void validateUpdate(Integer id, String status) throws ValidateException{
        if (null == id) {
            throw new ValidateException(BaseStatusEnum.ParamError);
        }

        if (null == status && status.equals("")) {
            throw new ValidateException(BaseStatusEnum.ParamError);
        } else if (!status.equals("Y") && !status.equals("N")) {
            throw new ValidateException(BaseStatusEnum.ParamError);
        }
    }

}
