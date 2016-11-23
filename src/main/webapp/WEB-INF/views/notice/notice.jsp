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

	<table border="1px">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		<tbody>
			<c:forEach items="${list}" var="n">
				<tr>
					<td>${n.notice_index}</td>
					<td>               
                     <c:choose>
                        <c:when test="${n.notice_depth != 0 }">
                           <c:forEach var="depth"  begin="0" end="${n.notice_depth*2}" step="1">
                              &nbsp;
                           </c:forEach>                  
                           <img src="../image/reply.png">&nbsp;   
                        </c:when>
                  </c:choose>
              <a href="noticeDetail.htm?notice_index=${n.notice_index}">${n.notice_title}</a></td>
					<td>관리자</td>
					<td>${n.notice_date}</td>
					<td>${n.notice_count}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<se:authorize access="hasAnyRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/notice/noticeWrite.htm"> 글쓰기</a>
</se:authorize>
</div>
