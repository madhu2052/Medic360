package com.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.emp.jdbc.JdbcConnectivity;
import com.emp.model.LoginModel;

public class LoginDao {
	private Connection connection=null;
	public boolean checkCredentials(LoginModel lm) throws ClassNotFoundException, SQLException {
		ResultSet result=null;
		boolean isStatus=false;
		connection=JdbcConnectivity.connect(); 
		String sql="select * from login where username=? and password=?";
		PreparedStatement statement=connection.prepareStatement(sql);
		statement.setString(1, lm.getUsername());
		statement.setString(2,lm.getPassword());
		
		result=statement.executeQuery();
		
		if( result.next())
		{
			isStatus=true;
		}
		
		
		return isStatus;
	}

}
