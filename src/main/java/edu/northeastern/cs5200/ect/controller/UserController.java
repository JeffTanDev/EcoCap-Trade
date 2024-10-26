package edu.northeastern.cs5200.ect.controller;

import edu.northeastern.cs5200.ect.pojo.Result;
import edu.northeastern.cs5200.ect.pojo.User;
import edu.northeastern.cs5200.ect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/company")
    public Result<?> getInfo(@RequestParam("name") String name) {
        User curUser = userService.get(name);

        if (curUser != null) {
            // 如果找到公司信息，返回成功的 Result 包装数据
            return Result.success(curUser);
        } else {
            // 如果未找到公司信息，返回错误的 Result 包装状态和消息
            return Result.error(404, "Company not found");
        }
    }
}
