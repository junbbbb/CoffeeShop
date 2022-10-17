package com.javalec.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.sql.Connection;
import java.sql.DriverManager;

import com.javalec.base.AdminMainFrame;
import com.javalec.dao.DaoEmployeeManage;
import com.javalec.dto.DtoEmployeeManage;
import com.javalec.dto.DtoSalesManage;
import com.javalec.function.ExecSalesFunction;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExecEmployeeManagePanel extends JPanel {
	private JComboBox cbSelection;
	public static JTextField tfSelection;
	private JLabel lblNewLabel;
	private JTextField tfTotalEmployeeNum;
	private JScrollPane scrollPane;
	private JTable innertable;
	public static int sum = 0;
	public static double avg = 0;

	private final DefaultTableModel Outer_Table = new DefaultTableModel();

	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	private JTextField tfEmployeeManageMsg;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnInsert;
	private JButton btnSelection;
	public static String conditionQueryColumn = "";

	/**
	 * Create the panel.
	 */
	public ExecEmployeeManagePanel() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		add(getBtnSelection());
		add(getBtnUpdate());
		add(getBtnInsert());
		add(getBtnDelete());
		add(getCbSelection());
		add(getTfSelection());
		add(getTfTotalEmployeeNum());
		add(getScrollPane());
		add(getTfEmployeeManageMsg());
		add(getLblNewLabel());
		tableInit();
		searchAction();

	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setBounds(0, 11, 92, 30);
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "직원ID", "이름", "직급", "전화번호" }));
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
					ExecEmployeeManagePanel.class.getResource("/com/javalec/image/ExecStoreManagerPanelBrownbar.png")));
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalEmployeeNum() {
		if (tfTotalEmployeeNum == null) {
			tfTotalEmployeeNum = new JTextField();
			tfTotalEmployeeNum.setBounds(5, 61, 116, 21);
			tfTotalEmployeeNum.setBorder(null);
			tfTotalEmployeeNum.setEditable(false);
			tfTotalEmployeeNum.setBackground(new Color(226, 161, 101));
			tfTotalEmployeeNum.setText("");
			tfTotalEmployeeNum.setColumns(10);
		}
		return tfTotalEmployeeNum;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 92, 1025, 626);
			scrollPane.setViewportView(getInnertable());
		}
		return scrollPane;
	}

	private JTable getInnertable() {
		if (innertable == null) {
			innertable = new JTable();
			innertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		innertable.setModel(Outer_Table);
		return innertable;
	}

	// TableInit
	private void tableInit() {

		Outer_Table.addColumn("No");
		Outer_Table.addColumn("직원ID");
		Outer_Table.addColumn("이름");
		Outer_Table.addColumn("직급");
		Outer_Table.addColumn("전화번호");
		Outer_Table.addColumn("주소"); // 5
		Outer_Table.addColumn("이메일");
		Outer_Table.addColumn("입사일");

		Outer_Table.setColumnCount(8); // 몇개인지 알려줘야함.

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF); // 태이블끼리 왓다갓다하면 따른짓을 많이해야해서 끈다.
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		int vColIndex = 0;
		TableColumn col = innertable.getColumnModel().getColumn(vColIndex);
		int width = 40;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 1;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 2;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 3;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 4;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 130;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 5;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 342;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 6;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 170;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

		vColIndex = 7;
		col = innertable.getColumnModel().getColumn(vColIndex);
		width = 110;
		col.setPreferredWidth(width);
		col.setCellRenderer(centerRenderer);

	}

	private void searchAction() {
		DaoEmployeeManage dao = new DaoEmployeeManage();
		ArrayList<DtoEmployeeManage> dtoList = dao.selectList();

		int listCount = dtoList.size();
		tfTotalEmployeeNum.setText("전체: " + listCount + "건");

		for (int index = 0; index < listCount; index++) {
			String wkNo = Integer.toString(index + 1);
			String wkEid = dtoList.get(index).getEid();
			String wkEname = dtoList.get(index).getEname();
			String wkErank = dtoList.get(index).getErank();
			String wkEtelno = dtoList.get(index).getEtelno();
			String wkEaddress = dtoList.get(index).getEaddress();
			String wkEemail = dtoList.get(index).getEemail();
			String wkEindate = dtoList.get(index).getEindate();

			String[] qTxt = { wkNo, wkEid, wkEname, wkErank, wkEtelno, wkEaddress, wkEemail, wkEindate };
			Outer_Table.addRow(qTxt);
		}

	}

	private JTextField getTfEmployeeManageMsg() {
		if (tfEmployeeManageMsg == null) {
			tfEmployeeManageMsg = new JTextField();
			tfEmployeeManageMsg.setText("계좌정보 및 상세정보는 해당 직원을 클릭하여 확인하세요.");
			tfEmployeeManageMsg.setBorder(null);
			tfEmployeeManageMsg.setBackground(new Color(226, 161, 101));
			tfEmployeeManageMsg.setBounds(700, 61, 320, 21);
			tfEmployeeManageMsg.setColumns(10);
		}
		return tfEmployeeManageMsg;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.setBounds(617, 61, 70, 21);
		}
		return btnDelete;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.setBounds(537, 61, 70, 21);
		}
		return btnUpdate;
	}

	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("등록");
			btnInsert.setBounds(457, 61, 70, 21);
		}
		return btnInsert;
	}

	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("");
			btnSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnSelection
					.setIcon(new ImageIcon(ExecEmployeeManagePanel.class.getResource("/com/javalec/image/search.png")));
			btnSelection.setBounds(430, 11, 50, 30);
		}
		return btnSelection;
	}

	private void conditionQuery() { // 콤보박스 결과 값 가져와 테이블 데이터 출력
		int i = cbSelection.getSelectedIndex();
		switch (i) {
		case 0:
			conditionQueryColumn = "eid";
			break;

		case 1:
			conditionQueryColumn = "ename";
			break;

		case 2:
			conditionQueryColumn = "erank";
			break;

		case 3:
			conditionQueryColumn = "etelno";

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
			DaoEmployeeManage dao = new DaoEmployeeManage();
			ArrayList<DtoEmployeeManage> dtoList = dao.selectListConditionQuery();

			int listCount = dtoList.size();
			tfTotalEmployeeNum.setText("전체: " + listCount + "건");

			for (int index = 0; index < listCount; index++) {
				String wkNo = Integer.toString(index + 1);
				String wkEid = dtoList.get(index).getEid();
				String wkEname = dtoList.get(index).getEname();
				String wkErank = dtoList.get(index).getErank();
				String wkEtelno = dtoList.get(index).getEtelno();
				String wkEaddress = dtoList.get(index).getEaddress();
				String wkEemail = dtoList.get(index).getEemail();
				String wkEindate = dtoList.get(index).getEindate();

				String[] qTxt = { wkNo, wkEid, wkEname, wkErank, wkEtelno, wkEaddress, wkEemail, wkEindate };
				Outer_Table.addRow(qTxt);
			}
		}
	}

} // End
