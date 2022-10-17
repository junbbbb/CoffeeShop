package com.javalec.panel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dialog.StatDialog;
import com.javalec.dto.DtoSalesManage;
import com.javalec.util.DBConnect;
import com.javalec.function.AdminSalesFunction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AdminSalesPanel extends JPanel {

	private JComboBox cbSelection;
	public static JTextField tfSelection;
	private JLabel lblNewLabel;
	private JTextField tfTotalMenuNum;
	private JScrollPane scrollPane;
	private JTable innertable;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private JButton btnSelection;
	public static JTextField tfCalendar1;
	public static JTextField tfCalendar2;
	private JButton btnCalendarSelection;
	private JButton btnStat;
	public static int sum = 0;
	public static double avg = 0;
	public static String conditionQueryColumn = "";

	/**
	 * Create the panel.
	 */

	public AdminSalesPanel() {
		setLayout(null);
		add(getBtnStat());
		add(getTfCalendar2());
		add(getTfCalendar1());
		add(getBtnCalendarSelection());
		add(getCbSelection());
		add(getTfSelection());
		add(getTfTotalMenuNum());
		add(getLblNewLabel());
		add(getScrollPane());
		add(getBtnSelection());
		tableInit();
		searchAction();

	} // adminsalepanel end

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setBounds(0, 11, 92, 30);
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "직원ID", "고객ID", "메뉴ID", "메뉴명" }));
		}
		return cbSelection;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(100, 11, 320, 30);
			tfSelection.setColumns(10);
		}
		return tfSelection;
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
			tfTotalMenuNum.setBounds(330, 61, 116, 21);
			tfTotalMenuNum.setBorder(null);
			tfTotalMenuNum.setEditable(false);
			tfTotalMenuNum.setBackground(new Color(226, 161, 101));
			tfTotalMenuNum.setText("");
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

	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("");
			btnSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnSelection.setIcon(new ImageIcon(AdminSalesPanel.class.getResource("/com/javalec/image/search.png")));
			btnSelection.setBounds(430, 11, 50, 30);
		}
		return btnSelection;
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
		Outer_Table.addColumn("주문ID");
		Outer_Table.addColumn("직원ID");
		Outer_Table.addColumn("고객ID");
		Outer_Table.addColumn("메뉴ID");
		Outer_Table.addColumn("메뉴명");
		Outer_Table.addColumn("판매가");
		Outer_Table.addColumn("주문수량");
		Outer_Table.addColumn("판매총액");
		Outer_Table.addColumn("주문날짜");
		Outer_Table.addColumn("주문처리");
		Outer_Table.setColumnCount(11);

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF);
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 40;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 65;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 2;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 75;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 260;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 8;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 9;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 102;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 10;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

	}

	private void searchAction() {
		AdminSalesFunction dao = new AdminSalesFunction();
		ArrayList<DtoSalesManage> dtoList = dao.selectListSales();

		int listCount = dtoList.size();
		tfTotalMenuNum.setText("전체: " + listCount + "건");
		sum = 0;
		avg = 0;
		for (int index = 0; index < listCount; index++) {

			String wkNo = Integer.toString(index + 1);
			String wkOseq = Integer.toString(dtoList.get(index).getOseq());
			String wkEid = dtoList.get(index).getEid();
			String wkCustomer_custid = dtoList.get(index).getCustomer_custid();
			String wkMenu_menuid = dtoList.get(index).getMenu_menuid();
			String wkMenuname = dtoList.get(index).getMenuname();
			String wkMenuprice = Integer.toString(dtoList.get(index).getMenuprice());
			String wkOquantity = Integer.toString(dtoList.get(index).getOquantity());
			String wkOtotal = Integer.toString(dtoList.get(index).getOtotal());
			String wkOdate = dtoList.get(index).getOdate();
			String wkOprocess = "완료";

			String[] qTxt = { wkNo, wkOseq, wkEid, wkCustomer_custid, wkMenu_menuid, wkMenuname, wkMenuprice,
					wkOquantity, wkOtotal, wkOdate, wkOprocess };
			Outer_Table.addRow(qTxt);
			sum += Integer.parseInt(wkOtotal);

		}
		avg = sum / listCount;

	}

	private void conditionQuery() { // 콤보박스 결과 값 가져와 테이블 데이터 출력
		int i = cbSelection.getSelectedIndex();
		switch (i) {
		case 0:
			conditionQueryColumn = "o.employee_eid";
			break;

		case 1:
			conditionQueryColumn = "o.customer_custid";
			break;

		case 2:
			conditionQueryColumn = "o.menu_menuid";
			break;

		case 3:
			conditionQueryColumn = "m.menuname";

		default:
			break;

		}

		tableInit();
		conditionQueryAction(conditionQueryColumn);

	}

	private void conditionQueryAction(String conditionQueryColumn) { // 조건 검색

		if (tfSelection.getText().equals("")) {
			tableInit();
			searchAction();

		} else {
			AdminSalesFunction dao = new AdminSalesFunction();
			ArrayList<DtoSalesManage> dtoList = dao.selectListSalesCondition();

			int listCount = dtoList.size();
			tfTotalMenuNum.setText("전체: " + listCount + "건");
			sum = 0;
			avg = 0;
			if (listCount != 0) {
				for (int index = 0; index < listCount; index++) {

					String wkNo = Integer.toString(index + 1);
					String wkOseq = Integer.toString(dtoList.get(index).getOseq());
					String wkEid = dtoList.get(index).getEid();
					String wkCustomer_custid = dtoList.get(index).getCustomer_custid();
					String wkMenu_menuid = dtoList.get(index).getMenu_menuid();
					String wkMenuname = dtoList.get(index).getMenuname();
					String wkMenuprice = Integer.toString(dtoList.get(index).getMenuprice());
					String wkOquantity = Integer.toString(dtoList.get(index).getOquantity());
					String wkOtotal = Integer.toString(dtoList.get(index).getOtotal());
					String wkOdate = dtoList.get(index).getOdate();
					String wkOprocess = "완료";

					String[] qTxt = { wkNo, wkOseq, wkEid, wkCustomer_custid, wkMenu_menuid, wkMenuname, wkMenuprice,
							wkOquantity, wkOtotal, wkOdate, wkOprocess };
					Outer_Table.addRow(qTxt);
					sum += Integer.parseInt(wkOtotal);

				}
				avg = sum / listCount;
			} else {
				JOptionPane.showMessageDialog(null, "검색결과가없습니다.");
			}
		}
	}

	private JTextField getTfCalendar1() {
		if (tfCalendar1 == null) {
			tfCalendar1 = new JTextField();
			tfCalendar1.setBounds(12, 61, 106, 21);
			tfCalendar1.setColumns(10);
		}
		return tfCalendar1;
	}

	private JTextField getTfCalendar2() {
		if (tfCalendar2 == null) {
			tfCalendar2 = new JTextField();
			tfCalendar2.setColumns(10);
			tfCalendar2.setBounds(138, 61, 106, 21);
		}
		return tfCalendar2;
	}

	private JButton getBtnCalendarSelection() {
		if (btnCalendarSelection == null) {
			btnCalendarSelection = new JButton("");
			btnCalendarSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableInit();
					searchCalendarAction();
				}
			});
			btnCalendarSelection
					.setIcon(new ImageIcon(AdminSalesPanel.class.getResource("/com/javalec/image/search.png")));
			btnCalendarSelection.setBounds(260, 60, 50, 22);
		}
		return btnCalendarSelection;
	}

	private void searchCalendarAction() {
		AdminSalesFunction dao = new AdminSalesFunction();
		ArrayList<DtoSalesManage> dtoList = dao.selectListCalendar();

		int listCount = dtoList.size();
		sum = 0;
		avg = 0;
		tfTotalMenuNum.setText("전체: " + listCount + "건");
		if (listCount != 0) {
			for (int index = 0; index < listCount; index++) {

				String wkNo = Integer.toString(index + 1);
				String wkOseq = Integer.toString(dtoList.get(index).getOseq());
				String wkEid = dtoList.get(index).getEid();
				String wkCustomer_custid = dtoList.get(index).getCustomer_custid();
				String wkMenu_menuid = dtoList.get(index).getMenu_menuid();
				String wkMenuname = dtoList.get(index).getMenuname();
				String wkMenuprice = Integer.toString(dtoList.get(index).getMenuprice());
				String wkOquantity = Integer.toString(dtoList.get(index).getOquantity());
				String wkOtotal = Integer.toString(dtoList.get(index).getOtotal());
				String wkOdate = dtoList.get(index).getOdate();
				String wkOprocess = "완료";

				String[] qTxt = { wkNo, wkOseq, wkEid, wkCustomer_custid, wkMenu_menuid, wkMenuname, wkMenuprice,
						wkOquantity, wkOtotal, wkOdate, wkOprocess };
				Outer_Table.addRow(qTxt);

				sum += Integer.parseInt(wkOtotal);
			}

			avg = sum / listCount;
		} else {
			JOptionPane.showMessageDialog(null, "검색결과가없습니다.");
		}
	}

	private JButton getBtnStat() {
		if (btnStat == null) {
			btnStat = new JButton("통계보기");
			btnStat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatDialog dialog = new StatDialog();
					dialog.setVisible(true);

				}
			});
			btnStat.setBackground(new Color(240, 240, 240));
			btnStat.setBounds(920, 60, 95, 23);
		}
		return btnStat;
	}
} // End
