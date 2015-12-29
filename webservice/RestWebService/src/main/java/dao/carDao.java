/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import dto.CarDTO;

/**
 * @author yuanping
 *
 */
public interface carDao {

	public List<CarDTO> getAllcars() throws SQLException;
	public CarDTO getCarById(String id)throws SQLException;
	public CarDTO getCarByName(String name)throws SQLException;
	public void insertCar(String carName) throws SQLException;
	public void deleteCar(String id) throws SQLException;
	public void updateCar(CarDTO car)throws SQLException;
}
