package com.javalec.panel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.javalec.base.ExecMainFrame;
import com.javalec.dao.DaoMenuManage;
import com.javalec.dao.DaoMenuManageManage;
import com.javalec.dto.DtoMenuManage;
import com.javalec.dto.DtoMenuManageManage;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class AdminMenuManagePanel extends JPanel {
	private JComboBox comboBox;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JLabel lblNewLabel;
	private JTextField tfTotalMenuNum;
	private JScrollPane scrollPane;
	private JTable innertable;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

	/**
	 * Create the panel.
	 */
	public AdminMenuManagePanel() {
		setLayout(null);
		add(getComboBox());
		add(getLblSearchBar());
		add(getTfSearch());
		add(getTfTotalMenuNum());
		add(getLblNewLabel());
		add(getScrollPane());
		tableInit();
		searchAction();

	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(0, 11, 92, 30);
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "메뉴코드", "분류", "메뉴명", "판매가", "판매여부" }));
		}
		return comboBox;
	}

	private JLabel getLblSearchBar() {
		if (lblSearchBar == null) {
			lblSearchBar = new JLabel("New label");
			lblSearchBar.setBounds(100, 11, 353, 30);
			lblSearchBar
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/SearchBarIcon.png")));
		}
		return lblSearchBar;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(100, 11, 320, 30);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(0, 51, 1024, 40);
			lblNewLabel.setBackground(new Color(226, 161, 101));
			lblNewLabel.setIcon(new ImageIcon(
					ExecStoreManagePanel.class.getResource("/com/javalec/image/ExecStoreManagerPanelBrownbar.png")));
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalMenuNum() {
		if (tfTotalMenuNum == null) {
			tfTotalMenuNum = new JTextField();
			tfTotalMenuNum.setBounds(5, 61, 116, 21);
			tfTotalMenuNum.setBorder(null);
			tfTotalMenuNum.setEditable(false);
			tfTotalMenuNum.setBackground(new Color(226, 161, 101));
			tfTotalMenuNum.setText("전체: 24건");
			tfTotalMenuNum.setColumns(10);
		}
		return tfTotalMenuNum;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 92, 1025, 626);
			scrollPane.setViewportView(getInnertable());
		}
		innertable.setModel(Outer_Table);
		return scrollPane;
	}

	private JTable getInnertable() {
		if (innertable == null) {
			innertable = new JTable();
			innertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return innertable;
	}

	private void tableInit() {

		Outer_Table.addColumn("No");
		Outer_Table.addColumn("메뉴코드");
		Outer_Table.addColumn("분류");
		Outer_Table.addColumn("메뉴명");
		Outer_Table.addColumn("판매가");
		Outer_Table.addColumn("판매여부");
		/*
		 * 판매여부는 DB의 menumanage 테이블의 mmstartdate와 mmenddate 활용
		 * 
		 * select count(*) from menumanage where menuid = wkMenuid mmstartdate is not
		 * null and mmenddate is null;
		 * 
		 * 
		 * 
		 * 
		 */

		Outer_Table.setColumnCount(6); // 몇개인지 알려줘야함.

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF); // 태이블끼리 왓다갓다하면 따른짓을 많이해야해서 끈다.
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 45;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 2;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 250;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

	}

	private void searchAction() {
		DaoMenuManage dao = new DaoMenuManage();
		DaoMenuManageManage dao2 = new DaoMenuManageManage();
		ArrayList<DtoMenuManage> dtoList = dao.selectListAdminMenuManage();
		ArrayList<DtoMenuManageManage> dtoList2 = dao2.selectListAdminMenuManageManage();

		int listCount = dtoList.size();
		String wkMenusell2;

		for (int index = 0; index < listCount; index++) {
		

			String wkNo = Integer.toString(index + 1);
			String wkMenuid = dtoList.get(index).getMenuid();
			String wkMenuname = dtoList.get(index).getMenuname();
			String wkMenucategory = dtoList.get(index).getMenucategory();
			String wkMenuprice = Integer.toString(dtoList.get(index).getMenuprice());
			String wkMenusell = dtoList2.get(index).getMmenddate();

			if (wkMenusell == null) {
				wkMenusell2 = "판매중";
			} else {
				wkMenusell2 = "미판매";
			}

			String[] qTxt = { wkNo, wkMenuid, wkMenuname, wkMenucategory, wkMenuprice, wkMenusell2 };
			Outer_Table.addRow(qTxt);
			
			// 미완성 솔루션. dtoList2.size < dtoList.size 일 때 에러! index bound

		}

	}

} // End
