package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.LoginRequest;
import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login")
    public Result<?> Auth(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Boolean login= loginService.auth(username,password);
        if(login){
            return Result.success("success");
        }
        else{
            return Result.error(404, "Password or Username is wrong");
        }
    }
}
