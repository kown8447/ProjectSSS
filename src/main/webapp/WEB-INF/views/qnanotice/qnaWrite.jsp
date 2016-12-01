<!-- 
   @Project : InitSpring
   @File name : qnaWrite.jsp
   @Author : 우명제
   @Data : 2016.11.21
   @Desc : qna게시판 글쓰기페이지
-->   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="content">
	<h2>QnA</h2>
	
	<form action="" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 50%; margin: auto;">
			<tr>
				<th style="text-align: center;width:120px">제목</th>
				<td>
					<input type="text" name="qna_title" id="qna_title" class="form-control"></td>
			</tr>
			<tr style="height: 45%">
				<th style="text-align: center;">내용</th>
				<td>
				<textarea id="qna_content" name="qna_content" rows="22" style="width:600px;"></textarea>
				</td>
			</tr>
			<tr>
				<th style="text-align: center;">첨부파일</th>
				<td><input type="file" id="file" name="file"></td>	
			</tr>
		</table>
		
		<br>
		<div align="center">
			<input type="submit" id="writeBtn" name="writeBtn" class="btn btn-success" value="저장" />
			<a href="qnanotice.htm" class="btn btn-success">취소</a>
		</div>
	</form>
</div>	