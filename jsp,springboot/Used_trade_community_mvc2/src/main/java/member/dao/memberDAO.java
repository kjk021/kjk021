package member.dao;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.dto.memberDTO;

public class memberDAO {

	private static memberDAO instance = new memberDAO();
    
    public static memberDAO getInstance() {
        return instance;
    }
    
    private memberDAO() { }
	
	private Connection getConnection() throws Exception {
	    Context initCtx = new InitialContext();
	    Context envCtx = (Context) initCtx.lookup("java:comp/env");
	    DataSource ds = (DataSource)envCtx.lookup("jdbc/basicjsp");
	    return ds.getConnection();
	}
	
	// 회원가입 메서드
    public void insertMember(memberDTO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "INSERT INTO member (mem_num, name, id, passwd, nickname, email, phone, RRN, ranks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, member.getMem_num());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getId());
            pstmt.setString(4, member.getPasswd());
            pstmt.setString(5, member.getNickname());
            pstmt.setString(6, member.getEmail());
            pstmt.setString(7, member.getPhone());
            pstmt.setString(8, member.getRRN());
            pstmt.setInt(9, member.getRanks());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 로그인 메서드
    public int login(String id, String passwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;

        try {
            conn = getConnection();

            String sql = "SELECT passwd, ranks FROM member WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPasswd = rs.getString("passwd");
                int ranks = rs.getInt("ranks");
                
                if (dbPasswd.equals(passwd)) {
                    // 비밀번호가 일치하는 경우
                    if (ranks == 0) {
                        // 관리자 로그인
                        result = 1;
                    } else if (ranks == 1) {
                        // 일반 사용자 로그인
                        result = 2;
                    }
                } else {
                    // 비밀번호가 일치하지 않는 경우
                    result = 4;
                }
            } else {
                // 아이디가 존재하지 않는 경우
                result = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
    
    
    // 회원 정보를 가져오는 메서드
    public memberDTO getMemberInfo(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        memberDTO member = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM member WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new memberDTO();
                member.setId(rs.getString("id"));
                member.setPasswd(rs.getString("passwd"));
                member.setNickname(rs.getString("nickname"));
                member.setMem_num(rs.getInt("mem_num"));
                member.setEmail(rs.getString("email"));
                // 필요한 다른 정보들도 필요한 만큼 추가해주세요.
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return member;
    }
   
    
}
	

