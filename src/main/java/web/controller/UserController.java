package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
	public String getUsers(ModelMap model) {
		List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "user";
	}

	@PostMapping(value = "/")
	public String deleteUserDataBase(@RequestParam("id") long id){
		userService.removeUser(id);
		return "redirect:/";
	}

	@GetMapping(value = "/addUser")
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addAndUpdateUser";
	}

	@PostMapping(value = "/addUser")
	public String addUserDataBase(@ModelAttribute("user")User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "/updateUser")
	public String updateUser(@RequestParam("id") long id, ModelMap model){
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "addAndUpdateUser";
	}

	@PostMapping(value = "/updateUser")
	public String updateUserDataBase(@ModelAttribute("user")User user){
		userService.updateUser(user);
		return "redirect:/";
	}

}