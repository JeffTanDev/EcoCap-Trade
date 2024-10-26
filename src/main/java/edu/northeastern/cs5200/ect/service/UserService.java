package edu.northeastern.cs5200.ect.service;

import edu.northeastern.cs5200.ect.pojo.User;

public interface UserService {
    /**
     * 查询公司信息
     * @return
     */
    User get(String name);
}
