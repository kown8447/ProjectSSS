<!-- 
   @Project : InitSpring
   @File name : qnaReply.jsp
   @Author : 우명제
   @Data : 2016.11.23
   @Desc : qna게시판 답글
-->   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h4>▶&nbsp;Q&A 답글</h4> <br><br>

<div class="row" style="width: 70%; margin: auto;">
	<form action="" method="post" enctype="multipart/form-data">
	
	<!-- 히든 필드로 정보 보내기 -->
	<input type="hidden" name="qna_index" value="${qna.qna_index}" />
	<input type="hidden" name="qna_depth" value="${qna.qna_depth}" />
	<input type="hidden" name="qna_step" value="${qna.qna_step}" />
	<input type="hidden" name="qna_refer" value="${qna.qna_refer}" />
	<!-- 히든 필드로 정보 보내기 -->
	
	<table class="table table-bordered">
		<tr>
			<th style="background-color: #F8F4EC; text-align: center;"><b>작성자</b></th>
			<td>${id}</td>
		</tr>		
		<tr>
			<th style="background-color: #F8F4EC; text-align: center;">제목</th>
			<td colspan="3">				
				<input type="text" name="qna_title" id="qna_title" value="RE:${qna.qna_title}" class="form-control" >
			</td>
		</tr>
		<tr>
			<th style="background-color: #F8F4EC; text-align: center;">글내용</th>
			<td  colspan="3">
				<textarea name="qna_content" id="qna_content" rows="8" style="resize: none;"class="form-control"></textarea>
			</td>
		</tr>
		<tr>
			<th style="background-color: #F8F4EC; text-align: center;" >첨부파일</th>
			<td>
					<div class="form-group">	
               			<span class="filetype"> 
               				<input type="text" class="file-text" />
                  			<span class="file-btn">찾아보기</span> 
                  			<span class="file-select">
                    			<input type="file" class="input-file" name="file" id="file"  readonly>
               				</span>
               			</span>
            		</div>
			</td>
		</tr>
	</table>
	<br>
		<div class="col-md-offset-10">
			<input type="submit" id="replyBtn" value="저장"  class="btn btn-success btn-sm"/>
			<a href="qnaDetail.htm?qna_index=${qna.qna_index}"  class="btn btn-default btn-sm">취소</a>	
		</div>
	</form>
</div>