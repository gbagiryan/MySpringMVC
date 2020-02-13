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
public class MainController {


    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public ModelAndView logout(){
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete")
    public ModelAndView delete(HttpSession session){

        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public ModelAndView registration(){
        return new ModelAndView("register");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user = userRepository.getByName(username);
        if (user==null){
            return new ModelAndView("index", "wrongLogin", "username doesn't exist");
        }
        if (user!=null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return new ModelAndView("profile");
        }

        return new  ModelAndView("index", "wrongLogin", "wrong password");
    }
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ModelAndView register(@RequestParam String name, @RequestParam String surname,
                                 @RequestParam String username, @RequestParam String password,
                                 @RequestParam int age, HttpSession session){

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);

        userRepository.addUser(user);

        return new ModelAndView("profile");
    }

}
