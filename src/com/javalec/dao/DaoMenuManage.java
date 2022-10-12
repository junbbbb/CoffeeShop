package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoMenuManage;
import com.javalec.util.DBConnect;

public class DaoMenuManage {
	String menuid;
	String menuname;
	int menuprice;
	
	String conname; // 컬럼이름
	String condata; // 데이터값
	
	
public ArrayList<DtoMenuManage> selectList(){
		
		ArrayList<DtoMenuManage> dtoList = new ArrayList<DtoMenuManage>();
		
		String whereStatement = "select menuid, menuname, menuprice from menu ";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			// 데이터베이스에 접근을 하겠다 선언한것이다.

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			// while은 false 하면 끝나버림
			while (rs.next()) {
				
				String wkMenuid = rs.getString(1);
				String wkMenuname = rs.getString(2); // 컬럼이름쓰던가 번호쓰던가 내맘임
				int wkMenuprice = rs.getInt(3);
				DtoMenuManage dto = new DtoMenuManage(wkMenuid, wkMenuname, wkMenuprice);
				dtoList.add(dto);
			}

			conn_mysql.close(); // 클로즈하기 내가쓰고 클로즈해야 다른사람도 들어간다.

		} catch (Exception e) {
			e.printStackTrace(); // 개발할떈 이렇게 쓰지만 나중엔 메세지로 잠시만 기다려주세요 등 쓰면됨.
		}
		return dtoList;
	}//Select List

} // End
