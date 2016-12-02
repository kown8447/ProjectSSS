<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
	<table>
		<thead>
			<tr>
				<td>교수연구실코드</td>
				<td>건물코드</td>
				<td>연구실 이름</td>
				<td>전화번호</td>
				<td>사용가능 여부</td>
			</tr>
		</thead>
		<c:forEach items="${lablist}" var="lab" varStatus="index">
			<tbody>
				<tr>
					<td>${lab.lab_code}</td>
					<td>${lab.building_name}</td>
					<td>
						<a href="labDetail.htm?lab_code=${lab.lab_code}">${lab.lab_name}</a>
					</td>
					<td>${lab.lab_phone}</td>
					<td>
						<c:choose>
							<c:when test="${lab.lab_possible==0}">
								사용가능
							</c:when>
							<c:when test="${lab.lab_possible==1}">
								사용 불가능
							</c:when>
							<c:otherwise>
								코드 에러
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>
