package com.racoon.springmvc.controller;

/**
 * Created by icemintt on 6/9/16.
 */
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.racoon.springmvc.model.User;
import com.racoon.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService service;

    @Autowired
    MessageSource messageSource;

    /*
     * This method will list all existing employees.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {
        User user=new User();
        model.addAttribute("user",user);
        model.addAttribute("edit",false);
        return "login";
    }

    /*
     * This method will provide the medium to add a new employee.
     */


    /*
     * This method will be called on form submission, handling POST request for
     * saving employee in database. It also validates the user input
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation
         * and applying it on field [ssn] of Model class [Employee].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        if(!service.isUserNameUnique(user.getUsername())){
//            FieldError ssnError =new FieldError("user","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
//            result.addError(ssnError);
            return "registration";
        }

        service.saveUser(user);

        model.addAttribute("success", "User " + user.getUsername() + " registered successfully");
        return "success";
    }
    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "login";
        }
        if(!service.isCorrectLogin(user)){
            FieldError loginError=new FieldError("user","login","invalid login");
            result.addError(loginError);
            return "login";
        }
        model.addAttribute("success", "User " + user.getUsername()  + " login successfully");
        return "success";

    }


    /*
     * This method will provide the medium to update an existing employee.
     */
    @RequestMapping(value = { "/edit-{username}-user" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String username, ModelMap model) {
        User user=service.findByName(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-{username}-user" }, method = RequestMethod.POST)
    public String updateEmployee(@Valid User user, BindingResult result,
                                 ModelMap model, @PathVariable String username) {

        if (result.hasErrors()) {
            return "registration";
        }

        if(!service.isUserNameUnique(user.getUsername())){
//            FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
//            result.addError(ssnError);
            return "registration";
        }

        service.updateUser(user);

        model.addAttribute("success", "User " + user.getUsername()  + " updated successfully");
        return "success";
    }


    /*
     * This method will delete an employee by it's SSN value.
     */
    @RequestMapping(value = { "/delete-{username}-user" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String username) {
        service.deleteUser(username);
        return "redirect:/list";
    }

}

