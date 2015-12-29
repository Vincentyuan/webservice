package dao;

import java.sql.SQLException;
import java.util.List;

import dto.CarDTO;
import dto.UserDTO;


public interface userDao {

	public List<UserDTO> getAllUser() throws SQLException;
	public UserDTO getUserById(String id)throws SQLException;
	public UserDTO getUserByName(String name)throws SQLException;
	public void insertUser(String carName) throws SQLException;
	public void deleteUser(String id) throws SQLException;
	public void updateUser(UserDTO user) throws SQLException;
}
