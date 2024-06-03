package org.example.controller;

import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public Result register(Integer usertype, @Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        //判断是否有重名
        User u=userService.findName(username);
        //未重复则加密密码
        if(u==null){
            String pd= Md5Util.getMD5String(password);
            userService.register(usertype,username,pd);
            return Result.success();
        }
        else {
            return Result.error("用户名重复");
        }
    }
    //登录
    @PostMapping("/login")
    public Result login(Integer usertype, @Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        User u=userService.findName(username);
        if(u==null){
            return Result.error("用户名不正确");
        }
        if(Md5Util.getMD5String(password).equals(u.getPassword()) && usertype==u.getUsertype()){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token);
            return Result.success(token);
        }
        return Result.error("密码或用户类型不正确");
    }
    //获取用户信息
    @GetMapping("/userinfo")
    public Result<User> getUserInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username= (String) map.get("username");
        User loginuser = userService.findName(username);
        return Result.success(loginuser);
    }
    //更新用户信息
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    //更新用户头像
    @PutMapping("/avatar")
    public Result updateUserAvatar(@RequestParam @URL String avatar){
        userService.updateAvatar(avatar);
        return Result.success();
    }
    //重置密码
    @PatchMapping("/password")
    public Result updatePd(@RequestParam Map<String,String> params,@RequestHeader("Authorization") String token){
        Map<String,Object> map=ThreadLocalUtil.get();
        String username= (String) map.get("username");
        User u=userService.findName(username);
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!Md5Util.getMD5String(oldPwd).equals(u.getPassword())){
            return Result.error("密码错误");
        }
        if(oldPwd.equals(newPwd)){
            return Result.error("不能与原密码一致");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次输入的密码不一致");
        }
        userService.updatePd(newPwd);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    //更新收货地址
    @PutMapping("/address")
    public Result updateAddress(@RequestParam String address){
        userService.updateAddress(address);
        return Result.success();
    }

    //获取收货地址
    @GetMapping("/getaddress")
    public Result<String> getAddress(){
        String address=userService.getAddress();
        return Result.success(address);
    }
}
