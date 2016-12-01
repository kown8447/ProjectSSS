<!-- 
   @Project : InitSpring
   @File name : qnaWrite.jsp
   @Author : 우명제
   @Data : 2016.11.21
   @Desc : qna게시판 글쓰기페이지
-->   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<form action="" method="post" enctype="multipart/form-data">

		<div>
			<label>제 목</label>&nbsp; 
			<input type="text" name="qna_title" id="qna_title">
		</div>

		<div>
			<label>첨부파일</label> &nbsp; 
			<input type="file" id="file" name="file">
		</div>

		<div>
			<label>내용</label>&nbsp; 
			<div>
				<textarea id="qna_content" name="qna_content" rows="10" style="resize: none;"></textarea>
			</div>
			
		</div>
	
		<br>
	
		<input type="submit" id="writeBtn" name="writeBtn" value="저장" />
		<a href="qnanotice.htm">취소</a>

	</form>