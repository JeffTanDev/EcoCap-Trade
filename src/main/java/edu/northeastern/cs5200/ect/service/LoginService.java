package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;

public interface LoginService {
    CompanyUser auth(String username, String password);
}
