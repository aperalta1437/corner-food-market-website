package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.controller.utils.LoginProcessIssueEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final CustomerRepository customerRepository;

    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String getLoginPage(@RequestParam(name = "issue", required = false)String loginIssue, Model model, HttpServletResponse response) throws IOException {
        LoginProcessIssueEnum loginProcessIssueEnum;

        if (loginIssue != null) {
            loginProcessIssueEnum = LoginProcessIssueEnum.valueOf(loginIssue);
        } else {
            loginProcessIssueEnum = LoginProcessIssueEnum.NONE;
        }
        model.addAttribute("loginProcessIssueEnum", loginProcessIssueEnum);
        return "login";
    }

//    @PostMapping
//    public void processLogin(@RequestParam(name = "issue", required = false)String loginIssue, HttpServletResponse response) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        System.out.println(auth.getClass());
//
//        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//            response.sendRedirect("/account");
//        } else {
//            if (loginIssue == null) {
//                loginIssue = LoginProcessIssueEnum.FAILED_LOGIN.name();
//            } else {
//                loginIssue = LoginProcessIssueEnum.EXPIRED_SESSION.name();
//            }
//            response.sendRedirect("/login?issue=" + loginIssue);
//        }
//    }

    @ModelAttribute
    public void checkAuth(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/account");
        }
    }
}
