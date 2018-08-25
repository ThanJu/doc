package me.phoibe.doc.cms.controller;

import me.phoibe.doc.cms.dao.PhoibeUserMapper;
import me.phoibe.doc.cms.domain.po.PhoibeUser;
import me.phoibe.doc.cms.domain.po.PhoibeUserExample;
import me.phoibe.doc.cms.entity.Code;
import me.phoibe.doc.cms.entity.Constant;
import me.phoibe.doc.cms.entity.Result;
import me.phoibe.doc.cms.utils.CollectionUtils;
import me.phoibe.doc.cms.utils.JsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengzhaolei
 * @Title: LoginContorller
 * @Description: class
 * @date 2018/8/25 0:20
 */
@Controller
@RequestMapping("/phoibe/")
public class LoginContorller {

    @Resource
    private PhoibeUserMapper phoibeUserMapper;


    private static final String SESSION_KEY = "user";

    @Value("${phoibe.address}")
    private String address;

    @GetMapping("/")
    public String index(@SessionAttribute(SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        return "index";
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
    @RequestMapping(value = "userlogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String userlogin(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        HttpServletResponse response) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return JsonUtils.toJson(new Result<>(Code.LOGIN_FAILED, "ok"));
        }
        PhoibeUserExample phoibeUserExample = new PhoibeUserExample();
        phoibeUserExample.setDistinct(true);
        PhoibeUserExample.Criteria criteria = phoibeUserExample.createCriteria();
        criteria.andUserNameEqualTo(username);
        String encryed = getPassword(password, username);
        criteria.andPasswordEqualTo(encryed);
        System.out.println(phoibeUserExample.toString());

        List<PhoibeUser> list = phoibeUserMapper.selectByExample(phoibeUserExample);

        if (CollectionUtils.isNotEmpty(list)) {
            try {
                response.sendRedirect(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.sendRedirect(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }


    public static String getPassword(String password, String username) {

        if (StringUtils.isEmpty(password)) {
            return null;
        }

        return String.valueOf(
                DigestUtils.md5Hex(username + password + Constant.SAULT));
    }


    public static void main(String[] args) {
//        StringBuilder str1 = DigestUtils.appendMd5DigestAsHex("sdfwerfss".getBytes(), new StringBuilder(Constant.SAULT));
//        StringBuilder str2 = DigestUtils.appendMd5DigestAsHex("sdfwerfss".getBytes(), new StringBuilder(Constant.SAULT));

//        DigestUtils.md5Hex("sdfwerfss" +  Constant.SAULT);
//        boolean b1 = str1.equals(str2);
//        System.out.println(str1);
//        System.out.println(str2);
//        System.out.println(b1);
        System.out.println(getPassword("123456", "liming"));
    }
}
