<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>

<div id="content">
	<h2>공지사항 상세보기</h2>

	<form method="post">
		<table border="1px">
			<tr>
				<td width="20%" align="center"><b> 글번호 </b></td>
				<td width="30%">${notice.notice_index}</td>
				<td width="20%" align="center"><b>작성일</b></td>
				<td>${notice.notice_date}</td>
			</tr>
			<tr>
				<td width="20%" align="center"><b>글쓴이</b></td>
				<td colspan="3">관리자</td>
			</tr>
			<tr>
				<td width="20%" align="center"><b>첨부파일</b></td>
				<td colspan="3">
				<a href="download.htm?p=notice&f=${notice.notice_file}">${notice.notice_file}</a>
			</tr>
			<tr>
				<td width="20%" align="center"><b>제목</b></td>
				<td colspan="3">${notice.notice_title}</td>
			</tr>
			<tr height="100">
				<td width="20%" align="center"><b>글내용</b></td>
				<td colspan="3">
				<c:if test="${not empty notice.notice_content}">
                        ${notice.notice_content}
                     </c:if></td>
			</tr>
		</table>

		<p>
		<a  href="notice.htm">목록</a> 
		<a  href="noticeEdit.htm?notice_index=${notice.notice_index}">수정</a>
		<a  href="noticeDel.htm?notice_index=${notice.notice_index}">삭제</a>
		<se:authorize access="hasAnyRole('ROLE_ADMIN')">
		<a  href="noticeDel.htm?notice_index=${notice.notice_index}">답글</a>
		</se:authorize>
		
		</p>

	</form>
</div>
