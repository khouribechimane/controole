package com.imane.controle.controllers;

import com.imane.controle.entities.User;
import com.imane.controle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserRepository loginRep;
    @GetMapping("/verification")
    public String verifie(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
        User log=loginRep.verifie(username, password);
        boolean b=true;
        if(log==null) {
            String msg="usename or password incorrect";
            b=false;
            model.addAttribute("b",b);
            model.addAttribute("msg",msg);
            return "login";

        }else {
            return "index";
        }
    }
}
