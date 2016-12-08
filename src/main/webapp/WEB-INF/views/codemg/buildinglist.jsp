<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width: 75%">
	<h4>▶&nbsp;건물 리스트</h4>
	<br> <br>
	<div class="container" style="width: 95%">
		<table class="table" style="text-align: center">
			<thead>
				<tr>
					<td>건물코드</td>
					<td>건물명</td>
					<td>주소</td>
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
		<a href="registerbuilding.htm"><button style="float: right;"
				class="btn btn-danger">되돌아가기</button></a>
	</div>
</div>