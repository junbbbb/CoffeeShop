package com.javalec.panel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.base.ExecMainFrame;
import com.javalec.dao.DaoEmployeeManage;
import com.javalec.dao.DaoMenuManage;
import com.javalec.dto.DtoEmployeeManage;
import com.javalec.dto.DtoMenuManage;
import javax.swing.JButton;

public class ExecMenuManagePanel extends JPanel {
	private JComboBox comboBox;
	private JLabel lblSearchBar;
	private JTextField tfSearch;
	private JLabel lblNewLabel;
	private JTextField tfTotalMenuNum;

	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable innertable;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private JTextField tfStoreNumMsg;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnInsert;

	/**
	 * Create the panel.
	 */
	public ExecMenuManagePanel() {
		setLayout(null);
		add(getBtnUpdate());
		add(getBtnInsert());
		add(getBtnDelete());
		add(getComboBox());
		add(getLblSearchBar());
		add(getTfSearch());
		add(getTfTotalMenuNum());
		add(getScrollPane());
		add(getTfStoreNumMsg());
		add(getLblNewLabel());
		tableInit();
		searchAction();

	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "메뉴코드", "분류", "메뉴명", "판매가", "판매여부" }));
			comboBox.setBounds(0, 11, 92, 30);
		}
		return comboBox;
	}

	private JLabel getLblSearchBar() {
		if (lblSearchBar == null) {
			lblSearchBar = new JLabel("New label");
			lblSearchBar
					.setIcon(new ImageIcon(ExecMainFrame.class.getResource("/com/javalec/image/SearchBarIcon.png")));
			lblSearchBar.setBounds(100, 11, 353, 30);
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
			lblNewLabel.setBackground(new Color(226, 161, 101));
			lblNewLabel.setIcon(new ImageIcon(
					ExecStoreManagePanel.class.getResource("/com/javalec/image/ExecStoreManagerPanelBrownbar.png")));
			lblNewLabel.setBounds(0, 51, 1024, 40);
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalMenuNum() {
		if (tfTotalMenuNum == null) {
			tfTotalMenuNum = new JTextField();
			tfTotalMenuNum.setBorder(null);
			tfTotalMenuNum.setEditable(false);
			tfTotalMenuNum.setBackground(new Color(226, 161, 101));
			tfTotalMenuNum.setText("");
			tfTotalMenuNum.setBounds(5, 61, 116, 21);
			tfTotalMenuNum.setColumns(10);
		}
		return tfTotalMenuNum;
	}

	// TableInit
	private void tableInit() {

		Outer_Table.addColumn("No"); // 테이블위에 쓰이는것들
		Outer_Table.addColumn("메뉴코드");
		Outer_Table.addColumn("분류");
		Outer_Table.addColumn("메뉴명");
		Outer_Table.addColumn("판매가");
		Outer_Table.addColumn("판매 매장 수");

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
		width = 100;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

	}

	private void searchAction() {
		DaoMenuManage dao = new DaoMenuManage();
		ArrayList<DtoMenuManage> dtoList = dao.selectList();

		int listCount = dtoList.size();
		tfTotalMenuNum.setText("전체: " + listCount + "건");

		for (int index = 0; index < listCount; index++) {

			// 메뉴 분류 시작
			ArrayList<String> category = new ArrayList<>();
			category.add("커피");
			category.add("베버리지");
			category.add("쥬스");
			// 메뉴 분류 끝

			// 임의 판매 매장 수 시작
			ArrayList<Integer> saleStoreNum = new ArrayList<>();
			saleStoreNum.add(2);
			saleStoreNum.add(2);
			saleStoreNum.add(3);
			saleStoreNum.add(3);
			saleStoreNum.add(3);
			saleStoreNum.add(3);
			saleStoreNum.add(3);
			saleStoreNum.add(1);
			// 임의 판매 매장 수 끝

			String wkNo = Integer.toString(index + 1);
			String wkMenuid = dtoList.get(index).getMenuid();
			String wkMenucategory = null;

			// 메뉴 분류 할당 시작
			if (wkMenuid.contains("B")) {
				wkMenucategory = category.get(1);
			} else if (wkMenuid.contains("C")) {
				wkMenucategory = category.get(0);
			} else if (wkMenuid.contains("J")) {
				wkMenucategory = category.get(2);
			}
			// 메뉴 분류 할당 끝

			String wkMenuname = dtoList.get(index).getMenuname();
			String wkMenuprice = Integer.toString(dtoList.get(index).getMenuprice());
			String wkMenusalestorenum = Integer.toString(saleStoreNum.get(index));
			String[] qTxt = { wkNo, wkMenuid, wkMenucategory, wkMenuname, wkMenuprice, wkMenusalestorenum };
			Outer_Table.addRow(qTxt);
		}

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

	private JTextField getTfStoreNumMsg() {
		if (tfStoreNumMsg == null) {
			tfStoreNumMsg = new JTextField();
			tfStoreNumMsg.setBackground(new Color(225, 161, 101));
			tfStoreNumMsg.setText("판매 매장 수를 클릭하면 매장 목록이 나옵니다.");
			tfStoreNumMsg.setBounds(767, 61, 250, 21);
			tfStoreNumMsg.setColumns(10);
			tfStoreNumMsg.setBorder(null);
		}
		return tfStoreNumMsg;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.setBounds(685, 61, 70, 21);
		}
		return btnDelete;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.setBounds(605, 61, 70, 21);
		}
		return btnUpdate;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("등록");
			btnInsert.setBounds(525, 61, 70, 21);
		}
		return btnInsert;
	}
} // End
