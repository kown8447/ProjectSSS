<!-- 
   @Project : InitSpring
   @File name : qnaNotice.jsp
   @Author : 우명제
   @Data : 2016.11.21
   @Desc : qna게시판 목록
-->   

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h3>QnA</h3>
<div id="content">

	<form>
		<div class="col-sm-3" style="margin-left: 68%">
			<div class="input-group">

				<select id="searchType" name="searchType">
					<option value="0">제목</option>
					<option value="1">제목+내용</option>
					<option value="2">글쓴이</option>
				</select> 
				
				<input type="text" id="keyword" name="keyword" /> 
				
				<span class="input-group-btn"> 
				 <input type="submit" id="searchBtn" class="btn btn-success" value="찾기">
				</span>
			</div>
		</div>
	</form>

<div style="width:85%; text-align: center; margin: auto;">
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
         
			<c:forEach items="${list}" var="qna">
				<tr>
					<td>${qna.qna_index}</td>
					<td>
										
			<c:choose>
					<c:when test="${qna.qna_depth != 0 }">
						<c:forEach var="depth"  begin="0" end="${qna.qna_depth*2}" step="1">
							&nbsp;
						</c:forEach>						
							▶	
					</c:when>			
			</c:choose>
			
			<c:if test="${fn:length(qna.qna_title) > 15}">
                  	<a  href="qnaDetail.htm?qna_index=${qna.qna_index}">
                  	<c:if test="${qna.qna_status==1}">
                  	[삭제된 글의 답글입니다]
                  	</c:if>
                 	<c:out value="${fn:substring(qna.qna_title,0,15)}" />...  </a>                  
            </c:if> 
                     
            <c:if test="${fn:length(qna.qna_title) <= 15}">
            	<c:if test="${qna.qna_status==1}">
                  	[삭제된 글의 답글입니다]
                  	</c:if>
                    <a href="qnaDetail.htm?qna_index=${qna.qna_index}">${qna.qna_title}</a>
            </c:if>
                   </td>
														
				<td>${qna.member_id}</td>
				<td>${qna.qna_date}</td>
				<td>${qna.qna_count}</td>
			</tr>
			</c:forEach>
	</tbody>
	</table>			
	<div style="margin-left: 90%">
		<a href="${pageContext.request.contextPath}/qnanotice/qnaWrite.htm" class="btn btn-success">글쓰기</a>
	</div>
</div>	

	<div align = "center">
	 <ul class="pagination">
			<!-- 처음 이전 링크 --> 
			<c:if test="${pg>fromPage}">
			<!-- 5>10 : false / 15>10 : true -->
     	    [<a href="qnanotice.htm?pg=1&keyword=${keyword}&searchType=${searchType}">◀◀</a>]
     	    [<a href="qnanotice.htm?pg=${pg-1}&keyword=${keyword}&searchType=${searchType}">◀</a>]      
     		</c:if> 
     		
     		<c:if test="${pg<=fromPage}">
			<!-- 5<=10 :true / 15<=10:false --> 
         	[<span style="color: gray">◀◀</span>]   
         	[<span style="color: gray">◀</span>]
      		</c:if> <!-- 블록 범위 찍기 --> 
      		
      		<c:forEach begin="${fromPage}" end="${toPage}" var="i">
				<c:if test="${i==pg}">[${i}]</c:if>
				<c:if test="${i!=pg}">[<a href="qnanotice.htm?pg=${i}&keyword=${keyword}&searchType=${searchType}">${i}</a>]</c:if>
			</c:forEach> <!-- 다음, 이후 --> 
			
			<c:if test="${pg<allPage}">
			<!-- 20<21 : true -->
            [<a href="qnanotice.htm?pg=${pg+1}&keyword=${keyword}&searchType=${searchType}">▶</a>]
            [<a href="qnanotice.htm?pg=${allPage}&keyword=${keyword}&searchType=${searchType}">▶▶</a>]
      		</c:if> 
      		
      		<c:if test="${pg>=allPage}">
      		
			<!-- 21>=21 :true -->
            [<span style="color: gray">▶</span>]
            [<span style="color: gray">▶▶</span>]  
    		</c:if>
	</ul>
	</div>
</div>	




