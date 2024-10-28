package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.mapper.LoginMapping;
import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapping loginMapping;

    @Override
    public Boolean auth(String username, String password) {
        CompanyUser newCompany=loginMapping.auth(username,password);
        System.out.println(newCompany);
        if (newCompany!=null){
            return true;
        }else {
            return false;
        }
    }
}