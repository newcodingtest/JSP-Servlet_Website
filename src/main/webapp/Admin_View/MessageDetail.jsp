<%@page import="com.model.BoardDAO"%>
<%@page import="com.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>�� �󼼺���</title>
</head>
<style>
    .title{
        text-align: center;
        width: 100px;
    }
    .article{
        text-align: left;
        width: 500px;

    }
    button{
        width:100px;
        height:50px;
    }
</style>
<body >
<%


%>
    <h1>���� �󼼺���</h1>
    
    <table border=1>
      <tr>
        <td class="title">�۹�ȣ</td>
        <td class="article" height=50px>1</td>
    </tr>
    <tr>
        <td class="title">����</td>
        <td class="article" height=50px>����</td>
    </tr>
    <tr>
        <td class="title">�ۼ���</td><td>�ۼ���</td>
    </tr>
    <tr>
        <td class="title">�ۼ��ð�</td>
        <td class="article" height=50px>�ۼ��ð�</td>
    </tr>
    <tr >
        <td class="title">����</td>
        <td class="article" height=200px> ${bean.content}<img src="${file}" width=512 height=384></img></td>
    </tr>
      <tr >
        <td class="title">��ȸ��</td>
        <td class="article" height=200px>�ۼ��ð�</td>
    </tr>
   
    </table>
 
	<br>
    <a href="update.jsp?comid=${bean.comid}" class="btn btn-default">����</a> <br>
    <a href="delete?comid=${bean.comid}" class="btn btn-default">����</a> <br>
    <a href="List.jsp">�������</a> <br>

    
    
    
    <table border=1>
    <!---------------------------------------->
    <tr>
        <td class="title">�ۼ���</td>
        <td class="article" height=50px> "��� ���� �ҷ����� ��"</td>
    </tr>
    
    </table>
    <!----�̺κ� for������ �ݺ��ؼ� ��� ǥ�� �ϸ� �ɵ�-->
    
    <div>
    <div>
    <textarea cols="70" rows="10"></textarea>
        <!-- �ִ� �Է°����� ����ŭ cols x rows�� ���߼� -->
    </div>
    
    <div>
    <button>��� �ۼ�</button><input type="text" placeholder="�ӽ� ��й�ȣ">
    </div>
        
        
    </div>
</body>
</html>