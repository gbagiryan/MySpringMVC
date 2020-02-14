package am.bagiryan.myspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public ModelAndView logout() {
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete")
    public ModelAndView delete(HttpSession session) {

        return new ModelAndView("index");
    }
}
