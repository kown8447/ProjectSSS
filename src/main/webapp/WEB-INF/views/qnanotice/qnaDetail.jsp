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

<h4>▶&nbsp;Q&A 상세보기</h4> <br><br>

<div class="row" style="width: 70%; margin: auto;">   
   <form method="post">
      <table class="table table-bordered">
         <tr>
            <th style="text-align: center; background-color: #F8F4EC;">글번호</th>
            <td style="text-align: center; width:30%;">${qna.qna_index}</td>
            <th style="text-align: center; background-color: #F8F4EC;">작성일</th>
            <td>${qna.qna_date}</td>
         </tr>            
         <tr>
            <th style="text-align: center; background-color: #F8F4EC;">작성자</th>
            <td colspan="3">${qna.member_id}</td>
         </tr>         
         <tr>
            <th style="text-align: center; background-color: #F8F4EC;">제목</th>
            <td colspan="3">${qna.qna_title}</td>
         </tr>
         <tr>
            <th style="text-align: center; background-color: #F8F4EC; height: 100px;">글내용</th>
            <td colspan="3">${qna.qna_content}</td>
         </tr>         
         <tr>
            <th style="text-align: center; background-color: #F8F4EC;">첨부파일</th>
            <td colspan="3">
               <a href="download.htm?p=qnanotice&f=${qna.qna_file}">${qna.qna_file}</a>
            </td>
         </tr>
      </table>
      <br>
      
      <!-- 기능 -->
      <div class="col-md-offset-8">
         <a href="qnaDel.htm?qna_index=${qna.qna_index}" id="qnaDelete" class="btn btn-warning btn-sm">삭제</a>      
         <a href="qnaEdit.htm?qna_index=${qna.qna_index}" id="qnaUpdate" class="btn btn-success btn-sm">수정</a>
         <a href="qnaReply.htm?qna_index=${qna.qna_index}" class="btn btn-success btn-sm" >답변</a>
         <a href="qnanotice.htm" class="btn btn-success btn-sm">목록</a>
      </div>
      <br>   
      
      <input type="hidden" id="qna_index" name="qna_index" value="${qna.qna_index}" />
      
      <!--댓글 올리는부분-->
      <font style="color: #D5D5D5" size="3">Comments</font>
      <textarea id="insertReply_content" name="insertReply_content"  rows="2" class="form-control" style="resize: none;"></textarea>
      <button type="button" id="commentBtn" name="commentBtn" class="btn btn-default" style="color:#D5D5D5; float: right; width: 100px">등록</button>
      <br>
      <hr>
      
      <!-- 댓글 View  -->
      <div id="replyList" style="text-align: left;">
         <c:forEach items="${rqna}" var="rqna" varStatus="index">
            <div id="replyView_${index.count}">
            <input type="hidden" id="cmt_index_${index.count}" value="${rqna.reply_index}"> 
            <input type="hidden" id="cmt_id_${index.count}" value="${rqna.member_id}">            
               <table>
                  <tr>
                     <td> 
                        <div id="cmt_writer_${index.count}" style="color: green;">${rqna.member_id}</div>
                     </td>   
                  </tr>
                  <span style="float:right">
                     <button type="button" value="cmtUpdate_${index.count}" id="cmtUpdate_${index.count}" class="cmtEdit btn-link btn-xs" name="cmtEdit">수정</button>
                     <button type="button" id="cmtDelete_${index.count}" value="cmtDelete_${index.count}" class="cmtDel btn-link btn-xs" name="cmtDel">삭제</button>
                  </span>
                  <tr>
                     <td> 
                        <textarea rows="2" cols="100" class="cmtContent form-control" id="cmt_content_${index.count}" readonly="readonly" style="resize: none;background-color: white;border: 0">${rqna.reply_content}</textarea>
                     </td>
                  </tr>
               </table>
             </div> 
             <hr>
         </c:forEach>
      </div>

   
   </form>


   <input type="hidden" id="writerId" value="${qna.member_id}">
   <input type="hidden" id="readerId" value="${readerId}">
</div>