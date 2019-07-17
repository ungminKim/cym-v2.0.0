package com.cym.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.cym.dto.CymDto;
import com.cym.dto.CymDtoList;

import javax.naming.Context;
import javax.naming.InitialContext;

public class CymDao {

	public static final int MEMBER_NONEXISTENT  = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	DataSource dataSource;
	
	private static CymDao instance = new CymDao();
	public static CymDao getInstance() {
		return instance;
	}
	public CymDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle19c");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int insertMember(CymDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "insert into cmTable values(?, ?, ?)";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.executeUpdate();
			ri = CymDao.MEMBER_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from cmTable where id = ?";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = CymDao.MEMBER_EXISTENT;
			} else {
				ri = CymDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck( String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from cmTable where id = ?";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					ri = CymDao.MEMBER_LOGIN_SUCCESS;				// 로그인 Ok
				} else {
					ri = CymDao.MEMBER_LOGIN_PW_NO_GOOD;		// 비번 X
				}
			} else {
				ri = CymDao.MEMBER_LOGIN_IS_NOT;		// 회원 X	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public CymDto getMember(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from cmTable where id = ?";
		CymDto dto = null;
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new CymDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(CymDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "update cmTable set pw=?, where id=?";
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getId());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public void addList(CymDtoList dtolist) {
		
		Connection cn = null;
		PreparedStatement pstmt = null;
		String query = "insert into toDoList values(?, ?)";
		
		try {
			cn = dataSource.getConnection();
			pstmt = cn.prepareStatement(query);
			pstmt.setString(1, dtolist.getList());
			pstmt.setString(2, dtolist.getName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) cn.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<CymDtoList> makeList() {
		
		ArrayList<CymDtoList> arrayList = new ArrayList<CymDtoList>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from toDoList";
			pstmt = connection.prepareStatement(query);
			set = pstmt.executeQuery();
			
			while(set.next()) {
				CymDtoList dtolist = new CymDtoList(); 
				dtolist.setList(set.getString("list"));
				dtolist.setName(set.getString("name"));
				
				arrayList.add(dtolist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return arrayList;
		
	}
	
	public void deleteList(String name) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		String query = "delete from toDoList where name = ?";
		
		try {
			cn = dataSource.getConnection();
			pstmt = cn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(cn != null) cn.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

