package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import util.Jdbc;


public class userDaoImpl implements userDao {
	
	private Connection con;

	@Override
	public List<UserDTO> getAllUser() throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String  sql="select * from user";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		List<UserDTO> users=new ArrayList<>();
		
		while (rs.next()) {
			UserDTO user =new UserDTO();
			user.setUser_name(rs.getString("user_name"));
			user.setUser_id(rs.getInt("user_id"));
			user.setCar_id(rs.getInt("car_id"));
			users.add(user);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return users;
	}
	
	public static void main(String []args) throws SQLException{
		userDaoImpl userDaoImpl=new userDaoImpl();
		System.out.println("hello");
		System.out.println(userDaoImpl.getAllUser().size());
	}

	@Override
	public UserDTO getUserById(String id) throws SQLException {
		// TODO Auto-generated method stub
		this.con=Jdbc.getCon();
		String sql="select * from user where user_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ResultSet rs=ps.executeQuery();
		UserDTO user=new UserDTO();
		user.setUser_id(rs.getInt("user_id"));
		user.setUser_name(rs.getString("user_name"));
		user.setCar_id(rs.getInt("car_id"));
		ps.close();
		Jdbc.release(con);
		return user;
	}

	@Override
	public UserDTO getUserByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(String username) throws SQLException {
		// TODO Auto-generated method stub
		this.con =Jdbc.getCon();
		String  sql="insert into user(user_id,user_name,car_id) value(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(2, username);
		
		ps.close();
		Jdbc.release(con);
	}

	@Override
	public void deleteUser(String id) throws SQLException {
		// TODO Auto-generated method stub
		this.con=Jdbc.getCon();
		String sql="delete from user where user_id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ps.executeQuery();
		ps.close();
		Jdbc.release(con);
	}

	@Override
	public void updateUser(UserDTO user) throws SQLException {
		// TODO Auto-generated method stub
		this.con = Jdbc.getCon();
		String sql = "update user set car_id=? "
				+ " where user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, user.getCar_id());
		ps.setInt(2, user.getUser_id());
		

		ps.execute();
		ps.close();
		Jdbc.release(con);
		
	}

}
