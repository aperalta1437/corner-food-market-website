package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import com.cornerfoodmarketwebsite.business.service.utils.SignupResponseEnum;
import com.cornerfoodmarketwebsite.business.service.SignupFormService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/signup")
public class SignupController {
    private final SignupFormService signupFormService;
    private final ExceptionLogService exceptionLogService;

    @Autowired
    public SignupController(SignupFormService signupFormService, ExceptionLogService exceptionLogService) {
        this.signupFormService = signupFormService;
        this.exceptionLogService = exceptionLogService;
    }

//    @GetMapping
//    public String getSignupPage() {
//        model.addAttribute("signupForm", new SignupForm());
//
//        return "signup";
//    }

    @PostMapping(value = "/process-signup")
    public ResponseEntity<String> processSignup(SignupForm signupForm) {
        JSONObject jsonResponse = new JSONObject();
        try {
            SignupResponseEnum signupResponseEnum = this.signupFormService.processNewSignup(signupForm);


        } catch (Exception exception) {
            this.exceptionLogService.logException(exception);
            try {
                jsonResponse.put("Message", SignupResponseEnum.SERVER_ERROR.getSignupMessage());
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

//    @ModelAttribute
//    public void checkAuth(HttpServletResponse response) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//            response.sendRedirect("/account");
//        }
//    }
}
