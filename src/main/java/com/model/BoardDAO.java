package com.model;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	int cnt=0;
	PreparedStatement psmt=null;
	Connection conn=null;
	ResultSet rs=null;
	
	
	
	//db����
	public void conn() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String dbid="yjy";
			String dbpw="yjy";
		
			conn=DriverManager.getConnection(url,dbid,dbpw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	//db��������
	public void close() {

		try {
			if(rs!=null) 
				rs.close();
			if(conn!=null) 
				conn.close();
			if(psmt!=null)
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	//��ü �۸���Ʈ �����ٶ�
	public ArrayList<BoardDTO> list() {
		
	
		ArrayList<BoardDTO> list=new ArrayList<BoardDTO>(); 
		conn();
		
		try {
			String sql="select comid,title,id,to_char(comdate,'HH:MM:SS') comdate,visit from community order by comid desc";
			psmt=conn.prepareStatement(sql);
			
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				int comid=rs.getInt("comid");
				String title=rs.getString("title");
			    String id=rs.getString("id");
				String comdate=rs.getString("comdate");
				int visit=rs.getInt("visit");
				BoardDTO dto=new BoardDTO(comid,title,id,comdate,visit);
	
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	public  List<BoardDTO> getlist(){
		
		return getlist("title","",1);
	}
	
	public  List<BoardDTO> getlist(int page){
		
		return getlist("title","",page);
	}
	
	//�˻�
	public List<BoardDTO> getlist(String field/*검색조건*/,String query/*검색어*/,int page){
		
		conn();
		List<BoardDTO> list=new ArrayList<>();
		try {
			String sql=" SELECT * FROM( "+
					 " select rownum num,N.* "+
					" from (select * from community where "+field+" like ? order by comdate desc) N"+
					" )"+
					" WHERE num BETWEEN ? AND ?";
			//1,11,21,31-> an= 1+(page-1)*10
			//10,20,30,40-> page*10
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2, 1+(page-1)*10);
			psmt.setInt(3, page*10);
			
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				int comid=rs.getInt("comid");
				String title=rs.getString("title");
				String content=rs.getString("title");
				String id=rs.getString("id");
				String comdate=rs.getString("comdate");
				int visit=rs.getInt("visit");
			
				
				BoardDTO dto=new BoardDTO(comid,title,content,id,comdate,visit);
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		
		return list;
	}
	
	public int getListCount(){
		
		return getListCount("title","");
	}
	
	public int getListCount(String field,String query){
		int count=0;
		conn();
		try {
			
			String sql=" SELECT count(comid) count FROM( "
					+ " select rownum num,N.* "
					+ " from (select * from community where "+field+" like ? order by comdate desc) "
					+ " )"
					+ " WHERE num BETWEEN 6 AND 10";
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, "%"+query+"%");
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt("count");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return 0;
	}
	
	
	//���� ���� �������
	public BoardDTO getNextNotice(int id){
		
		String sql="select * from community "
				+ "where id= ("
				+ "	select id from community "
				+ "	where comdate>(select comdate from community where comid=?) "
				+ "	and rownum=1 "
				+ ") ";
		return null;
	}
	
	public BoardDTO getPrevNotice(int id){
		
		String sql="select id from (select * from community order by comdate desc) "
				+ "	where comdate < (select comdate from community where comid=59) "
				+ "	and rownum=1";
		
		return null;
	}
	
	
	
	
	//�۵��
	public int write(String title,String content,String id,String pw,String files,String filerealname){
		conn();
		ArrayList<BoardDTO> list=null;
		try {
			String sql="insert into community(comid, title, content, id, pw,files,filerealname,comdate,visit) values(community_seq.nextval,?,?,?,?,?,?,sysdate,0)";
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1,title);
			psmt.setString(2,content );
			psmt.setString(3, id);
			psmt.setString(4, pw);
			psmt.setString(5, files);
			psmt.setString(6, filerealname);
			
			
			cnt=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}

	//��ü���⿡�� �� ���� ������ �󼼺���
	public BoardDTO detail(int comid) {
		
		BoardDTO dto=null;
		conn();
		String sql="";
		try {

			sql="select comid,title,content,id,to_char(comdate,'HH:MM:SS') comdate,visit,files,filerealname from community where comid=?";
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, comid);
			
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				
				int comid1=rs.getInt("comid");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String id=rs.getString("id");
				String comdate=rs.getString("comdate");
				int visit=rs.getInt("visit"); 
				String files=rs.getString("files"); 
				String filerealname=rs.getString("filerealname"); 
				
				dto=new BoardDTO(comid1, title, content, id, comdate, visit,files,filerealname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
		
		
	}

	//�� ����
	public int delete(int comid){
		conn();
	
		try {
			String sql="delete  from community where comid=?";
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, comid);
	
			cnt=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}
	
	//�ۼ���
	public int update(String id,String pw,String title,String content,int comid){
		conn();
	
		try {
			String sql="update community set id=?,pw=?,title=?,content=? where comid=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, title);
			psmt.setString(4, content);
			psmt.setInt(5, comid);
	
			cnt=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}

	//��ȸ�� ����
		public int increase(int comid) {

			conn();
			
			try {
				String sql="update community set visit=visit+1 where comid=?";
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, comid);
				
				cnt=psmt.executeUpdate();
				if(cnt>0) {
					System.out.println("��ȸ�� ���� ����");
					
				}else {
					System.out.println("��ȸ�� 1���� ����");
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
			
			
		}
		
		
		

	
}
