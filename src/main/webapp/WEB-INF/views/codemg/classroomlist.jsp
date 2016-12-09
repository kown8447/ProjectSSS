<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : classroomlist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 강의실 목록 보기 view
-->		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="row  col-sm-offset-2">
	<h4>▶&nbsp;강의실 리스트</h4><br> <br>
	<div class="col-sm-10 form-inline">
		<select id="classroomInbuildings" name="building" class="form-control">
			<option value="default">전체보기</option>
			<c:forEach var="building" items="${buildingList}">
				<option value="${building.building_code}">${building.building_name}</option>
			</c:forEach>
		</select>
		<a href="registerclassroom.htm" style="float: right;" class="btn btn-success  col-sm-offset-6">강의실 등록</a>
	</div>
	<br><br><br>
	<div class="col-sm-10">
		<table class="table table-bordered" style="text-align: center" >
			<thead>
				<tr class="active">
					<th style="text-align: center">강의실코드</th>
					<th style="text-align: center">건물코드</th>
					<th style="text-align: center">강의실 이름</th>
					<th style="text-align: center">수용인원</th>
					<th style="text-align: center">강의실 타입</th>
				</tr>
			</thead>
			<tbody id="classRooms">
				<c:forEach items="${classlist}" var="c" varStatus="index">

					<tr>
						<td>${c.classroom_code }</td>
						<td>${c.building_name }</td>
						<td><a
							href="classroomUpdate.htm?classroom_code=${c.classroom_code}">${c.classroom_name }</a>
						</td>
						<td>${c.seat }</td>
						<td><c:choose>
								<c:when test="${c.classroom_type==0 }">
                     일반강의실
                  </c:when>
								<c:when test="${c.classroom_type==1 }">
                     실습실
                  </c:when>
								<c:otherwise>
                     에러
                  </c:otherwise>
							</c:choose></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		
	</div>
</div>

