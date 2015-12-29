package web;

import java.sql.SQLException;
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
public class CarService {

	// show all cars
	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CarDTO> getCars() {

		carDao carDao = new carDaoImpl();

		try {
			return carDao.getAllcars();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// show car by id
	@RequestMapping(value = "/cars/{car_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CarDTO getCarById(@PathVariable("car_id") String car_id)
			throws Exception {

		return new carDaoImpl().getCarById(car_id);
	}

	// update car by post method
	@RequestMapping(value = "/cars}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String updateCar(@RequestBody CarDTO car) throws Exception {

		carDao carDao = new carDaoImpl();
		try {
			carDao.updateCar(car);
			return "update finished";
		} catch (Exception e) {
			// TODO: handle exception
			return "update failed";
		}

	}

	// add car by put mehtod
	@RequestMapping(value = "/cars", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String addCar(@RequestBody CarDTO carDTO) {
		carDao carDao = new carDaoImpl();
		try {
			carDao.insertCar(carDTO.getCar_name());
			return "update finished";
		} catch (Exception e) {
			// TODO: handle exception
			return "update failed";
		}
	}

	@RequestMapping(value = "/cars/{car_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String deleteCar(@PathVariable String car_id) {
		carDao carDao = new carDaoImpl();
		try {
			CarDTO car = new carDaoImpl().getCarById(car_id);
			userDao userDao = new userDaoImpl();
			
			UserDTO userDTO = userDao.getUserById(String.valueOf(car
					.getUser_id()));
			userDTO.setCar_id(0);
			userDao.updateUser(userDTO);
			
			carDao.deleteCar(car_id);
			return "delete finished";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "delete failed";
		}

	}
}
