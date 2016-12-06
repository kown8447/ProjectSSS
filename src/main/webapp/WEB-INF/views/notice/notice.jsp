<%--
@Project : InitSpring
@File name : notice.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 목록 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h4>▶&nbsp;공지사항</h4><br><br>

<div class="row" style="width: 80%; margin: auto;">
	<form method="post">
		<div class="form-inline">
			<font>◎&nbsp;게시물 갯수: ${total}</font>
			<input type="text" id="keyword" name="keyword" class="form-control col-sm-offset-6" > 
			<input type="submit" id="searchBtn" class="btn btn-success" value="찾기">
		</div>
	</form>
	<br>
	
	<table class="table table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" style="text-align: center">등록된 글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${list}" var="n">
				<tr>
					<td>${n.notice_index}</td>
					<td><c:choose>
							<c:when test="${n.notice_depth != 0 }">
								<c:forEach var="depth" begin="0" end="${n.notice_depth*2}" step="1"> 
								&emsp;
                           		</c:forEach>
								<img src="../images/reply.png">&nbsp;   
                       		 </c:when>
						</c:choose> 
						<c:if test="${fn:length(n.notice_title) > 15}">
								<a href="noticeDetail.htm?notice_index=${n.notice_index}"> 
								<c:if test="${n.notice_status==1}">
										<h5 style="color:red">[ 삭제된 글의 답글입니다 ]</h5>
									</c:if> 
									<c:out value="${fn:substring(n.notice_title,0,15)}" />...
								</a>
							</c:if> 
							<c:if test="${fn:length(n.notice_title) <= 15}">
								<c:if test="${n.notice_status==1}">
									<h5 style="color:red">[ 삭제된 글의 답글입니다 ]</h5>
								</c:if>
								<a href="noticeDetail.htm?notice_index=${n.notice_index}">${n.notice_title}</a>
							</c:if></td>
					<td>관리자</td>
					<td>${n.notice_date}</td>
					<td>${n.notice_count}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="col-md-offset-11">
		<se:authorize access="hasAnyRole('ROLE_ADMIN')">
			<a href="${pageContext.request.contextPath}/notice/noticeWrite.htm" class="btn btn-success" >글쓰기</a>
		</se:authorize>
	</div>

	
	<div align = "center">
	 <ul class="pagination">
	 	<!-- 처음, 이전페이지 -->
     	<c:if test="${pg>fromPage}">
			<li><a href="notice.htm?pg=1&keyword=${query}">◀◀</a></li>
			<li><a href="notice.htm?pg=${pg-1}&keyword=${query}">◀</a></li>
		</c:if>
		<c:if test="${pg<=fromPage}">
			<li><span style="color: gray">◀◀</span></li>
			<li><span style="color: gray">◀</span></li>
		</c:if>
		 
		 <!-- 블록 범위 찍기 -->
		 <c:forEach begin="${fromPage}" end="${toPage}" var="i">
			<li><c:if test="${i==pg}"><a href="#">${i}</a></c:if></li>	
				<c:if test="${i!=pg}">
					<li><a href="notice.htm?pg=${i}&keyword=${query}">${i}</a></li>
				</c:if>
		</c:forEach>
		 
		 <!-- 다음, 이후 -->
		<c:if test="${pg<allPage}">
			<li><a href="notice.htm?pg=${pg+1}&keyword=${query}">▶</a></li>
			<li><a href="notice.htm?pg=${allPage}&keyword=${query}">▶▶</a></li>
		</c:if>
		
		<c:if test="${pg>=allPage}">
			<li><span style="color: gray">▶</span></li>
			<li><span style="color: gray">▶▶</span></li>
		</c:if>
	</ul>
	</div>
</div>
