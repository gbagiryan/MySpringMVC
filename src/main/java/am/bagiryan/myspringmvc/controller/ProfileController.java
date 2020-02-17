package am.bagiryan.myspringmvc.controller;

import am.bagiryan.myspringmvc.model.User;
import am.bagiryan.myspringmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView logout(@CookieValue(value = "cookieUsername", required = false) Cookie cookieUsername,
                         HttpSession session, HttpServletResponse response) {
        if (cookieUsername!=null) {
            cookieUsername.setMaxAge(0);
            response.addCookie(cookieUsername);
        }
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete")
    public ModelAndView delete(HttpSession session) {

        userRepository.deleteUser((User) (session.getAttribute("user")));
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
}
