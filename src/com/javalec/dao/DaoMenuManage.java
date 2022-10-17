package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoMenuManage;
import com.javalec.panel.ExecMenuManagePanel;
import com.javalec.panel.ExecSalesPanel;
import com.javalec.util.DBConnect;

public class DaoMenuManage {
	String menuid;
	String menuname;
	int menuprice;

	String conname; // 컬럼이름
	String condata; // 데이터값
	
	// --------------- 메뉴 전체 출력 -----------------

	public ArrayList<DtoMenuManage> selectList() {

		ArrayList<DtoMenuManage> dtoList = new ArrayList<DtoMenuManage>();

		String whereStatement = "select menuid, menuname, menuprice from menu ";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			while (rs.next()) {

				String wkMenuid = rs.getString(1);
				String wkMenuname = rs.getString(2);
				int wkMenuprice = rs.getInt(3);
				DtoMenuManage dto = new DtoMenuManage(wkMenuid, wkMenuname, wkMenuprice);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	// ------------------ 조건 검색 ---------------------------
	
	public ArrayList<DtoMenuManage> selectListConditionQuery() {

		ArrayList<DtoMenuManage> dtoList = new ArrayList<DtoMenuManage>();

		String whereStatement = "select menuid, menuname, menuprice from menu ";
		String whereStatement2 = "where " + ExecMenuManagePanel.conditionQueryColumn;
		String whereStatement3 = " like '%" + ExecMenuManagePanel.tfSelection.getText().trim() + "%'"; // 조건 + 검색어

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2 + whereStatement3);

			while (rs.next()) {

				String wkMenuid = rs.getString(1);
				String wkMenuname = rs.getString(2);
				int wkMenuprice = rs.getInt(3);
				DtoMenuManage dto = new DtoMenuManage(wkMenuid, wkMenuname, wkMenuprice);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	
	
	
	
	// ----------------- 지점장 메뉴관리 ---------------------
	
	public ArrayList<DtoMenuManage> selectListAdminMenuManage() {

		ArrayList<DtoMenuManage> dtoList = new ArrayList<DtoMenuManage>();

		String whereStatement = "select menuid, menuname, menuprice from menu";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			// 메뉴 분류 시작
			ArrayList<String> category = new ArrayList<>();
			category.add("커피");
			category.add("베버리지");
			category.add("쥬스");
			String wkMenucategory = null;
			// 메뉴 분류 끝

			while (rs.next()) {

				String wkMenuid = rs.getString(1);
				String wkMenuname = rs.getString(2);

				// 메뉴 분류 할당 시작
				if (wkMenuid.contains("B")) {
					wkMenucategory = category.get(1);
				} else if (wkMenuid.contains("C")) {
					wkMenucategory = category.get(0);
				} else if (wkMenuid.contains("J")) {
					wkMenucategory = category.get(2);
				}
				// 메뉴 분류 할당 끝

				int wkMenuprice = Integer.parseInt(rs.getString(3));
				DtoMenuManage dto = new DtoMenuManage(wkMenuid, wkMenuname, wkMenucategory, wkMenuprice);

				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;

	}

} // End
