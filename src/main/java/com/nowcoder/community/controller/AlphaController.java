package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Springboot...";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter()) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GET请求
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "5") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // Restful风格
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        return "a student";
    }

    // POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 相应HTML数据
    @RequestMapping(path = "teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "东南大学");
        model.addAttribute("age", "120");
        return "/demo/view";
    }

    // 响应json数据，通常在异步请求中
    // java对象 --- json字符串 --- js对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        HashMap<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", "20");
        emp.put("salary", "5000");
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", "20");
        emp.put("salary", "5000");
        list.add(emp);

        HashMap<String, Object> emp2 = new HashMap<>();
        emp2.put("name", "李四");
        emp2.put("age", "30");
        emp2.put("salary", "11000");
        list.add(emp2);
        return list;
    }

    // cookie
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置生效范围（访问这些路径时才会携带cookie）
        cookie.setPath("/community/alpha");
        // 设置生存时间（cookie默认关掉浏览器就消失了），让cookie持久化到硬盘
        cookie.setMaxAge(60 * 10);
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // session示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id" , 1);
        session.setAttribute("name", "Test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功");
    }

}
