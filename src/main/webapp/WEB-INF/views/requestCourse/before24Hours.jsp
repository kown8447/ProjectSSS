<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/timer.css" rel="stylesheet">

<script type="text/javascript">
$(function() {
	setTimeout(() => {location.href=$("#linkAddress").val();}, $('#pageMoveSettingTime').val());
});
</script>


<div class="box-counter-container">
  <div class="box-counter hours">
	<ul class="ten">
		<c:choose>
			<c:when test="${hourTen == null}">
				<li>0</li>
      			<li>5</li>
      			<li>4</li>
      			<li>3</li>
      			<li>2</li>
      			<li>1</li>
			</c:when>
			<c:otherwise>
				<c:forEach begin="0" end="${hourTen}"  step="1" varStatus="idx">
					<li>${hourTen-(idx.count-1)}</li>
				</c:forEach>
				<c:forEach begin="${hourTen+1}" end="5" step="1" varStatus="idx">
					<li>${5-(idx.count-1)}</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
    </ul>
    <ul class="unit">
     	<c:forEach begin="0" end="${hour}" step="1" varStatus="idx">
     				<li>${hour--(idx.count-1)}</li>
		</c:forEach>
		<c:forEach begin="${hour+1}" end="9" step="1" varStatus="idx">
					 <li>${9-(idx.count-1)}</li>
		</c:forEach>
    </ul>
    <label>hours</label>
  </div>
  
  <div class="box-counter minutes">
<ul class="ten">
     <c:choose>
			<c:when test="${minuteTen == null}">
				<li>0</li>
      			<li>5</li>
      			<li>4</li>
      			<li>3</li>
      			<li>2</li>
      			<li>1</li>
			</c:when>
			<c:otherwise>
				<c:forEach begin="0" end="${minuteTen}"  step="1" varStatus="idx">
					<li>${minuteTen-(idx.count-1)}</li>
				</c:forEach>
				<c:forEach begin="${minuteTen+1}" end="5" step="1" varStatus="idx">
					<li>${5-(idx.count-1)}</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
    </ul>
    <ul class="unit">
    <c:forEach begin="0" end="${minute}" step="1" varStatus="idx">
     				<li>${minute-(idx.count-1)}</li>
		</c:forEach>
		<c:forEach begin="${minute+1}" end="9" step="1" varStatus="idx">
					 <li>${9-(idx.count-1)}</li>
		</c:forEach>
    </ul>
    <label>minutes</label>
  </div>
  
  <div class="box-counter seconds">
    <ul class="ten">
      <li>5</li>
      <li>4</li>
      <li>3</li>
      <li>2</li>
      <li>1</li>
      <li>0</li>
    </ul>
    <ul class="unit">
      <li>9</li>
      <li>8</li>
      <li>7</li>
      <li>6</li>
      <li>5</li>
      <li>4</li>
      <li>3</li>
      <li>2</li>
      <li>1</li>
      <li>0</li>
    </ul>
    <label>seconds</label>
  </div>

  <input type="hidden" id="pageMoveSettingTime" value="${pageMoveSettingTime}">
   <input type="hidden" id="linkAddress" value="${linkAddress}">

</div>
   	
