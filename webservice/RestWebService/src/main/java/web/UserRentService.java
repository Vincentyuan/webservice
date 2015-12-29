package web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dao.carDao;
import dao.carDaoImpl;
import dao.userDao;
import dao.userDaoImpl;
import dto.CarDTO;
import dto.UserDTO;
@Controller
public class UserRentService {

	private userDao userDao;

	public UserRentService() {
		this.userDao = new userDaoImpl();
	}

	// show all users for get mehtod
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserDTO> getUsers() {
		try {
			return userDao.getAllUser();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	// show users by user_id
	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDTO getUserById(@PathVariable("user_id") String user_id) {
		try {
			return userDao.getUserById(user_id);

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * //show one user for get method by name
	 * 
	 * @RequestMapping(value="/users/{user_name}", method=RequestMethod.GET)
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public UserDTO getUser(@PathVariable("user_name") String
	 * user_name){ return null;
	 * 
	 * }
	 */

	// update users ,with post
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String setUser(@RequestBody UserDTO user) {
		try {
			userDao.updateUser(user);
			carDao carDao = new carDaoImpl();
			CarDTO car = carDao.getCarById(String.valueOf(user.getCar_id()));
			
			car.setCar_isrented(true);
			car.setUser_id(user.getUser_id());
			
			carDao.updateCar(car);
			return "update finished";
		} catch (Exception e) {
			// TODO: handle exception
			return "update failed";
		}

	}

	@RequestMapping(value = "/users/{user_name}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String addUser(@PathVariable("user_name") String userName) {
		try {
			userDao.insertUser(userName);
			return "add Finished";
		} catch (Exception e) {
			// TODO: handle exception
			return "add failed";
		}

	}

	// delete method by id
	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String DeleteUserById(@PathVariable("user_id") String user_id) {
		try {
			UserDTO user = userDao.getUserById(user_id);
			
			carDao carDao=new carDaoImpl();
			CarDTO car = carDao.getCarById(String.valueOf(user
					.getCar_id()));
			car.setCar_isrented(false);
			carDao.updateCar(car);
			userDao.deleteUser(user_id);
			return "delete finished";
		} catch (Exception e) {
			// TODO: handle exception
			return "delete failed";
		}

	}
}
