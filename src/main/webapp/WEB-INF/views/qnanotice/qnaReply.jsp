<!-- 
   @Project : InitSpring
   @File name : qnaReply.jsp
   @Author : 우명제
   @Data : 2016.11.23
   @Desc : qna게시판 답글
-->   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<form action="" method="post" enctype="multipart/form-data">

	<table>
		<tr>
			<th>제목</th>
			<td>
				<!-- 히든 필드로 정보 보내기 -->
				<input type="hidden" name="qna_index" value="${qna.qna_index}" />
				<input type="hidden" name="qna_depth" value="${qna.qna_depth}" />
				<input type="hidden" name="qna_step" value="${qna.qna_step}" />
				<input type="hidden" name="qna_refer" value="${qna.qna_refer}" />
				<!-- 히든 필드로 정보 보내기 -->
				
				<input type="text" name="qna_title" id="qna_title" value="RE:${qna.qna_title}" >
			</td>
		</tr>

		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" id="file" name="file" >
			</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>
				<textarea name="qna_content" id="qna_content" rows="5" cols="50"></textarea>
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="submit" id="replyBtn" value="답변" />
				<a href="qnaDetail.htm?qna_index=${qna.qna_index}">취소</a>
			</td>
		</tr>
	</table>

</form>

