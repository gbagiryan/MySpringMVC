package am.bagiryan.myspringmvc.controller;

import am.bagiryan.myspringmvc.model.User;
import am.bagiryan.myspringmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User user;


    @RequestMapping(method = RequestMethod.GET, path = {"", "/", "index"})
    public ModelAndView home(@CookieValue(value = "cookieUsername", required = false) Cookie cookieUsername,
                             HttpSession session) {
        if (cookieUsername != null) {
            session.setAttribute("user", userRepository.getByName(cookieUsername.getValue()));
            return new ModelAndView("redirect:/profile");
        }else {
            return new ModelAndView("index");
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public ModelAndView registration() {

        return new ModelAndView("register");
    }


    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password,
                              @RequestParam(required = false, defaultValue = "false") boolean remember,
                              HttpSession session, HttpServletResponse response) {

        user = userRepository.getByName(username);
        if (user == null) {
            return new ModelAndView("index", "login", "username doesn't exist");
        }
        if (user.getPassword().equals(password)) {
            session.setAttribute("user", user);

            if (remember) {
                Cookie cookie = new Cookie("cookieUsername", user.getUsername());
                cookie.setMaxAge(30 * 24 * 60);
                response.addCookie(cookie);
            }

            return new ModelAndView("redirect:/profile");
        }

        return new ModelAndView("index", "login", "wrong password");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ModelAndView register(@RequestParam String name, @RequestParam String surname,
                                 @RequestParam String username, @RequestParam String password,
                                 @RequestParam(required = false, defaultValue = "0") int age, HttpSession session) {

        if (userRepository.getByName(username) != null) {
            return new ModelAndView("register", "reg", "That username already exists");
        } else if (name == null || surname == null || username == null || password == null ||
                name.equals("") || surname.equals("") || username.equals("") || password.equals("") ) {
            return new ModelAndView("register", "reg", "All fields must be filled");
        } else {
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(age);

            userRepository.addUser(user);

            session.setAttribute("user", user);
            return new ModelAndView("redirect:/profile");
        }
    }
}
