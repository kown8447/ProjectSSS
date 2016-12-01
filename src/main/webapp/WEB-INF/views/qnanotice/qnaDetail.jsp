<!-- 
   @Project : InitSpring
   @File name : qnaDetail.jsp
   @Author : 우명제
   @Data : 2016.11.22
   @Desc : qna게시판 상세 글보기 및 삭제
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table>
	<tr>
		<td>제목</td>
		<td>${qna.qna_title}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${qna.qna_date}</td>
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
		<td><a href="download.htm?p=qnanotice&f=${qna.qna_file}">${qna.qna_file}</a>
		<td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${qna.qna_content}</td>
	</tr>
</table>
<hr>

<!-- 댓글 -->
<input type="hidden" id="qna_index" name="qna_index"
	value="${qna.qna_index}" />

<div id="replyList">
	<c:forEach items="${rqna}" var="rqna" varStatus="index">
		<div id="replyView_${index.count}">
			<input type="hidden" id="cmt_index_${index.count}"
				value="${rqna.reply_index}"> <div id="cmt_writer_${index.count}">${rqna.member_id}</div> <br>
			<div class="cmtContent" id="cmt_content_${index.count}">${rqna.reply_content}</div>
			<button type="button" value="cmtUpdate_${index.count}"
				id="cmtUpdate_${index.count}" class="cmtEdit" name="cmtEdit">수정</button>
			<button type="button" id="cmtDelete_${index.count}" value="cmtDelete_${index.count}" class="cmtDel"
				name="cmtDel">삭제</button>
				<input type="hidden" id="cmt_id_${index.count}"
				value="${rqna.member_id}">
		</div>
	</c:forEach>
</div>
<hr>

<table>
	<tr>
		<td>댓글<br>
		<textarea id="insertReply_content" name="insertReply_content"  rows="3" cols="40"></textarea>
			<button type="button" id="commentBtn" name="commentBtn">등록</button>
		</td>
	</tr>

</table>


<!-- 기능 -->

<a href="qnanotice.htm">목록</a>
<a href="qnaEdit.htm?qna_index=${qna.qna_index}" id="qnaUpdate">수정</a>
<a href="qnaDel.htm?qna_index=${qna.qna_index}" id="qnaDelete">삭제</a>
<a href="qnaReply.htm?qna_index=${qna.qna_index}">답변</a>
<hr>
<input type="hidden" id="writerId" value="${qna.member_id}">
<input type="hidden" id="readerId" value="${readerId}">