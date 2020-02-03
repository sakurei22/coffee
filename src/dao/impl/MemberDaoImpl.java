package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.MemberDao;
import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

//DB와 대화하는 클래스 ( database server )
public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean selectId(String id) {
		String sql = " SELECT ID FROM MEMBER WHERE ID = ?";
		boolean findId = true;
		Connection conn = DBConnection.getConnection(); // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;							//RESULT
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				findId = false; //데이터가 있을 때
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return findId;
	}

	@Override
	public boolean addMember(MemberDto dto) {
		//db에 추가 작업
				String sql = " INSERT INTO MEMBER "
						+ " VALUES (?, ?, ?, ?, ?)";
				
				Connection conn = DBConnection.getConnection(); // DB Connection
				PreparedStatement psmt = null;					// SQL
				ResultSet rs = null;							//RESULT
				
				int count = 0;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, dto.getId()); // db는 1부터 시작, 위의 ?에 대입
					psmt.setString(2, dto.getPwd());
					psmt.setString(3, dto.getName());
					psmt.setString(4, dto.getEmail());
					psmt.setInt(5, dto.getAuth());
					
					System.out.println("sql : " + sql);
					count = psmt.executeUpdate();
					
					if(count > 0) {
						System.out.println("입력성공");
						
					} else {
						System.out.println("입력실패");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBClose.close(psmt, conn, rs);
				}
				
				return count > 0 ? true : false;
	}

	@Override
	public MemberDto loginMember(String id, String pwd) {

		MemberDto dto = null;		
		String sql = " SELECT ID, PWD, NAME, EMAIL, AUTH FROM MEMBER WHERE ID = ?";
		
		Connection conn = DBConnection.getConnection(); // DB Connection
		PreparedStatement psmt = null;					// SQL
		ResultSet rs = null;							//RESULT
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id); // db는 1부터 시작, 위의 ?에 대입		
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				if(rs.getString("pwd").equals(pwd)) {				
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setAuth(rs.getInt("auth"));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}
}
