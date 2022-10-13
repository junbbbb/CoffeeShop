package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoEmployeeManage;
import com.javalec.util.DBConnect;

public class DaoEmployeeManage {
	String eid;
	String ename;
	String erank;
	String eindate;

	String conname; // 컬럼이름
	String condata; // 데이터값
	
	public ArrayList<DtoEmployeeManage> selectList() {

		ArrayList<DtoEmployeeManage> dtoList = new ArrayList<DtoEmployeeManage>();

		String whereStatement = "select eid, ename, erank, eindate, etelno, eemail, eaddress from employee ";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			// 데이터베이스에 접근을 하겠다 선언한것이다.

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			while (rs.next()) {

				String wkEid = rs.getString(1);
				String wkEname = rs.getString(2);
				String wkErank = rs.getString(3);
				String wkEindate = rs.getString(4);
				String wkEtelno = rs.getString(5);
				String wkEemail = rs.getString(6);
				String wkEaddress = rs.getString(7);

				DtoEmployeeManage dto = new DtoEmployeeManage(wkEid, wkEname, wkErank, wkEindate, wkEtelno, wkEemail,
						wkEaddress);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}// Select List

	public ArrayList<DtoEmployeeManage> selectListAdminEmployeeManage() {

		ArrayList<DtoEmployeeManage> dtoList = new ArrayList<DtoEmployeeManage>();

		String whereStatement = "select eid, ename, erank, eindate, etelno, eemail, eaddress from employee ";
		String whereStatement2 = "where erank = '사원' ";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			// 데이터베이스에 접근을 하겠다 선언한것이다.

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {

				String wkEid = rs.getString(1);
				String wkEname = rs.getString(2);
				String wkErank = rs.getString(3);
				String wkEindate = rs.getString(4);
				String wkEtelno = rs.getString(5);
				String wkEemail = rs.getString(6);
				String wkEaddress = rs.getString(7);

				DtoEmployeeManage dto = new DtoEmployeeManage(wkEid, wkEname, wkErank, wkEindate, wkEtelno, wkEemail,
						wkEaddress);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}// Select List

} // End
