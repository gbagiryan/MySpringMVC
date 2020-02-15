package am.bagiryan.myspringmvc.controller;

import am.bagiryan.myspringmvc.model.User;
import am.bagiryan.myspringmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/profile")
    public ModelAndView prof(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("index", "login", "you must log in");
        }
        return new ModelAndView("profile");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public String logout() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete")
    public ModelAndView delete(HttpSession session) {

        userRepository.deleteUser((User) (session.getAttribute("user")));
        session.setAttribute("user", null);
        return new ModelAndView("redirect:/index");
    }
}
