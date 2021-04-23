package com.model;

public class BoardDTO {
	
	private String category;
	private String title;
	private String content;
	private String id;
	private String pw;
	private String comdate;
	private int visit;
	private String files;
	private String filerealname;
	
	private int comid;
	
	
	
	public BoardDTO(int comid,String title, String content, String id, String comdate, int visit) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
		this.comdate = comdate;
		this.visit = visit;
		this.comid = comid;
	}

 //5
	public BoardDTO(int comid, String title, String id, String comdate, int visit) {
		super();
		this.comid = comid;
		this.title = title;
		this.id = id;
		this.comdate = comdate;
		this.visit = visit;
	}
//6
	public BoardDTO(String title, String content, String id, String pw,String files,String filerealname) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
		this.pw = pw;
		this.files=files;
		this.filerealname=filerealname;
		
	}
	public String getFiles() {
		return files;
	}
	
	public void setFiles(String files) {
		this.files = files;
	}
	
	public String getFilerealname() {
		return filerealname;
	}
	
	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}
//6
	public BoardDTO(int comid, String title, String id, String comdate, int visit,String file ){
		super();
		this.comid = comid;
		this.title = title;
		this.id = id;
		this.comdate = comdate;
		this.visit = visit;
		this.files=files;	
	}

	//8
	public BoardDTO(int comid, String title, String content, String id,  String comdate,
			int visit, String files,String filerealname) {
		super();
		this.comid = comid;
		this.title = title;
		this.content = content;
		this.id = id;
		this.comdate = comdate;
		this.visit = visit;
		this.files = files;
		this.filerealname=filerealname;
	}
	

	public int getComid() {
		return comid;
	}
	public void setComid(int comid) {
		this.comid = comid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	public String getComdate() {
		return comdate;
	}
	public void setComdate(String comdate) {
		this.comdate = comdate;
	}

	@Override
	public String toString() {
		return "BoardDTO [category=" + category + ", title=" + title + ", content=" + content + ", id=" + id + ", pw="
				+ pw + ", comdate=" + comdate + ", visit=" + visit + ", files=" + files + ", filerealname=" + filerealname
				+ ", comid=" + comid + "]";
	}

	
	
}
