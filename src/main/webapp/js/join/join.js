/*
 * @JavaScript : join
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원 가입 시 유효성 검증 처리.
 * 총 4단계 회원 가입에 대한 유효성 검증 정리.
*/
$(function() {
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 학생버튼 클릭시 다음 단계
   */
   $('#code_type').click(function() {
      $.ajax({
            url : "join.htm",
            method : "post",
            data : {
               code_type:0
            },
            dataType:"json",
            success:function(data){
               if(data.result == 'success'){
                  location.href='join1.htm';
               }else if(data.result == 'fail'){
                  alert('정보가 일치하지 않습니다.');
                  location.href='join.htm';
               }
            }
      });
   });
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 교수버튼 클릭시 다음 단계
   */
   $('#code_type1').click(function() {
      $.ajax({
            url : "join.htm",
            method : "post",
            data : {
               code_type:1
            },
            dataType:"json",
            success:function(data){
               if(data.result == 'success'){
                  location.href='join1.htm';
               }else if(data.result == 'fail'){
                  alert('정보가 일치하지 않습니다.');
                  location.href='join.htm';
               }
            }
      });
   });
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 관리자버튼 클릭시 다음 단계
   */
   $('#code_type2').click(function() {
      $.ajax({
            url : "join.htm",
            method : "post",
            data : {
               code_type:2
            },
            dataType:"json",
            success:function(data){
               if(data.result == 'success'){
                  location.href='join1.htm';
               }else if(data.result == 'fail'){
                  alert('정보가 일치하지 않습니다.');
                  location.href='join.htm';
               }
            }
      });
   });
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 단계2번째 회원가입 권한이 있는지 확인
   */
   $('#step1btn').click(function() {
      if ($("#code").val() == "") {
            alert("코드번호를 꼭 입력하세요!");
            $("#code").focus();
      }else if ($("#code_name").val() == "") {
          alert("이름을 꼭 입력하세요!");
              $("#code_name").focus();
      }else if($("#year option:selected").val() == 0 ){
         alert('년도 체크가 안됬습니다');
         $("select[name='year']:eq(0)").focus();
      }else if($("#month option:selected").val() == 0 ){
         alert('월 체크가 안됬습니다');
         $("select[name='month']:eq(0)").focus();
      }else if($("#day option:selected").val() == 0 ){
         alert('day 체크가 안됬습니다');
         $("select[name='day']:eq(0)").focus();
      }
      else{
         var month= $('#month option:selected').val()  ;
         if(month<10) {
            month = "0"+month;
         }
         var day= $('#day option:selected').val()  ;
         if(day<10) {
            day = "0"+day;
         }
         $.ajax({
               url : "join1.htm",
               method : "post",
               data : {
                  code:$('#code').val(),
                  code_name:$('#code_name').val(),
                  code_year:$('#year option:selected').val(),
                  code_month:month,
                  code_day:day
               },
               dataType:"json",
               success:function(data){
                  if(data.result == 'success'){
                     location.href='join2.htm';
                  }else if(data.result == 'fail'){
                     alert('정보가 일치하지 않습니다.');
                     location.href='join1.htm';
                  }
               }
         });
      }


   
   });
   
   var sessionid;
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 이메일 인증
   */
   $('#auth_email').click(function() {
      var re_mail = /^([\w\.-]+)@([a-z\d\.-]+)\.([a-z\.]{2,6})$/; 
      var mail = $('#member_email');
      if ($("#member_email").val() == "") {
            alert("이메일을 꼭 입력해주세용 !");
            $("#member_email").focus();
      }else if(re_mail.test(mail.val()) != true){
         alert('[Email 입력 오류] 유효한 이메일 주소를 입력해 주세요.');
         mail.focus();
      }else{
         $.ajax(
               {
                  url:"authEmail.htm",
                  data:{member_email:$('#member_email').val()},
                  dataType:"json",
                  success:function(data){
                     if(data.mailresult == 'success'){
                        sessionid = data.sessionID;
                        alert('메일이 발송되었습니다. 인증번호를 확인해 주세요.');
                     }
                  }
               }   
            );
      }
      
      
   });
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 이메일 인증번호 확인 
   */
   $('#step2btn').click(function() {
      if($('#auth_key').val() == sessionid){
         location.href="join3.htm";
      }else if($('#member_email').val() == ""){
          alert("이메일을 꼭 입력해주세용 !");
               $("#member_email").focus();
      }else if($('#auth_key').val() == ""){
          alert("인증번호를 꼭 입력해주세용 !");
               $("#auth_key").focus();
      }else{
         alert('인증실패. 이메일 주소 또는 인증번호를 다시 확인 부탁드립니다.');
         $('#member_email').focus();
      }
   });
   
   var checkid = false;
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 중복체크 script
   */
   $('#checkID').click(function() {
      $.ajax(
            {
               url:"checkID.htm",
               data:{
                  member_id:$('#member_id').val()
                  },
               dataType:"json",
               success:function(data){
                  if(data.checkresult == 'fail'){
                     checkid = false;
                     alert('중복된 아이디 입니다.');
                  }else{
                     checkid = true;
                     alert('사용 가능한 아이디 입니다.');
                  }
               }
            }
      );
   });
   /*
    * @JavaScript : join
    * @Date : 2016.11.22
    * @Author : 김영빈
    * @Desc
    * 유효성 검증
   */
   $('#complete').click(function() {
	   var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
	   var password = $("#member_pwd").val();
	   if(!/^[a-zA-Z0-9]{10,15}$/.test(password)){
		   alert('숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.');
		   $("#member_pwd").focus();
		   return false;
	   }
	   
      if($("#member_id").val() == "") {
          alert("아이디를 꼭 입력하세요!");
          $("#member_id").focus();
          return false;
      }else if(checkid == false){
          alert('아이디 중복 체크를 해주세요');
          return false;
      }else if($("#member_pwd").val() == "") {
        alert("비밀번호을 꼭 입력하세요!");
            $("#member_pwd").focus();
            return false;
      }else if($("#member_pwd_confirm").val() == "") {
          alert("비밀번호 확인을 꼭 입력하세요!");
              $("#member_pwd_confirm").focus();
              return false;
      }else if($('#member_pwd').val() != $('#member_pwd_confirm').val()){
          alert('비밀번호 체크를 해주세요');
          return false;
      }else if($('#sample6_postcode').val() == ""){
    	  alert("주소입력해주세요 !");
    	  $("#sample6_postcode").focus();
          return false;
      }else if($("#member_phone").val() == "") {
          alert("휴대폰 번호를 꼭 입력하세요!");
          $("#member_phone").focus();
          return false;
      }else if( !regExp.test( $("#member_phone").val() ) ) {
          alert("잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.");
          return false;
      }else if($(":input:radio[name=member_sex]:checked").length < 1) {
          alert("성별 선택해 !");
          $("#member_sex").focus();
          return false;
      }else if($('#files').val() == ""){
    	  alert("파일 꼭 선택해 !");
    	  $("#files").focus();
          return false;
      }else if($('#files').val() != "") {
    	  pathMiddle = $('#files').val().lastIndexOf(".");
          pathEnd = $('#files').val().length;
          extName = $('#files').val().substring(pathMiddle+1, pathEnd);
          if(extName != "png" && extName != "jpg"){
        	  alert("그림 파일 입력 양식에 맞지 않는 파일입니다.");
        	  $("#files").focus();
              return false;
          }
      }
   });
   
   
});
/*
 * @JavaScript : join
 * @Date : 2016.11.22
 * @Author : 김영빈
 * @Desc
 * 다음 api 를 통해 주소 입력
*/
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample6_address').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('sample6_address2').focus();
        }
    }).open();
}
/*
 * @JavaScript : join
 * @Date : 2016.11.22
 * @Author : 김영빈
 * @Desc
 * 이미지 파일 바로 보여주기  
*/
function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object
    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader(); //file 생성

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
           $('#list').empty();
          var span = document.createElement('span');
          span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join('');
          document.getElementById('list').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }
window.onload=function(){
  document.getElementById('files').addEventListener('change', handleFileSelect, false);
  }