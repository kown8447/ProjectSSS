<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>

<div class="row  col-sm-offset-2">
	<h4>▶&nbsp;건물목록</h4><br> <br>
	
	<div class="col-sm-10">
		<a href="registerbuilding.htm" style="float: right;" class="btn btn-success">건물등록</a>
		<table class="table table-hover" style="text-align: center" >
			<thead>
				<tr>
					<th style="text-align: center">건물코드</th>
					<th style="text-align: center">건물명</th>
					<th style="text-align: center">주소</th>
				</tr>
			</thead>
			<c:forEach items="${building}" var="bd" varStatus="index">
				<tbody>
					<tr>
						<td id="code${index.count}">${bd.building_code}</td>
						<td><a
							href="buildingDetail.htm?building_code=${bd.building_code}">${bd.building_name}</a></td>
						<td>${bd.building_addr}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>