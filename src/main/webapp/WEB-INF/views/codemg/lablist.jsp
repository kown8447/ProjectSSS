<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>  
<div class="row  col-sm-offset-2">
	<h4>▶&nbsp;연구실 목록 </h4><br><br>

	<div class="col-sm-10">
	 	<a href="registerlaboratory.htm" style="float:right;" class="btn btn-success">연구실 등록</a>
	 	<br><br><br>
		<table class="table table-bordered" style="text-align: center" >
			<thead>
				<tr class="active">
					<th style="text-align: center">교수연구실코드</th>
					<th style="text-align: center">건물코드</th>
					<th style="text-align: center">연구실 이름</th>
					<th style="text-align: center">전화번호</th>
					<th style="text-align: center">사용가능 여부</th>
				</tr>
			</thead>
			<c:forEach items="${lablist}" var="lab" varStatus="index">
				<tbody>
					<tr>
						<td>${lab.lab_code}</td>
						<td>${lab.building_name}</td>
						<td><a href="labDetail.htm?lab_code=${lab.lab_code}">${lab.lab_name}</a>
						</td>
						<td>${lab.lab_phone}</td>
						<td><c:choose>
								<c:when test="${lab.lab_possible==0}">
                     사용가능
                  </c:when>
								<c:when test="${lab.lab_possible==1}">
                     사용 불가능
                  </c:when>
								<c:otherwise>
                     코드 에러
                  </c:otherwise>
							</c:choose></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>