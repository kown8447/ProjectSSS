<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h3>즐겨찾기 메뉴설정</h3>
	<hr>
	<form action="#">
		<div class="ui-widget ui-helper-clearfix">
			<table border="1px" height="100px">
				<tr>
					<td>첫번째칸</td>
					<td id="gallery"><c:if test="${fn:length(allUserLinks)>0}">
							<p>공통기능</p>
							<ul>
								<c:forEach items="${allUserLinks}" var="link">
									<li><button class="btn btn-success">${link.link_name}</button></li>
								</c:forEach>
							</ul>
						</c:if> <c:if test="${fn:length(studentInfoLinks)>0}">
							<p>학생정보</p>
							<ul>
								<c:forEach items="${studentInfoLinks}" var="link">
									<li><button class="btn btn-success">${link.link_name}</button></li>
								</c:forEach>
							</ul>
						</c:if> <c:if test="${fn:length(enrollLinks)>0}">
							<p>수강신청</p>
							<ul>
								<c:forEach items="${enrollLinks}" var="link">
									<li><button class="btn btn-success">${link.link_name}</button></li>
								</c:forEach>
							</ul>
						</c:if> <c:if test="${fn:length(subjectLinks)>0}">
							<p>수강신청</p>
							<ul>
								<c:forEach items="${subjectLinks}" var="link">
									<li><button class="btn btn-success">${link.link_name}</button></li>
								</c:forEach>
							</ul>
						</c:if> <c:if test="${fn:length(adminLinks)>0}">
							<p>수강신청</p>
							<ul>
								<c:forEach items="${adminLinks}" var="link">
									<li><button class="btn btn-success">${link.link_name}</button></li>
								</c:forEach>
							</ul>
						</c:if></td>
				</tr>
			</table>
		</div>
		<div id="box" class="ui-widget-content ui-state-default">
			<h4 class="ui-widget-header">
				<span>☆</span> 즐겨찾기
			</h4>
		</div>
		<br> <br>
		<button type="submit" class="btn btn-primary"
			style="margin-left: 70%; float: left">저장</button>
		<button class="btn btn-danger" style="margin-left: 5px">취소</button>
	</form>
</div>