package com.yx.samples.spel.controller;

import com.yx.samples.spel.entity.User;
import com.yx.samples.spel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("users")
@Api(tags = "动态从外部参数spel来切换数据源的使用示例")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("通过Session传递")
    @GetMapping("/session")
    public List<User> usersFromSession(HttpServletRequest request, @RequestParam(value = "dsName", defaultValue = "") String dsName) {
        request.getSession().setAttribute("tenantName", dsName);
        return userService.selectSpelBySession();
    }

    @ApiOperation("通过Header传递")
    @GetMapping("/header")
    public List<User> usersFromHeader(@RequestHeader(value = "tenantName", defaultValue = "") String tenantName) {
        return userService.selectSpelByHeader();
    }

    @ApiOperation("通过参数传递")
    @GetMapping("/spel-expression")
    public List<User> usersFromSpelExpression(@RequestParam(value = "dsName", defaultValue = "") String dsName) {
        return userService.selectSpelByKey(dsName);
    }

    @ApiOperation("通过参数传递2")
    @GetMapping("/spel-expression2")
    public List<User> usersFromSpelExpression2(@RequestParam(value = "dsName", defaultValue = "") String dsName) {
        return userService.selecSpelByTenant(User.builder().tenantName(dsName).build());
    }
}
