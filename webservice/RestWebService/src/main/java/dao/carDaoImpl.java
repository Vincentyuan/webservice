package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.sun.org.apache.bcel.internal.generic.NEW;

import util.Jdbc;
import dto.CarDTO;

public class carDaoImpl implements carDao {

	private Connection con;

	@Override
	public List<CarDTO> getAllcars() throws SQLException {
		// TODO Auto-generated method stub

		con = Jdbc.getCon();
		String sql = "select * from car";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<CarDTO> cars = new ArrayList<>();

		while (rs.next()) {
			CarDTO car = new CarDTO();
			car.setCar_id(rs.getInt("car_id"));
			car.setCar_name(rs.getString("car_name"));
			car.setCar_isrented(rs.getBoolean("car_isrended"));
			car.setUser_id(rs.getInt("user_id"));
			cars.add(car);
		}

		rs.close();
		ps.close();

		Jdbc.release(con);

		return cars;
	}

	public static void main(String[] args) throws SQLException {

		carDao carDao = new carDaoImpl();
		CarDTO carDTO = carDao.getCarById("1");
		carDTO.setCar_isrented(true);
		carDTO.setUser_id(444);

		carDao.updateCar(carDTO);
	}

	@Override
	public CarDTO getCarById(String id) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from car where car_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ResultSet rs = ps.executeQuery();
		CarDTO car = new CarDTO();
		car.setCar_id(rs.getInt("car_id"));
		car.setCar_name(rs.getString("car_name"));
		car.setUser_id(rs.getInt("user_id"));
		car.setCar_isrented(rs.getBoolean("car_isrended"));

		rs.close();
		ps.close();
		Jdbc.release(con);

		return car;
	}

	@Override
	public CarDTO getCarByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from car where car_name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		CarDTO car = new CarDTO();
		car.setCar_id(rs.getInt("car_id"));
		car.setCar_name(rs.getString("car_name"));
		car.setUser_id(rs.getInt("user_id"));
		car.setCar_isrented(rs.getBoolean("car_isrended"));

		rs.close();
		ps.close();
		Jdbc.release(con);

		return car;
	}

	@Override
	public void insertCar(String carName) throws SQLException {
		// TODO Auto-generated method stub
		this.con = Jdbc.getCon();
		String sql = "insert into car(car_id,car_name,car_isrended,user_id)"
				+ " values (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(2, carName);
		ps.setString(3, "false");

		ps.execute();
		System.out.println("add sql excute finished " + carName);
		ps.close();
		Jdbc.release(con);
	}

	@Override
	public void deleteCar(String id) throws SQLException {
		// TODO Auto-generated method stub
		this.con = Jdbc.getCon();
		String sql = "delete from car where car_id =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		ps.execute();

		ps.close();
		Jdbc.release(con);
		
		
	}

	@Override
	public void updateCar(CarDTO car) throws SQLException {
		// TODO Auto-generated method stub
		this.con = Jdbc.getCon();
		String sql = "update car set user_id=? , " + "car_isrended=?"
				+ " where car_id=?";
		System.out.println(car.getCar_id() + " " + car.getCar_name() + " "
				+ car.isCar_isrented() + " " + car.getUser_id());
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, car.getUser_id());
		ps.setBoolean(2, true);
		ps.setInt(3, car.getCar_id());
		System.out.println(car.getCar_id() + " " + car.getCar_name() + " "
				+ car.isCar_isrented() + " " + car.getUser_id());
		System.out.println(sql);
		ps.execute();
		ps.close();
		Jdbc.release(con);
	}

}
