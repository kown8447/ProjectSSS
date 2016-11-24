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

	<input type="text" name="q" value="" /> 
	<input type="submit" value="검색" />
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

	
<table>
<tr>
	<td align="center">
		<!-- 처음 이전 링크 -->
		<c:if test="${pg>block}">  <!-- 5>10 : false / 15>10 : true -->
			[<a href="list.htm?pg=1">◀◀</a>]
			[<a href="list.htm?pg=${fromPage-1}">◀</a>]		
		</c:if>
		<c:if test="${pg<=block}"> <!-- 5<=10 :true / 15<=10:false -->
			[<span style="color:gray">◀◀</span>]	
			[<span style="color:gray">◀</span>]
		</c:if>
		
		<!-- 블록 범위 찍기 -->
		<c:forEach begin="${fromPage}" end="${toPage}" var="i">
			<c:if test="${i==pg}">[${i}]</c:if>
			<c:if test="${i!=pg}">
				[<a href="list.htm?pg=${i}">${i}</a>]
			</c:if>
		</c:forEach>
		
		<!-- 다음, 이후 -->
		<c:if test="${toPage<allPage}"> <!-- 20<21 : true -->
				[<a href="list.htm?pg=${toPage+1}">▶</a>]
				[<a href="list.htm?pg=${allPage}">▶▶</a>]
		
		</c:if>	
		<c:if test="${toPage>=allPage}"> <!-- 21>=21 :true -->
				[<span style="color:gray">▶</span>]
				[<span style="color:gray">▶▶</span>]
		
		</c:if>			
		
	</td>
</tr>
</table>

	
	
</div>
