<!-- 
   @Project : InitSpring
   @File name : qnaEdit.jsp
   @Author : 우명제
   @Data : 2016.11.22
   @Desc : qna게시판 글 수정
-->   

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="" method="post" enctype="multipart/form-data">
		
		<table>
			<tr>
				<td>제목</td>
				<td><input name="qna_title" id="qna_title" value="${qna.qna_title}" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${qna.member_id}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${qna.qna_count}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" id="file" name="file" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="qna_content" name="qna_content">${qna.qna_content}</textarea></td>
			</tr>			
		</table>

			<input type="submit" id="editBtn" name="editBtn" value="수정" /> 
			<a href="qnaDetail.htm?qna_index=${qna.qna_index}">취소</a>

	</form>

 