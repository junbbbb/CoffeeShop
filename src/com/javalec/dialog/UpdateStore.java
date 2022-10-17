package com.javalec.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoStoreManage;
import com.javalec.dto.DtoStoreManage;
import com.javalec.util.DBConnect;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateStore extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbSelection;
	public static JTextField tfSelection;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JLabel lblStoreseq2;
	private JLabel lblSname;
	private JLabel lblSaddress;
	private JLabel lblStelno;
	private JLabel lblEid;
	private JLabel lblEname;
	private JLabel lblSopendate;
	private JLabel lblScrn;
	private JButton btnDelete;
	private JTextField tfStoreseq2;
	private JTextField tfSname;
	private JTextField tfSaddress;
	private JTextField tfStelno;
	private JTextField tfEid;
	private JTextField tfEname;
	private JTextField tfSopendate;
	private JTextField tfScrn;
	private JButton btnSelection;
	public static String conditionQueryColumn = "";
	private JTextField tfTotalResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateStore dialog = new UpdateStore();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateStore() {
		setBounds(100, 100, 1048, 551);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getCbSelection());
		contentPanel.add(getTfSelection());
		contentPanel.add(getScrollPane());
		contentPanel.add(getLblStoreseq2());
		contentPanel.add(getLblSname());
		contentPanel.add(getLblSaddress());
		contentPanel.add(getLblStelno());
		contentPanel.add(getLblEid());
		contentPanel.add(getLblEname());
		contentPanel.add(getLblSopendate());
		contentPanel.add(getLblScrn());
		contentPanel.add(getBtnDelete());
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				updateAction();
				tableInit();
				clearColumn();
			}
		});
		btnUpdate.setBounds(857, 471, 72, 23);
		contentPanel.add(btnUpdate);
		btnUpdate.setActionCommand("OK");
		getRootPane().setDefaultButton(btnUpdate);
		contentPanel.add(getTfStoreseq2());
		contentPanel.add(getTfSname());
		contentPanel.add(getTfSaddress());
		contentPanel.add(getTfStelno());
		contentPanel.add(getTfEid());
		contentPanel.add(getTfEname());
		contentPanel.add(getTfSopendate());
		contentPanel.add(getTfScrn());
		contentPanel.add(getBtnSelection());
		contentPanel.add(getTfTotalResult());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				tableInit();
			}
		}
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setBounds(22, 25, 90, 23);
			cbSelection.setModel(new DefaultComboBoxModel(new String[] { "매장코드", "매장명", "주소", "지점장명" }));
		}
		return cbSelection;
	}

	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(124, 26, 198, 23);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 73, 988, 151);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) { // 1 is left button
						tableClick();
					}
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		innerTable.setModel(Outer_Table);
		return innerTable;
	}

	private void tableInit() {

		Outer_Table.addColumn("매장코드");
		Outer_Table.addColumn("매장명");
		Outer_Table.addColumn("주소");
		Outer_Table.addColumn("전화번호");
		Outer_Table.addColumn("지점장ID"); // 5
		Outer_Table.addColumn("지점장명");
		Outer_Table.addColumn("개업일");
		Outer_Table.addColumn("사업자등록번호");

		Outer_Table.setColumnCount(8); // 몇개인지 알려줘야함.

		int i = Outer_Table.getRowCount(); // 현재 몇줄이냐? 중간 삭제 변경 일때 위에있는 모양이 바뀌니깐. 기존에 있는걸 지울려고 가져옴
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0); // 하나씩 떙겨오면서 지운다.
		}

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF); // 태이블끼리 왓다갓다하면 따른짓을 많이해야해서 끈다.

		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 70;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 280;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 125;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);

		vColIndex = 5;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);

		vColIndex = 6;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 110;
		col.setPreferredWidth(width);

		vColIndex = 7;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 140;
		col.setPreferredWidth(width);

	}

	private JLabel getLblStoreseq2() {
		if (lblStoreseq2 == null) {
			lblStoreseq2 = new JLabel("매장코드");
			lblStoreseq2.setFont(new Font("Gulim", Font.BOLD, 14));
			lblStoreseq2.setBounds(22, 240, 72, 17);
		}
		return lblStoreseq2;
	}

	private JLabel getLblSname() {
		if (lblSname == null) {
			lblSname = new JLabel("매장명");
			lblSname.setFont(new Font("Gulim", Font.BOLD, 14));
			lblSname.setBounds(22, 270, 72, 17);
		}
		return lblSname;
	}

	private JLabel getLblSaddress() {
		if (lblSaddress == null) {
			lblSaddress = new JLabel("주소");
			lblSaddress.setFont(new Font("Gulim", Font.BOLD, 14));
			lblSaddress.setBounds(22, 300, 72, 17);
		}
		return lblSaddress;
	}

	private JLabel getLblStelno() {
		if (lblStelno == null) {
			lblStelno = new JLabel("전화번호");
			lblStelno.setFont(new Font("Gulim", Font.BOLD, 14));
			lblStelno.setBounds(22, 330, 72, 17);
		}
		return lblStelno;
	}

	private JLabel getLblEid() {
		if (lblEid == null) {
			lblEid = new JLabel("지점장ID");
			lblEid.setFont(new Font("Gulim", Font.BOLD, 14));
			lblEid.setBounds(22, 360, 72, 17);
		}
		return lblEid;
	}

	private JLabel getLblEname() {
		if (lblEname == null) {
			lblEname = new JLabel("지점장명");
			lblEname.setFont(new Font("Gulim", Font.BOLD, 14));
			lblEname.setBounds(22, 390, 72, 17);
		}
		return lblEname;
	}

	private JLabel getLblSopendate() {
		if (lblSopendate == null) {
			lblSopendate = new JLabel("개업일");
			lblSopendate.setFont(new Font("Gulim", Font.BOLD, 14));
			lblSopendate.setBounds(22, 420, 72, 17);
		}
		return lblSopendate;
	}

	private JLabel getLblScrn() {
		if (lblScrn == null) {
			lblScrn = new JLabel("사업자등록번호");
			lblScrn.setFont(new Font("Gulim", Font.BOLD, 14));
			lblScrn.setBounds(22, 450, 109, 17);
		}
		return lblScrn;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteAction();
					tableInit();
					clearColumn();
				}
			});
			btnDelete.setBounds(941, 471, 69, 23);
			btnDelete.setActionCommand("OK");
		}
		return btnDelete;
	}

	private JTextField getTfStoreseq2() {
		if (tfStoreseq2 == null) {
			tfStoreseq2 = new JTextField();
			tfStoreseq2.setEditable(false);
			tfStoreseq2.setBounds(142, 234, 90, 21);
			tfStoreseq2.setColumns(10);
		}
		return tfStoreseq2;
	}

	private JTextField getTfSname() {
		if (tfSname == null) {
			tfSname = new JTextField();
			tfSname.setColumns(10);
			tfSname.setBounds(142, 266, 118, 21);
		}
		return tfSname;
	}

	private JTextField getTfSaddress() {
		if (tfSaddress == null) {
			tfSaddress = new JTextField();
			tfSaddress.setColumns(10);
			tfSaddress.setBounds(142, 296, 326, 21);
		}
		return tfSaddress;
	}

	private JTextField getTfStelno() {
		if (tfStelno == null) {
			tfStelno = new JTextField();
			tfStelno.setColumns(10);
			tfStelno.setBounds(142, 326, 141, 21);
		}
		return tfStelno;
	}

	private JTextField getTfEid() {
		if (tfEid == null) {
			tfEid = new JTextField();
			tfEid.setColumns(10);
			tfEid.setBounds(142, 356, 90, 21);
		}
		return tfEid;
	}

	private JTextField getTfEname() {
		if (tfEname == null) {
			tfEname = new JTextField();
			tfEname.setColumns(10);
			tfEname.setBounds(142, 386, 141, 21);
		}
		return tfEname;
	}

	private JTextField getTfSopendate() {
		if (tfSopendate == null) {
			tfSopendate = new JTextField();
			tfSopendate.setColumns(10);
			tfSopendate.setBounds(142, 418, 118, 21);
		}
		return tfSopendate;
	}

	private JTextField getTfScrn() {
		if (tfScrn == null) {
			tfScrn = new JTextField();
			tfScrn.setColumns(10);
			tfScrn.setBounds(143, 448, 179, 21);
		}
		return tfScrn;
	}

	private void tableClick() {
		int i = innerTable.getSelectedRow(); // 몇 번 줄이야?
		String wkSequence = (String) innerTable.getValueAt(i, 0);// i번행의 0번 열?

		String whereStatement = "select storeseq2, sname, saddress, stelno, eid, ename, sopendate, scrn from store ";
		String whereStatement2 = "where storeseq2 = '" + wkSequence + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); // 검색용

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			if (rs.next()) {
				tfStoreseq2.setText(rs.getString(1));
				tfSname.setText(rs.getString(2));
				tfSaddress.setText(rs.getString(3));
				tfStelno.setText(rs.getString(4));
				tfEid.setText(rs.getString(5));
				tfEname.setText(rs.getString(6));
				tfSopendate.setText(rs.getString(7));
				tfScrn.setText(rs.getString(8));

			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("검색");
			btnSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnSelection.setBounds(334, 25, 77, 23);
		}
		return btnSelection;
	}

	private void conditionQuery() { // 콤보박스 결과 값 가져와 테이블 데이터 출력
		int i = cbSelection.getSelectedIndex();
		switch (i) {
		case 0:
			conditionQueryColumn = "storeseq2";
			break;

		case 1:
			conditionQueryColumn = "sname";
			break;

		case 2:
			conditionQueryColumn = "saddress";
			break;

		case 3:
			conditionQueryColumn = "ename";

		default:
			break;

		}

		tableInit();
		conditionQueryAction(conditionQueryColumn);

	}

	private void conditionQueryAction(String conditionQueryColumn) { // 조건 검색

		DaoStoreManage dao = new DaoStoreManage();
		ArrayList<DtoStoreManage> dtoList = dao.selectListUpdate();

		int listCount = dtoList.size();
		tfTotalResult.setText("전체: " + listCount + "건");

		for (int index = 0; index < listCount; index++) {
			String wkStoreseq2 = dtoList.get(index).getStoreseq2();
			String wkSname = dtoList.get(index).getSname();
			String wkSaddress = dtoList.get(index).getSaddress();
			String wkStelno = dtoList.get(index).getStelno();
			String wkEid = dtoList.get(index).getEid();
			String wkEname = dtoList.get(index).getEname();
			String wkSopendate = dtoList.get(index).getSopendate();
			String wkScrn = dtoList.get(index).getScrn();
			String[] qTxt = { wkStoreseq2, wkSname, wkSaddress, wkStelno, wkEid, wkEname, wkSopendate, wkScrn };
			Outer_Table.addRow(qTxt);
		}
	}

	private JTextField getTfTotalResult() {
		if (tfTotalResult == null) {
			tfTotalResult = new JTextField();
			tfTotalResult.setEditable(false);
			tfTotalResult.setBounds(423, 26, 77, 21);
			tfTotalResult.setColumns(10);
		}
		return tfTotalResult;
	}

	private void clearColumn() { // 화면 나머지 지우기
		tfStoreseq2.setText("");
		tfSname.setText("");
		tfSaddress.setText("");
		tfStelno.setText("");
		tfEid.setText("");
		tfEname.setText("");
		tfScrn.setText("");
		tfSopendate.setText("");
	}

	private void updateAction() { // insert, delete update have same format
		PreparedStatement ps = null; // sql 코드 에러 등
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "update store set sname = ?, saddress = ?, stelno = ?, eid = ?, ename = ?, scrn = ?, sopendate = ?";
			String query2 = "where storeseq2 =?";
			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1, tfSname.getText().trim());
			ps.setString(2, tfSaddress.getText().trim());
			ps.setString(3, tfStelno.getText().trim());
			ps.setString(4, tfEid.getText().trim());
			ps.setString(5, tfEname.getText().trim());
			ps.setString(6, tfScrn.getText().trim());
			ps.setString(7, tfSopendate.getText().trim());
			ps.setString(8, tfStoreseq2.getText());

			ps.executeUpdate(); // 실행, 1이면 정상

			conn_mysql.close(); // 연결 종료해줘야 다른 사람 들어감, 주로 5명 제한

			JOptionPane.showMessageDialog(null, tfSname.getText() + "의 정보가 수정 되었습니다.!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteAction() {
		PreparedStatement ps = null; // sql 코드 에러 등
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "delete from store where storeseq2 = ? ";

			ps = conn_mysql.prepareStatement(query);
			ps.setString(1, tfStoreseq2.getText());

			ps.executeUpdate();

			conn_mysql.close();

			JOptionPane.showMessageDialog(null, tfStoreseq2.getText() + "의 정보가 삭제 되었습니다.!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

} // End
