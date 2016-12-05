<!-- 
   @Project : InitSpring
   @File name : qnaWrite.jsp
   @Author : 우명제
   @Data : 2016.11.21
   @Desc : qna게시판 글쓰기페이지
-->   
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h4>▶&nbsp;Q&A 작성</h4> <br><br>
	
	<form action="" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 70%; margin: auto;">
			<tr>
				<th style="text-align: center; width:20%; background-color: #F8F4EC;">제목</th>
				<td>
					<input type="text" name="qna_title" id="qna_title" class="form-control"></td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #F8F4EC;">내용</th>
				<td>
				<textarea id="qna_content" name="qna_content" rows="16" style="resize: none;" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #F8F4EC;">첨부파일</th>
				<td>
					<div class="form-group">	
               			<span class="filetype"> 
               				<input type="text" class="file-text" />
                  			<span class="file-btn">찾아보기</span> 
                  			<span class="file-select">
                    			<input type="file" class="input-file" name="file" id="file"  value="${notice.notice_file }" readonly>
               				</span>
               			</span>
            		</div>
				</td>	
			</tr>
		</table>
		
		<br>
		<div align="center" style="margin-left: 40%">
			<input type="submit" id="writeBtn" name="writeBtn" class="btn btn-success btn-sm" style="width: 15%;" value="저장" />
			<a href="qnanotice.htm" class="btn btn-default btn-sm"style="width: 15%;" >취소</a>
		</div>
	</form>
</div>	