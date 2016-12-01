<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 
   @Project : InitSpring
   @File name : config.jsp
   @Author : 최준호
   @Data : 2016.11.30
   @Desc : 즐겨찾기 설정 페이지
-->
<div>
	<h3>즐겨찾기 메뉴설정</h3>
	<hr>
	<div class="ui-widget ui-helper-clearfix">
		<div id="gallery">
			<ul>
				<c:if test="${fn:length(allUserLinks)>0}">
					<li><a href="#allUserList">공통기능</a></li>
				</c:if>
				<c:if test="${fn:length(studentInfoLinks)>0}">
					<li><a href="#studentInfoList">학생정보</a></li>
				</c:if>
				<c:if test="${fn:length(enrollLinks)>0}">
					<li><a href="#enrollList">수강신청</a></li>
				</c:if>
				<c:if test="${fn:length(subjectLinks)>0}">
					<li><a href="#profSubjectList">과목관리</a></li>
				</c:if>
				<c:if test="${fn:length(adminLinks)>0}">
					<li><a href="#adminList">사이트관리</a></li>
				</c:if>
			</ul>
			<c:if test="${fn:length(allUserLinks)>0}">
				<ul id="allUserList">
					<c:forEach items="${allUserLinks}" var="link">
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
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${fn:length(studentInfoLinks)>0}">
				<ul id="studentInfoList">
					<c:forEach items="${studentInfoLinks}" var="link">
						<c:choose>
							<c:when test="${link.favorite}">
								<li id="stu_${link.link_code}" style="display: none;"><button
										class="btn btn-success" id="stu_${link.link_code}_btn">${link.link_name}</button></li>
							</c:when>
							<c:otherwise>
								<li id="stu_${link.link_code}"><button
										class="btn btn-success" id="stu_${link.link_code}_btn">${link.link_name}</button></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${fn:length(enrollLinks)>0}">
				<ul id="enrollList">
					<c:forEach items="${enrollLinks}" var="link">
						<c:choose>
							<c:when test="${link.favorite}">
								<li id="enr_${link.link_code}" style="display: none;"><button
										class="btn btn-success" id="enr_${link.link_code}_btn">${link.link_name}</button></li>
							</c:when>
							<c:otherwise>
								<li id="enr_${link.link_code}"><button
										class="btn btn-success" id="enr_${link.link_code}_btn">${link.link_name}</button></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${fn:length(subjectLinks)>0}">
				<ul id="profSubjectList">
					<c:forEach items="${subjectLinks}" var="link">
						<c:choose>
							<c:when test="${link.favorite}">
								<li id="pro_${link.link_code}" style="display: none;"><button
										class="btn btn-success" id="pro_${link.link_code}_btn">${link.link_name}</button></li>
							</c:when>
							<c:otherwise>
								<li id="pro_${link.link_code}"><button
										class="btn btn-success" id="pro_${link.link_code}_btn">${link.link_name}</button></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${fn:length(adminLinks)>0}">
				<ul id="adminList">
					<c:forEach items="${adminLinks}" var="link">
						<c:choose>
							<c:when test="${link.favorite}">
								<li id="adm_${link.link_code}" style="display: none;"><button
										class="btn btn-success" id="adm_${link.link_code}_btn">${link.link_name}</button></li>
							</c:when>
							<c:otherwise>
								<li id="adm_${link.link_code}"><button
										class="btn btn-success" id="adm_${link.link_code}_btn">${link.link_name}</button></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
		</div>

	</div>
	<div id="box" class="ui-widget-content ui-state-default">
		<h4 class="ui-widget-header">
			<span>☆</span> 즐겨찾기
		</h4>
		<ui id="gallery_favorite"> <c:forEach items="${favoLinks}"
			var="link">
			<c:choose>
				<c:when test="${link.link_type==1}">
					<li id="stu_${link.link_code}_favorite"><button
							class="btn btn-success">${link.link_name}</button></li>
				</c:when>
				<c:when test="${link.link_type==2}">
					<li id="enr_${link.link_code}_favorite"><button
							class="btn btn-success">${link.link_name}</button></li>
				</c:when>
				<c:when test="${link.link_type==3}">
					<li id="pro_${link.link_code}_favorite"><button
							class="btn btn-success">${link.link_name}</button></li>
				</c:when>
				<c:when test="${link.link_type==4}">
					<li id="adm_${link.link_code}_favorite"><button
							class="btn btn-success">${link.link_name}</button></li>
				</c:when>
				<c:otherwise>
					<li id="all_${link.link_code}_favorite"><button
							class="btn btn-success">${link.link_name}</button></li>
				</c:otherwise>
			</c:choose>
		</c:forEach> </ui>
	</div>
</div>