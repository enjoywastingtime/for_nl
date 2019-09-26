package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.entity.SysUser;

@RestController
@RequestMapping("/web/authc")
public class AuthcController {

    @GetMapping("index")
    public Object index() {
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getSession().getAttribute("sysUser");
        return user.toString();
    }

    @GetMapping("admin")
    public Object admin() {
        return "Welcome Admin";
    }

    // delete
    @GetMapping("removable")
    public Object removable() {
        return "removable";
    }

    // insert & update
    @GetMapping("renewable")
    public Object renewable() {
        return "renewable";
    }
}
