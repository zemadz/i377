<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Part 3</title>

<style type="text/css">
<!--
@import url("${pageContext.request.contextPath}/static/style.css");
-->
</style>
</head>




<body>


<jsp:include page="menu.jsp" />

<br /><br /><br />


  
  <form method="post" action="Add">

    <table class="formTable" id="formTable">
      <tbody>
        <tr>
          <td>Nimi:</td>
          <td><input name="name" id="nameBox" /></td>
        </tr>
        <tr>
          <td>Kood:</td>
          <td><input name="code" id="codeBox"/></td>
        </tr>
        <tr>
          <td colspan="2" align="right"><br/>
            <input type="submit" value="Lisa" id="addButton"/>
          </td>
        </tr>
      </tbody>
    </table>
  </form>
</body>
</html>