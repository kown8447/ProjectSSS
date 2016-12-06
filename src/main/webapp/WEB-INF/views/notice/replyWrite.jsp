<%--
@Project : InitSpring
@File name : noticeWrite.jsp
@Author : 송아름
@Data : 2016.11.23
@Desc : 게시판 공지사항 답글쓰기 form
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
<h4>▶&nbsp;공지사항 답글</h4> <br><br>

<div class="row" style="width: 70%; margin: auto;">
	<form method="POST" enctype="multipart/form-data">
	
			<!-- 히든 필드로 정보 보내기 -->
			<input type="hidden" name="notice_index" value="${notice.notice_index}"/>
			<input type="hidden" name="notice_step" value="${notice.notice_step}"/>
			<input type="hidden" name="notice_refer" value="${notice.notice_refer}"/>
			<input type="hidden" name="notice_depth" value="${notice.notice_depth}"/>
	
	<table class="table table-bordered">
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">작성자</th>
			<td>관리자</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >제목</th>
			<td colspan="3">
			<input type="text" name="notice_title" value="RE:${notice.notice_title}" class="form-control"></td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">글내용</th>
			<td colspan="3"><textarea rows="8" name="notice_content" class="form-control" style="resize: none;"></textarea>
			</td>
		</tr>
		<tr>
            <th style="text-align: center; background-color: #F8F4EC;">첨부파일</th>
            <td colspan="3">
               <div class="form-group">   
                        <span class="filetype"> 
                           <input type="text" class="file-text" />
                           <span class="file-btn">찾아보기</span> 
                           <span class="file-select">
                             <input type="file" class="input-file" name="file" id="file" readonly>
                           </span>
                        </span>
                  </div>               
            </td>
         </tr>
	</table>

		<br>
		<div class="col-md-offset-10">
			<input type="submit" id="replyBtn" value="저장" class="btn btn-success btn-sm"/> 
			<a href="notice.htm" class="btn btn-default btn-sm">취소</a>
		</div>

	</form>
</div>