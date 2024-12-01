package edu.northeastern.cs5200.ect.service.impl;

import edu.northeastern.cs5200.ect.pojo.CompanyUser;
import edu.northeastern.cs5200.ect.service.ProfileService;
import edu.northeastern.cs5200.ect.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    
    @Autowired
    private ProfileMapper profileMapper;
    
    @Override
    public CompanyUser getProfileByUsername(String username) {
        try {
            CompanyUser user = profileMapper.findByUsername(username);
            System.out.println("Found user: " + user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
} 