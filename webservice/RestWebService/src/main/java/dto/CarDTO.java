package dto;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import web.UserRentService;
import model.Car;

@JsonAutoDetect
public class CarDTO {
	
	int car_id;
	String car_name;
	boolean car_isrented;
	int user_id;
	Link UserLink;
	
	
	
	public CarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarDTO(int car_id, String car_name, boolean car_isrented, int user_id) {
		super();
		this.car_id = car_id;
		this.car_name = car_name;
		this.car_isrented = car_isrented;
		this.user_id = user_id;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public boolean isCar_isrented() {
		return car_isrented;
	}
	public void setCar_isrented(boolean car_isrented) {
		this.car_isrented = car_isrented;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
		UserLink=ControllerLinkBuilder.linkTo(UserRentService.class).slash("/user/"+user_id).withRel("user");
	}
	/**
	 * @return the userLink
	 */
	public Link getUserLink() {
		return UserLink;
	}
	/**
	 * @param userLink the userLink to set
	 */
	public void setUserLink(Link userLink) {
		UserLink = userLink;
	}
	
	
	

}
