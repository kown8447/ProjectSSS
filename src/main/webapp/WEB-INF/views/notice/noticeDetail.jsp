<%--
@Project : InitSpring
@File name : noticeDetail.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글상세보기 form
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>

<div id="content">
	<h2>공지사항 상세보기</h2>

	<form method="post">
		<table class="table table-bordered" style="width:70%; margin: auto;">
			<tr style="width:20%">
				<th style="text-align: center" >글번호</th>
				<td style="text-align: center; width:30%;">${notice.notice_index}</td>
				<th style="text-align: center">작성일</th>
				<td>${notice.notice_date}</td>
			</tr>
			<tr>
				<th style="text-align: center">작성자</th>
				<td colspan="3">관리자</td>
			</tr>
			<tr>
				<th style="text-align: center">제목</th>
				<td colspan="3">${notice.notice_title}</td>
			</tr>
			<tr>
				<th style="text-align: center; height: 100px;">글내용</th>
				<td colspan="3">
				<c:if test="${not empty notice.notice_content}">
                        ${notice.notice_content}
                     </c:if></td>
			</tr>
			<tr>
				<th style="text-align: center">첨부파일</th>
				<td colspan="3">
				<a href="download.htm?p=notice&f=${notice.notice_file}">${notice.notice_file}</a>
			</tr>
		</table>
		<br>
		
		<div align ="center">
		<se:authorize access="hasAnyRole('ROLE_ADMIN')">
		<a  href="noticeEdit.htm?notice_index=${notice.notice_index}" class="btn btn-success">수정</a>
		<a  href="noticeDel.htm?notice_index=${notice.notice_index}" class="btn btn-success">삭제</a>
		<a  href="replyWrite.htm?notice_index=${notice.notice_index}" class="btn btn-success">답글</a>
		</se:authorize>
		<a  href="notice.htm" class="btn btn-success">목록</a> 
		
		</div>

	</form>
</div>