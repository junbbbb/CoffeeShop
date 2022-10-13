package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoSalesManage;
import com.javalec.panel.AdminSalesPanel;
import com.javalec.util.DBConnect;

public class SalesFunction {

	public ArrayList<DtoSalesManage> selectListSales() {

		ArrayList<DtoSalesManage> dtoList = new ArrayList<DtoSalesManage>();

		String whereStatement = "select o.oseq, e.eid, o.customer_custid, o.menu_menuid, m.menuname, m.menuprice, o.oquantity,m.menuprice*o.oquantity, o.odate ";
		String whereStatement2 = "from orders o, menu m, employee e, store s, menumanage mm ";
		String whereStatement3 = "where o.menu_menuid = m.menuid and e.eid = mm.employee_eid  group by oseq order by oseq;";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2 + whereStatement3);
		

			while (rs.next()) {

				int wkOseq = rs.getInt(1);
				String wkEid = rs.getString(2);
				String wkCustomer_custid = rs.getString(3);
				String wkMenu_menuid = rs.getString(4);
				String wkMenuname = rs.getString(5);
				int wkMenuprice = rs.getInt(6);
				int wkOquantity = rs.getInt(7);
				int wkOtotal = rs.getInt(8);
				String wkOdate = rs.getString(9);
				DtoSalesManage dto = new DtoSalesManage(wkOseq, wkEid, wkCustomer_custid, wkMenu_menuid, wkMenuname, wkMenuprice, wkOquantity, wkOtotal,wkOdate);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	

	public ArrayList<DtoSalesManage> selectListCalendar() {

		ArrayList<DtoSalesManage> dtoList = new ArrayList<DtoSalesManage>();

		String whereStatement = "select o.oseq, e.eid, o.customer_custid, o.menu_menuid, m.menuname, m.menuprice, o.oquantity, m.menuprice*o.oquantity, o.odate ";
		String whereStatement2 = "from orders o, menu m, employee e, store s, menumanage mm ";
		String whereStatement3 = "where o.menu_menuid = m.menuid and e.eid = mm.employee_eid and odate between '" + AdminSalesPanel.tfCalendar1.getText() +"' and '" + AdminSalesPanel.tfCalendar2.getText()+"'  group by oseq order by oseq;";

		
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			// 입력할떄 필요없음 검색할떄 필요함
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2 + whereStatement3);

			while (rs.next()) {

				int wkOseq = rs.getInt(1);
				String wkEid = rs.getString(2);
				String wkCustomer_custid = rs.getString(3);
				String wkMenu_menuid = rs.getString(4);
				String wkMenuname = rs.getString(5);
				int wkMenuprice = rs.getInt(6);
				int wkOquantity = rs.getInt(7);
				int wkOtotal = rs.getInt(8);
				String wkOdate = rs.getString(9);
				DtoSalesManage dto = new DtoSalesManage(wkOseq, wkEid, wkCustomer_custid, wkMenu_menuid, wkMenuname, wkMenuprice, wkOquantity, wkOtotal,wkOdate);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

} // End
