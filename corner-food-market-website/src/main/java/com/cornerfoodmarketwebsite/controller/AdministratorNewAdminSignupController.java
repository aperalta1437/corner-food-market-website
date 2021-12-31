package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.AdministratorNewAdminSignupService;
import com.cornerfoodmarketwebsite.business.service.utils.UuidValidationResponseEnum;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/new-admin-signup")
public class AdministratorNewAdminSignupController {
    private final AdministratorNewAdminSignupService administratorNewAdminSignupService;

    @Autowired
    public AdministratorNewAdminSignupController(AdministratorNewAdminSignupService administratorNewAdminSignupService) {
        this.administratorNewAdminSignupService = administratorNewAdminSignupService;
    }

    @GetMapping(value = "/email/{new-admin-email}/uuid/{uuid}")
    public ResponseEntity<String> validateUuid(@PathVariable(value = "new-admin-email") String newAdministratorEmail, @PathVariable(value = "uuid") String uuid) {
        JSONObject jsonResponse = new JSONObject();
        try {
            UuidValidationResponseEnum uuidValidationResponseEnum = this.administratorNewAdminSignupService.validateUuid(newAdministratorEmail, uuid);
            jsonResponse.put("Message", uuidValidationResponseEnum.getResponseMessage());
            if (uuidValidationResponseEnum == UuidValidationResponseEnum.NOT_FOUND) {
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.NOT_FOUND);
            } else if (uuidValidationResponseEnum == UuidValidationResponseEnum.IS_USED || uuidValidationResponseEnum == UuidValidationResponseEnum.EXPIRED || uuidValidationResponseEnum == UuidValidationResponseEnum.IS_CANCELLED) {
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.GONE);
            }
        } catch (Exception exception) {
            try {
                jsonResponse.put("Message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }
}
