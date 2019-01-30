<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String bigtabon="1";
%>
<link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
<script src="semantic/dist/semantic.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%@ include file="../include/common.jsp" %>
<title> 나눔두리 마이페이지</title>
<%@ include file="../../common/css.jsp" %>
<style>
.mb50 {
     margin-bottom: 0px !important; 
}
#myTitle{
	margin-bottom: 10px;
	font-size:20px;
	color: black;
}
</style>
</head>
<body>
<%@ include file="../../common/navi.jsp" %>
<%@ include file="../include/header.jsp" %>
<!-- #wrap {position:relative; width:100%;} -->
	<div id="wrap">		
			<div class="contBox inner">
				<%@ include file="../include/tabMypage.jsp"%>
				<br><br>
				
				<div id="myTitle">진행중인 크라우드 펀딩</div>
				<table class="ui orange table">
				  <thead>
				    <tr><th>Food</th>
				    <th>Calories</th>
				    <th>Protein</th>
				  </tr></thead><tbody>
				    <tr>
				      <td>Apples</td>
				      <td>200</td>
				      <td>0g</td>
				    </tr>
				    <tr>
				      <td>Orange</td>
				      <td>310</td>
				      <td>0g</td>
				    </tr>
				  </tbody>
				</table>	
				<br><br><br><br>
				
				<div id="myTitle">종료된 크라우드 펀딩</div>
				<table class="ui orange table">
				  <thead>
				    <tr><th>Food</th>
				    <th>Calories</th>
				    <th>Protein</th>
				  </tr></thead><tbody>
				    <tr>
				      <td>Apples</td>
				      <td>200</td>
				      <td>0g</td>
				    </tr>
				    <tr>
				      <td>Orange</td>
				      <td>310</td>
				      <td>0g</td>
				    </tr>
				  </tbody>
				</table>				
			</div>								
	</div>		
			
			
	

	<%@ include file="../include/myNav.jsp" %>

</body>
</html>