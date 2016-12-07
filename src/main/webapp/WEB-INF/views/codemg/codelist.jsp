<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" style="width: 75%">
	<h4>▶&nbsp;리스트</h4>
	<br>
	<br>
	<div class="container" style="width: 95%">
		<form>
		<input type="hidden" name="code_type" value="${typeofcode}">
			<div class="form-inline">
				<select id="searchType"
					name="searchType" class="form-control col-sm-offset-4">
					<option value="code">코드</option>
					<option value="code_name">이름</option>
				</select>
				<input type="text" id="keyword" name="keyword" class="form-control"style="margin-right: 5px;" /> 
				<input type="submit" id="searchBtn" class="btn btn-success" value="찾기">
			</div>
		</form>
		<table class="table" style="text-align: center">
			<thead>
				<tr>
					<th style="text-align: center">구분</th>
					<th style="text-align: center">코드</th>
					<th style="text-align: center">이름</th>
					<th style="text-align: center">생년월일</th>

				</tr>
			</thead>
			<c:forEach items="${codelist}" var="code" varStatus="index">
				<tbody>
					<tr>
						<td>
							<c:choose>
								<c:when test="${code.code_type ==0}">학생</c:when>
								<c:when test="${code.code_type ==1}">교수</c:when>
							<c:otherwise>관리자</c:otherwise>
							</c:choose>
						</td>
						<td id="code${index.count}">${code.code}</td>
						<td><a href="codedetail.htm?code=${code.code}">${code.code_name}</a>
						</td>
						<td>${code.code_birth}</td>

						<td><input value="${code.code_type}" type="hidden"></td>
					</tr>
				</tbody>

			</c:forEach>
		</table>

		<c:if test="${typeofcode==0}">
			<a href="registerstudent.htm"><button style="float: right;" class="btn btn-danger">되돌아가기</button></a>
		</c:if>
		<c:if test="${typeofcode==1}">
			<a href="registerprofessor.htm"><button style="float: right;" class="btn btn-danger">되돌아가기</button></a>
		</c:if>
		<c:if test="${typeofcode==2}">
			<a href="registeradmin.htm"><button style="float: right;" class="btn btn-danger">되돌아가기</button></a>
		</c:if>

	</div>
</div>
