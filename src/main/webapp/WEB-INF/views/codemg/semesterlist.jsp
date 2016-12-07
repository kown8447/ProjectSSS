<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" style="width:75%">
<h4>▶&nbsp;리스트 </h4>
<br><br> 
<div class="container" style="width:95%">
<table class="table" style="text-align: center">
		<thead>
			<tr>
				<th style="text-align: center">학기코드</th>
				<th style="text-align: center">학기 이름</th>
				<th style="text-align: center">학기 시작일</th>
				<th style="text-align: center">학기 종료일</th>
			</tr>
		</thead>
		<c:forEach items="${semesterlist}" var="sm" varStatus="index">
		<tbody>
			<tr>
				<td>
				${sm.semester_code }
				</td>
				<td>
					<a href="detailSemester.htm?semester_code=${sm.semester_code}">
						${sm.semester_name }
					</a>
				</td>
				<td>
					${sm.semester_start }
				</td>
				<td>
					${sm.semester_end  }
				</td>
		</tbody>
		</c:forEach>
	</table>
		<a href="registersemester.htm"><button style="float:right;" class="btn btn-danger">되돌아가기</button></a>	
	</div>
	
</div>