<%--
@Project : InitSpring
@File name : notice.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 목록 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<h3>공지사항</h3>
<div id="content">
<se:authorize access="hasAnyRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/notice/noticeWrite.htm"> 글쓰기</a>
</se:authorize>
	<table border="1px">
			<tr>
				<th class="seq">번호</th>
				<th class="title">제목</th>
				<th class="writer">작성자</th>
				<th class="regdate">작성일</th>
				<th class="hit">조회수</th>
			</tr>
		<tbody>
			<c:forEach items="${list}" var="n">
				<tr>
					<td>${n.notice_index}</td>
					<td><a href="noticeDetail.htm?notice_index=${n.notice_index}">${n.notice_title}</a></td>
					<td>관리자</td>
					<td>${n.notice_date}</td>
					<td>${n.notice_count}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
