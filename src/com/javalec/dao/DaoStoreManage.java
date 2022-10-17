package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dialog.UpdateStore;
import com.javalec.dto.DtoSalesManage;
import com.javalec.dto.DtoStoreManage;
import com.javalec.panel.ExecSalesPanel;
import com.javalec.panel.ExecStoreManagePanel;
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

	// ---------- 모든 매장 출력 ----------------

	public ArrayList<DtoStoreManage> selectList() {

		ArrayList<DtoStoreManage> dtoList = new ArrayList<DtoStoreManage>();

		String whereStatement = "select storeseq2, sname, saddress, stelno, eid, ename, scrn, sopendate "
				+ "from store";
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			while (rs.next()) {

				String wkStoreseq2 = rs.getString(1);
				String wkSname = rs.getString(2);
				String wkSaddress = rs.getString(3);
				String wkStelno = rs.getString(4);
				String wkEid = rs.getString(5);
				String wkEname = rs.getString(6);
				String wkScrn = rs.getString(7);
				String wkSopendate = rs.getString(8);

				DtoStoreManage dto = new DtoStoreManage(wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname,
						wkScrn, wkSopendate);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	// --------------- 조건 검색 결과 출력 ----------------------

	public ArrayList<DtoStoreManage> selectListSalesCondition() {

		ArrayList<DtoStoreManage> dtoList = new ArrayList<DtoStoreManage>();

		String whereStatement = "select storeseq2, sname, saddress, stelno, eid, ename, scrn, sopendate "
				+ "from store where ";
		String whereStatement2 = ExecStoreManagePanel.conditionQueryColumn + " like '%"
				+ ExecStoreManagePanel.tfSelection.getText().trim() + "%' order by storeseq2";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {

				String wkStoreseq2 = rs.getString(1);
				String wkSname = rs.getString(2);
				String wkSaddress = rs.getString(3);
				String wkStelno = rs.getString(4);
				String wkEid = rs.getString(5);
				String wkEname = rs.getString(6);
				String wkScrn = rs.getString(7);
				String wkSopendate = rs.getString(8);

				DtoStoreManage dto = new DtoStoreManage(wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname,
						wkScrn, wkSopendate);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	// ---------------- 수정 및 삭제용 검색 ------------------
	public ArrayList<DtoStoreManage> selectListUpdate() {

		ArrayList<DtoStoreManage> dtoList = new ArrayList<DtoStoreManage>();

		String whereStatement = "select storeseq2, sname, saddress, stelno, eid, ename, scrn, sopendate "
				+ "from store where ";
		String whereStatement2 = UpdateStore.conditionQueryColumn + " like '%"
				+ UpdateStore.tfSelection.getText().trim() + "%' order by storeseq2";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {

				String wkStoreseq2 = rs.getString(1);
				String wkSname = rs.getString(2);
				String wkSaddress = rs.getString(3);
				String wkStelno = rs.getString(4);
				String wkEid = rs.getString(5);
				String wkEname = rs.getString(6);
				String wkScrn = rs.getString(7);
				String wkSopendate = rs.getString(8);

				DtoStoreManage dto = new DtoStoreManage(wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname,
						wkScrn, wkSopendate);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
} // End
