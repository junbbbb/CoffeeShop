package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoStoreManage;
import com.javalec.util.DBConnect;

public class DaoStoreManage {
	String storeseq;
	String sname;
	String saddress;
	String stelno;
	String scrn;
	String sclosedate;
	String sopendate;

	String conname; // 컬럼이름
	String condata; // 데이터값
	
	
public ArrayList<DtoStoreManage> selectList(){
		
		ArrayList<DtoStoreManage> dtoList = new ArrayList<DtoStoreManage>();
		
		String whereStatement = "select storeseq, sname, saddress, stelno, scrn, sopendate from store ";
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
				
				String wkStoreseq = rs.getString(1);
				String wkSname = rs.getString(2); // 컬럼이름쓰던가 번호쓰던가 내맘임
				String wkSaddress = rs.getString(3);
				String wkStelno = rs.getString(4);
				String wkScrn = rs.getString(5);
				String wkSopendate = rs.getString(6);
				
				DtoStoreManage dto = new DtoStoreManage(wkStoreseq, wkSname, wkSaddress, wkStelno, wkScrn, wkSopendate);
				dtoList.add(dto);
			}

			conn_mysql.close(); // 클로즈하기 내가쓰고 클로즈해야 다른사람도 들어간다.

		} catch (Exception e) {
			e.printStackTrace(); // 개발할떈 이렇게 쓰지만 나중엔 메세지로 잠시만 기다려주세요 등 쓰면됨.
		}
		return dtoList;
	}//Select List

} // End
