package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.domain.dto.UserInfo;
import me.phoibe.doc.cms.domain.po.PhoibeRole;
import me.phoibe.doc.cms.domain.po.PhoibeUser;
import me.phoibe.doc.cms.entity.Code;
import me.phoibe.doc.cms.entity.Constant;
import me.phoibe.doc.cms.entity.Result;
import me.phoibe.doc.cms.security.JwtUtil;
import me.phoibe.doc.cms.service.PhoibeUserService;
import me.phoibe.doc.cms.utils.JsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tengzhaolei
 * @Title: LoginContorller
 * @Description: class
 * @date 2018/8/25 0:20
 */
@RestController
@RequestMapping("/phoibe/")
public class LoginContorller {

    @Autowired
    private PhoibeUserService phoibeUserService;


    private static final String SESSION_KEY = "user";

    @Value("${phoibe.address}")
    private String address;

    @GetMapping("/")
    public Object index(@RequestParam String token, Model model) throws Exception {
        return "";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse response) {
        // 移除session
//        session.removeAttribute(SESSION_KEY);

        try {
            response.sendRedirect(address+"login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login";
    }


    @PostMapping("/loginPost")
    public @ResponseBody
    Map<String, Object> loginPost(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (!"123456".equals(password)) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        // 设置session
        session.setAttribute(SESSION_KEY, account);

        map.put("success", true);
        map.put("message", "登录成功");
        return map;
    }

    /**
     * 登录
     */
    @RequestMapping(value = "userlogin", method = { RequestMethod.POST})
    public Object userlogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            HttpServletResponse response, HttpServletRequest request) {

        PhoibeUser phoibeUser = new PhoibeUser();
        phoibeUser.setUserName(username);
        phoibeUser.setPassword(password);

        try {
            phoibeUser = phoibeUserService.login(phoibeUser);
            if(phoibeUser != null){
                String jwt = JwtUtil.generateToken(phoibeUser.getId().toString());
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(phoibeUser,userInfo);
                PhoibeRole phoibeRole = phoibeUserService.fetchUserRoleByUserId(userInfo.getId());
                userInfo.setRoleType(phoibeRole.getRoleType());
                userInfo.setRoleName(phoibeRole.getRoleName());

                Cookie cookie = new Cookie(JwtUtil.HEADER_STRING,jwt);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge((int)JwtUtil.EXPIRATION_TIME);
                response.addCookie(cookie);

                return JsonUtils.toJson(new Result<UserInfo>(Code.SUCCESS, userInfo));
            }
        } catch (Exception e) {
            return JsonUtils.toJson(new Result<>(Code.FAILED, e.getMessage()));
        }


        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    public static String getPassword(String password, String username) {

        if (StringUtils.isEmpty(password)) {
            return null;
        }

        return String.valueOf(
                DigestUtils.md5Hex(username + password + Constant.SAULT));
    }

}
