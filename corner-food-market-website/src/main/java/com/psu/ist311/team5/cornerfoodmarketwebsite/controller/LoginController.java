package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.controller.utils.LoginProcessIssue;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(@RequestParam(name = "issue", required = false)String loginIssue, Model model, HttpServletResponse response) throws IOException {
        LoginProcessIssue loginProcessIssue;

        if (loginIssue != null) {
            loginProcessIssue = LoginProcessIssue.valueOf(loginIssue);
        } else {
            loginProcessIssue = LoginProcessIssue.NONE;
        }
        model.addAttribute("loginProcessIssue", loginProcessIssue);
        return "login";
    }

    @PostMapping(value = "/login")
    public void processLogin(@RequestParam(name = "issue", required = false)String loginIssue, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/account");
        } else {
            if (loginIssue == null) {
                loginIssue = LoginProcessIssue.FAILED_LOGIN.name();
            } else {
                loginIssue = LoginProcessIssue.EXPIRED_SESSION.name();
            }
            response.sendRedirect("/login?issue=" + loginIssue);
        }
    }

    @ModelAttribute
    public void checkAuth(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/account");
        }
    }
}
