<!-- 
   @Project : InitSpring
   @File name : qnaEdit.jsp
   @Author : 우명제
   @Data : 2016.11.22
   @Desc : qna게시판 글 수정
-->   

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h4>▶&nbsp;Q&A 수정</h4> <br><br>
<%-- 		<input type="hidden" value="${qna.file}"> --%>
	<form action="" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width:70%; margin: auto;">
		
			<tr style="width:20%">
				<th style="text-align: center; background-color: #F8F4EC;" >글번호</th>
				<td style="text-align: center; width:30%;">${qna.qna_index}</td>
				<th style="text-align: center; background-color: #F8F4EC;">작성일</th>
				<td style="text-align: center;">${qna.qna_date}</td>
			</tr>			
			<tr>
				<th style="text-align: center; background-color: #F8F4EC;">작성자</th>
				<td colspan="3">${qna.member_id}</td>
			</tr>					
			<tr>	
				<th style="text-align: center; background-color: #F8F4EC;">제목</th>
				<td colspan="3">
					<input type="text" class="form-control" name="qna_title" id="qna_title" value="${qna.qna_title}" />
				</td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #F8F4EC;">내용</th>
				<td colspan="3">
					<textarea rows="7" class="form-control" id="qna_content" name="qna_content" style="resize: none;">${qna.qna_content}</textarea>
				</td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #F8F4EC;">첨부파일</th>
				<td colspan="3">
					<div class="form-group">	
               			<span class="filetype"> 
               				<input type="text" class="file-text" value="${qna.qna_file}"/>
                  			<span class="file-btn">찾아보기</span> 
                  			<span class="file-select">
                    			<input type="file" class="input-file" name="file" id="file">
               				</span>
               			</span>
            		</div>					
				</td>
			</tr>
		</table>
		<br>
		<div align="center" style="margin-left: 40%">
			<input type="submit" id="editBtn" name="editBtn" value="수정하기" class="btn btn-success btn-sm" style="width: 15%;"/> 
			<a href="qnaDetail.htm?qna_index=${qna.qna_index}" class="btn btn-default btn-sm" style="width: 15%;">취소</a>	
		</div>
	</form>

 