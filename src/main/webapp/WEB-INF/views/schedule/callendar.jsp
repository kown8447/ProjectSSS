<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<!-- 
   @Project : InitSpring
   @File name : callendar.jsp
   @Author : 김영빈
   @Data : 2016.11.28
   @Desc : 캘린더 view
-->

<!-- 캘린더  -->
<h4>▶&nbsp;학사일정</h4> <br><br>

<div id="calendar" class="calendar"></div>

<!-- 캘린더  일정추가 모달-->
<se:authorize access="hasAnyRole('ROLE_ADMIN')">
	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">학사일정 추가</h4>
				</div>
				<div class="modal-body">
					<div class="form">
						<div class="form-group" id="left">
							<label for="title">제목 :</label> 
							<input type="text" class="form-control" id="title" name="title" /> 
								<label for="comment" style="margin-top: 1%">내용 :</label>
							<textarea class="form-control" rows="5" id="content" name="content"></textarea>
						</div>
						<div class="form-group">
							<div class="col-sm-4">
								<label for="startdate">시작일</label> 
								<input type="text" id="startdate" name="startdate" class="form-control" placeholder="Click me!" />
							</div>
							
							<div class="col-sm-4">
								<label for="enddate">종료일</label> 
								<input type="text" id="enddate" name="enddate" type="text" class="form-control" placeholder="Click me!" /><br>
							</div>
						</div>
					</div>
				</div>
				<br> <br> <br>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" id="insert">학사일정추가</button>
				</div>
			</div>
		</div>
	</div>
</se:authorize>

<!-- 캘린더 클릭시 모달   -->
<div id="fullCalModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span> <span class="sr-only">close</span>
				</button>
				<h4 id="modalTitle" class="modal-title"></h4>
			</div>
			<div id="modalBody" class="modal-body"></div>
			<div class="modal-footer">
				<se:authorize access="hasAnyRole('ROLE_ADMIN')">
					<button class="btn btn-success" id="update">학사 일정 수정</button>
					<button class="btn btn-warning" id="delete">학사 일정 삭제</button>
				</se:authorize>
			</div>
		</div>
	</div>
</div>

<!-- 캘린더 일정 수정 모달  -->
<div class="modal fade" id="updatemodal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">학사일정 수정</h4>
			</div>
			<div class="modal-body">
				<div class="form">
					<div class="form-group" id="left">
						<label for="title">제목</label> 
						<input type="text" class="form-control" id="title1" name="title" /> 
						<label for="comment" style="margin-top: 1%">내용</label>
						<textarea class="form-control" rows="5" id="content1" name="content"></textarea>
					</div>
				</div>
			
					
				<form>
							<label for="startdate">시작일</label> 
							<input type="text" id="startdate1" />    ~
					
							<label for="enddate">종료일</label> 
							<input type="text" id="enddate1" type="text" />
					</form>
				</div>
		
			<br> <br> <br>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" id="up">수정 완료</button>
			</div>
		</div>
	</div>
</div>
<br>
<!-- 캘린더 일정 일괄 등록  form-->
<se:authorize access="hasAnyRole('ROLE_ADMIN')">
	<button class="btn btn-success btn-sm col-sm-offset-6" data-target="#layerpop" data-toggle="modal">일정일괄등록</button>
</se:authorize>
<br />
<div class="modal fade" id="layerpop">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h5 class="modal-title">일정 일괄 등록</h5>
			</div>
			<div class="modal-body">
				<form id="excelForm" action="compExcelUpload.htm">
					<span class="filetype"> <input type="text" class="file-text" />
						<span class="file-btn">찾아보기</span> <span class="file-select">
							<input type="file" class="input-file" id="excel" name="excel"
							accept=".xlsx">
					</span>
					</span>
				</form>
				Sample File :<a href="excel.htm">Schedule.xlsx</a> <br> <font
					style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 일정을
					일괄등록 해보세요</font>
			</div>
			<div class="modal-footer">
				<button type="button" id="excelUp" onclick="check()"
					class="btn btn-success btn-xs">등록하기</button>
			</div>
		</div>
	</div>
</div>




