package dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import web.CarService;

public class UserDTO {

	int user_id;
	String user_name;
	int  car_id;
	Link carLink;
	

	
	/**
	 * @return the carLink
	 */
	public Link getCarLink() {
		return carLink;
	}

	/**
	 * @param carLink the carLink to set
	 */
	public void setCarLink(Link carLink) {
		this.carLink = carLink;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(int user_id, String user_name, int car_id) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.car_id = car_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
		carLink=ControllerLinkBuilder.linkTo(CarService.class).slash("/cars/"+car_id).withRel("car");
	}


}
