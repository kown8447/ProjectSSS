<!-- 
   @Project : InitSpring
   @File name : qnaEdit.jsp
   @Author : 우명제
   @Data : 2016.11.22
   @Desc : qna게시판 글 수정
-->   

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="content">
	<h2>QnA 수정</h2>
	
	<form action="" method="post" enctype="multipart/form-data">
		
		<table class="table table-bordered" style="width:70%; margin: auto;">
			<tr style="width:20%">
				<th style="text-align: center" >글번호</th>
				<td style="text-align: center; width:30%;">${qna.qna_index}</td>
				<th style="text-align: center">작성일</th>
				<td>${qna.qna_date}</td>
			</tr>			
			<tr>
				<th style="text-align: center">작성자</th>
				<td colspan="3">${qna.member_id}</td>
			</tr>					
			<tr>	
				<th style="text-align: center">제목</th>
				<td colspan="3">
					<input type="text" class="form-control" name="qna_title" id="qna_title" value="${qna.qna_title}" />
				</td>
			</tr>
			<tr>
				<th style="text-align: center">내용</th>
				<td colspan="3">
					<textarea rows="7" class="form-control" id="qna_content" name="qna_content">${qna.qna_content}</textarea>
				</td>
			</tr>
			<tr>
				<th style="text-align: center">첨부파일</th>
				<td colspan="3">
					<input type="file" id="file" name="file" />
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" id="editBtn" name="editBtn" value="수정" class="btn btn-success"/> 
					<a href="qnaDetail.htm?qna_index=${qna.qna_index}" class="btn btn-success">취소</a>				
				</td>
			</tr>
		</table>

	</form>
</div>
 