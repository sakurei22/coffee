package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CoffeeDao;
import db.DBClose;
import db.DBConnection;
import dto.CoffeeDto;
import dto.HistoryDto;
import dto.SelectOrderDto;

public class CoffeeDaoImple implements CoffeeDao {

	@Override
	public List<CoffeeDto> getCoffeeList(){
		String sql = " SELECT * FROM COFFEE ";
		
		Connection conn = null ; // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;							//RESULT
		
		List<CoffeeDto> list = new ArrayList<CoffeeDto>();
	
		try {
			conn = DBConnection.getConnection(); // connection 안에 rollback있음
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CoffeeDto dto = new CoffeeDto(rs.getInt(1),//ID, 
										rs.getString(2),//NAME, 
										rs.getInt(3),//SHORT, 
										rs.getInt(4),//TALL, 
										rs.getInt(5),//GRANDE, 
										rs.getInt(6)//AUTH
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}

	@Override
	public void addOrderNumber(String id) {
		String sql = " INSERT INTO ORDERCOFFEE VALUES (SEQ_ORDER.NEXTVAL, ?, SYSDATE)";
		
		Connection conn = null ; // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;							//RESULT
		
		int count = 0;
	
		try {
			conn = DBConnection.getConnection(); // connection 안에 rollback있음
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id); // db는 1부터 시작, 위의 ?에 대입
			
			System.out.println("sql : " + sql);
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(count > 0) {
				System.out.println("입력성공");
				
			} else {
				System.out.println("입력실패");
			}
			
		} finally {
			DBClose.close(psmt, conn, rs);
		}
	}

	@Override
	public boolean addOrderList(List<SelectOrderDto> orderList, int orderNum) {
		int count = 0;

									//RESULT
		for (int i = 0; i < orderList.size(); i++) {
			Connection conn = DBConnection.getConnection(); // DB Connection
			PreparedStatement psmt = null;	
			ResultSet rs = null;
			SelectOrderDto dto = orderList.get(i);
			String sql = " INSERT INTO ORDER_LIST VALUES (SEQ_CF_ORDER.NEXTVAL, ?, "
					+ " ?, ?, ?, ?, ? ,?, ? )";

			
		
			try {

				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, orderNum); // ordernum
				psmt.setInt(2, dto.getCfId()); // CFID
				psmt.setInt(3, dto.getcSyrup()); // C_SYRUP
				psmt.setInt(4, dto.getcSize()); // C_SIZE
				psmt.setInt(5, dto.getcShot()); // C_SHOT
				psmt.setInt(6, dto.getcWhip()); // C_WHIP
				psmt.setInt(7, dto.getcCup()); // C_CUP
				psmt.setInt(8, dto.getcPrice()); // C_PRICE
				
				System.out.println("sql : " + sql);
				count = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(count > 0) {
					System.out.println("입력성공");
					
				} else {
					System.out.println("입력실패");
				}
				
			} finally {
				DBClose.close(psmt, conn, rs);
			}
		}
		
		return count > 0 ? true : false;
	}

	@Override
	public int selectOrderNum() {
		String sql = " SELECT ORDERNUM " + 
				"	FROM( " + 
				"		SELECT ROWNUM AS RNUM, ORDERNUM " + 
				"			FROM " + 
				"		   ( SELECT * FROM ORDERCOFFEE " + 
				"				ORDER BY ORDERDATE DESC) " + 
				"		) " + 
				" WHERE RNUM = 1 ";
		
		Connection conn = null ; // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;	
		int orderNum = 0;
		try {
			conn = DBConnection.getConnection(); // connection 안에 rollback있음
			System.out.println("sql : " + sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				orderNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return orderNum;
	}

	@Override
	public List<HistoryDto> getHistoryCoffeeList(String id) {
		String sql = " Select l.seq, l.ordernum, l.cfid, l.c_syrup, l.c_size, l.c_shot, l.c_whip, l.c_cup, "
				+ " l.c_price, TO_CHAR(c.orderdate, 'YYYY-MM-DD HH:MI:SS') from " + 
				" ORDER_LIST l, ORDERCOFFEE c " + 
				" WHERE l.ordernum = c.ordernum " + 
				"	and c.ORDERNUM IN (select ORDERNUM from ORDERCOFFEE  " + 
				"					WHERE MEMBERID = ? ) " + 
				" ORDER BY c.ORDERNUM DESC ";
		
		Connection conn = null ; // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;							//RESULT
		
		List<HistoryDto> list = new ArrayList<HistoryDto>();
	
		try {
			conn = DBConnection.getConnection(); // connection 안에 rollback있음
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, id);
			rs = psmt.executeQuery();
	
			while(rs.next()) { 
				HistoryDto dto = new HistoryDto(rs.getInt(1),//seq
										rs.getInt(2),//orderNum, 
										rs.getInt(3),//cfId, 
										rs.getInt(4),//cSyrup, 
										rs.getInt(5),//cSize, 
										rs.getInt(6),// shot
										rs.getInt(7), // whip
										rs.getInt(8),//cup
										rs.getInt(9),//price
										rs.getString(10) //date
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
}
