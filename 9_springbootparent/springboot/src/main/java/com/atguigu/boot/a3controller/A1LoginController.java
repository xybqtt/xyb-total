package com.atguigu.boot.a3controller;

import com.atguigu.boot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

//@RestController // 相当于@Controller + @ResponseBody
@Controller
public class A1LoginController {

    @GetMapping(value = {"/", "/login"})
    public String showLogin() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String main(User user, HttpSession session, Model model) {

        if(!StringUtils.isEmpty(user.getUsername()) && user.getPassword().length() > 0) {
            // 验证通过，用session保存用户
            session.setAttribute("loginUser", user);
            // 重写向main页面，防止每次出新的时候重复提交
            return "redirect:/main";
        } else {
            model.addAttribute("msg", "账号密码错误");
            // 验证失败，返回login页面
            return "login";
        }
    }

    @GetMapping(value = "/main")
    public String mainPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if(user != null)
            // 进入main页面
            return "main";

        // 返回登陆页面
        return "redirect:/login";
    }

}















