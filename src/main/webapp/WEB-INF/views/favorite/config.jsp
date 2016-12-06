
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

<h4>▶&nbsp;즐겨찾기 메뉴 설정</h4>
<br>
<br>
<div class="row">
	<div class="col-sm-5">
		<div id="gallery">
			<table class="table table-hover">
				<tr>
					<c:if test="${fn:length(studentLinks)>0}">
						<th>학적조회</th>
					</c:if>
					<c:if test="${fn:length(enrollLinks)>0}">
						<th>수강신청</th>
					</c:if>
					<c:if test="${fn:length(professorLinks)>0}">
						<th>교수메뉴</th>
					</c:if>
					<c:if test="${fn:length(buildingLinks)>0}">
						<th>빌딩관리</th>
					</c:if>
					<c:if test="${fn:length(stuAdminLinks)>0}">
						<th>학생관리</th>
					</c:if>
					<c:if test="${fn:length(profAdminLinks)>0}">
						<th>교수관리</th>
					</c:if>
					<c:if test="${fn:length(otherAdminLinks)>0}">
						<th>기타</th>
					</c:if>
					<c:if test="${fn:length(allUserLinks)>0}">
						<th>게시판</th>
					</c:if>
				</tr>
				<tr>
					<c:if test="${fn:length(studentLinks)>0}">
						<td id="studentList"><c:forEach items="${studentLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="stu_${link.link_code}" style="display: none;">
											<button class="btn btn-success"
												id="stu_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:when>
									<c:otherwise>
										<li id="stu_${link.link_code}">
											<button class="btn btn-success"
												id="stu_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(enrollLinks)>0}">
						<td id="enrollList"><c:forEach items="${enrollLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="enr_${link.link_code}" style="display: none;">
											<button class="btn btn-success"
												id="enr_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:when>
									<c:otherwise>
										<li id="enr_${link.link_code}">
											<button class="btn btn-success"
												id="enr_${link.link_code}_btn">${link.link_name}</button>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(professorLinks)>0}">
						<td id="professorList"><c:forEach items="${professorLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="prof_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="prof_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="prof_${link.link_code}"><button
												class="btn btn-success" id="prof_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(buildingLinks)>0}">
						<td id="buildingList"><c:forEach items="${buildingLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="bui_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="bui_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="bui_${link.link_code}"><button
												class="btn btn-success" id="bui_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(stuAdminLinks)>0}">
						<td id="stuAdminList"><c:forEach items="${stuAdminLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="aAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="aAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="aAdmin_${link.link_code}"><button
												class="btn btn-success" id="aAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(profAdminLinks)>0}">
						<td id="profAdminList"><c:forEach items="${profAdminLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="pAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="pAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="pAdmin_${link.link_code}"><button
												class="btn btn-success" id="pAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(otherAdminLinks)>0}">
						<td id="otherAdminList"><c:forEach items="${otherAdminLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="oAdmin_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="oAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="oAdmin_${link.link_code}"><button
												class="btn btn-success" id="oAdmin_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
					<c:if test="${fn:length(allUserLinks)>0}">
						<td id="allUserList"><c:forEach items="${allUserLinks}"
								var="link">
								<c:choose>
									<c:when test="${link.favorite}">
										<li id="all_${link.link_code}" style="display: none;"><button
												class="btn btn-success" id="all_${link.link_code}_btn">${link.link_name}</button></li>
									</c:when>
									<c:otherwise>
										<li id="all_${link.link_code}"><button
												class="btn btn-success" id="all_${link.link_code}_btn">${link.link_name}</button></li>
									</c:otherwise>
								</c:choose>
							</c:forEach></td>
					</c:if>
				</tr>
			</table>
		</div>
	</div>

	<div id="box" class="col-sm-5">
		<h4>
			<span>☆</span> 즐겨찾기
		</h4>
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
</div>