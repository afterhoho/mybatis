package com.oracle.oBootBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.oBootBoard.dto.BDto;

public class JdbcDao implements BDao {

	// JDBC 사용
	private final DataSource dataSource;

	public JdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("BDao boardList start ...");
		try {
			String query = "select * " + "From mvc_board order by bGroup desc,bStep asc ";
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			System.out.println("BDao query" + query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {

					BDto bDto = new BDto();
					bDto.setbId(rs.getInt("bId"));
					bDto.setbName(rs.getString("bName"));
					bDto.setbTitle(rs.getString("bTitle"));
					bDto.setbContent(rs.getNString("bContent"));
					bDto.setbDate(rs.getTimestamp("bDate"));
					bDto.setbHit(rs.getInt("bHit"));
					bDto.setbGroup(rs.getInt("bGroup"));
					bDto.setbStep(rs.getInt("bStep"));
					bDto.setbIndent(rs.getInt("bIndent"));
					bList.add(bDto);

				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("list dataSource" + e.getMessage());
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

		return bList;
	}

	@Override
	public void write(String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("BDao write start");
		try {// 1.Insert Into mvc_board
			String sql = "insert into mvc_board values(mvc_board_seq.nextval,?,?,?,sysdate,?,mvc_board_seq.nextval,0,0)";
			System.out.println("sql-->" + sql);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, new BDto().getbHit());
			System.out.println("pstmt bName->" + bName);
			System.out.println("pstmt bTitle->" + bTitle);
			System.out.println("pstmt bContent->" + bContent);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			close(conn, pstmt, rs);
		}

		// 2. prepareStatement
		// 3. mvc_board_seq
		// 4. bId , bGroup 같게
		// 5. bStep, bIndent, bDate --> 0, 0 , sysdate
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}

	@Override
	public BDto contentView(int bId) {
		upHit(bId);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto bDto = new BDto();
		try {
			conn = getConnection();

			String sql = "select bId,bHit,bName,bTitle,bContent from mvc_board where bId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bDto.setbId(bId);
				bDto.setbHit(rs.getInt("bHit"));
				bDto.setbName(rs.getString("bName"));
				bDto.setbTitle(rs.getString("bTitle"));
				bDto.setbContent(rs.getString("bContent"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			close(conn, pstmt, rs);
		}
		return bDto;
	}

	// 게시글 클릭하면 조회수 증가
	private void upHit(int bId) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update mvc_board set bHit = bHit+1 where bId=?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("JdbcDao upHit Query : " + sql);

			pstmt.setInt(1, bId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	// 수정
	public void modify(int bId, String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update mvc_board set bName=?,bTitle=?,bContent=? where bId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bId);
			int rn = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			close(conn, pstmt, null);
		}

	}

	 // reply_view
	 // 댓글 입력 창
	   @Override
	   public BDto reply_view(int bId) {

	      BDto dto = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql = "select * from mvc_board where bId=?";
	      
	      try {
	         
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         System.out.println("JdbcDao reply_view Query : " + sql);
	         
	         pstmt.setInt(1, bId);
	         
	         rs = pstmt.executeQuery();
	         
	         if (rs.next()) {
	            
	            int sbId = rs.getInt("bId");
	            int bHit = rs.getInt("bHit");
	            int bGroup = rs.getInt("bGroup");
	            int bStep = rs.getInt("bStep");
	            int bIndent = rs.getInt("bIndent");
	            String bName = rs.getString("bName");
	            String bTitle = rs.getString("bTitle");
	            String bContent = rs.getString("bContent");
	            Timestamp bDate = rs.getTimestamp("bDate");
	            
	            dto = new BDto(sbId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
	            
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         
	         try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	      }
	      
	      return dto;
	      
	   }
	   
	@Override
	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {
		// 홍해 기적
		replyShape(bGroup,bStep);
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert into mvc_board(bId,bName,bTitle,bContent,"
					+ "bGroup,bStep,bIndent)"
					+ " values(mvc_board_seq.nextval,?,?,?,"
					+ "?,?,?)";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bGroup);
			pstmt.setInt(5,bStep+1);
			pstmt.setInt(6, bIndent+1);
			
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			close(conn, pstmt, null);
		}
	}

	private void replyShape(int bGroup, int bStep) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		try {
			conn=getConnection();
			String sql = "update mvc_board set bStep = bStep+1"
						+ "where bGroup = ? and bStep >?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			close(conn, pstmt, null);
		}
		
	}

	@Override
	public void delete(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			String sql ="delete from mvc_board where bId=?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			close(conn, pstmt, null);
		}
		 
	}




}
