
<!-- 
   @Project : InitSpring
   @File name : config.jsp
   @Author : 최준호
   @Data : 2016.11.30
   @Desc : 즐겨찾기 설정 페이지
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class = "row">
<div class = "col-sm-2"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;즐겨찾기 설정 </h4><br><br>
<p style="color:blue;">(즐겨찾기 바구니 안에  원하는 메뉴를 드레그 해주세요.)</p>
		<div id="gallery">
			<table class="table table-hover" style="text-align: center;">
				<tr>
					<c:if test="${fn:length(studentLinks)>0}">
						<th style="text-align: center;">학적조회</th>
					</c:if>
					<c:if test="${fn:length(enrollLinks)>0}">
						<th style="text-align: center;">수강신청</th>
					</c:if>
					<c:if test="${fn:length(professorLinks)>0}">
						<th style="text-align: center;">교수메뉴</th>
					</c:if>
					<c:if test="${fn:length(buildingLinks)>0}">
						<th style="text-align: center;">빌딩관리</th>
					</c:if>
					<c:if test="${fn:length(stuAdminLinks)>0}">
						<th style="text-align: center;">학생관리</th>
					</c:if>
					<c:if test="${fn:length(profAdminLinks)>0}">
						<th style="text-align: center;">교수관리</th>
					</c:if>
					<c:if test="${fn:length(otherAdminLinks)>0}">
						<th style="text-align: center;">기타</th>
					</c:if>
					<c:if test="${fn:length(allUserLinks)>0}">
						<th style="text-align: center;">게시판</th>
					</c:if>
				</tr>
				<tr>
					<c:if test="${fn:length(studentLinks)>0}">
						<td id="studentList" style="list-style:none;"><c:forEach items="${studentLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="stu_${link.link_code}" style="display: none;">
											<button class="btn btn-info" id="stu_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:when>
									<c:otherwise>
										<li id="stu_${link.link_code}">
											<button class="btn btn-info" id="stu_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(enrollLinks)>0}">
						<td id="enrollList" style="list-style:none;"><c:forEach items="${enrollLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="enr_${link.link_code}" style="display: none;">
											<button class="btn btn-info"
												id="enr_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:when>
									<c:otherwise>
										<li id="enr_${link.link_code}">
											<button class="btn btn-info"
												id="enr_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(professorLinks)>0}">
						<td id="professorList" style="list-style:none;"><c:forEach items="${professorLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="prof_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="prof_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="prof_${link.link_code}"><button
												class="btn btn-info" id="prof_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(buildingLinks)>0}">
						<td id="buildingList" style="list-style:none;"><c:forEach items="${buildingLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="bui_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="bui_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="bui_${link.link_code}"><button
												class="btn btn-info" id="bui_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(stuAdminLinks)>0}">
						<td id="stuAdminList" style="list-style:none;"><c:forEach items="${stuAdminLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="aAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="aAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="aAdmin_${link.link_code}"><button
												class="btn btn-info" id="aAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(profAdminLinks)>0}">
						<td id="profAdminList" style="list-style:none;"><c:forEach items="${profAdminLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="pAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="pAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="pAdmin_${link.link_code}"><button
												class="btn btn-info" id="pAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(otherAdminLinks)>0}">
						<td id="otherAdminList" style="list-style:none;"><c:forEach items="${otherAdminLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="oAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="oAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="oAdmin_${link.link_code}"><button
												class="btn btn-info" id="oAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(allUserLinks)>0}">
						<td id="allUserList" style="list-style:none;"><c:forEach items="${allUserLinks}" var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="all_${link.link_code}" style="display: none;"><button
												class="btn btn-info" id="all_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="all_${link.link_code}"><button
												class="btn btn-info" id="all_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>
	<br><br><br>
	<h4 align="left">
		<span>☆</span> 즐겨찾기 바구니
	</h4>
	<div id="box" class="col-sm-3" style="border: 1px solid;">
		<ul id="gallery_favorite">
			<c:forEach items="${favoLinks}" var="link">
				<c:choose>
					<c:when test="${link.link_type==0}">
						<li id="stu_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==1}">
						<li id="enr_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==2}">
						<li id="prof_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==3}">
						<li id="bui_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==4}">
						<li id="aAdmin_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==5}">
						<li id="pAdmin_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:when test="${link.link_type==6}">
						<li id="oAdmin_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:when>
					<c:otherwise>
						<li id="all_${link.link_code}_favorite"><button
								class="btn btn-success">${link.link_name}</button></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
	<div class="col-sm-2"></div>
</div>