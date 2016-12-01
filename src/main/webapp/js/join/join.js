/*
 * @JavaScript : join
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 회원 가입 시 유효성 검증 처리.
 * 총 4단계 회원 가입에 대한 유효성 검증 정리.
 */
$(function() {
	$('#dialog').hide();
	
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
				code_type : 0
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 'success') {
					location.href = 'join1.htm';
				} else if (data.result == 'fail') {
					alert('정보가 일치하지 않습니다.');
					location.href = 'join.htm';
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
				code_type : 1
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 'success') {
					location.href = 'join1.htm';
				} else if (data.result == 'fail') {
					alert('정보가 일치하지 않습니다.');
					location.href = 'join.htm';
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
				code_type : 2
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 'success') {
					location.href = 'join1.htm';
				} else if (data.result == 'fail') {
					alert('정보가 일치하지 않습니다.');
					location.href = 'join.htm';
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
	$('#step1btn').click(
		function() {
			if ($("#code").val() == "") {
				alert("코드번호를 입력해주세요.");
				$("#code").focus();
			} else if ($("#code_name").val() == "") {
				alert("이름을입력해주세요.");
				$("#code_name").focus();
			} else if ($("#year option:selected").val() == 0
					&& $("#month option:selected").val() == 0
					&& $("#day option:selected").val() == 0) {
				alert('생년월일을 체크해주세요.');
				$("select[name='year']:eq(0)").focus();
				/*   }else if($("#month option:selected").val() == 0 ){
				      alert('월 체크가 안됬습니다');
				      $("select[name='month']:eq(0)").focus();
				   }else if($("#day option:selected").val() == 0 ){
				      alert('day 체크가 안됬습니다');
				      $("select[name='day']:eq(0)").focus();*/
			} else {
				var month = $('#month option:selected').val();
				if (month < 10) {
					month = "0" + month;
				}
				var day = $('#day option:selected').val();
				if (day < 10) {
					day = "0" + day;
				}
				$.ajax({
					url : "join1.htm",
					method : "post",
					data : {
						code : $('#code').val(),
						code_name : $('#code_name').val(),
						code_year : $('#year option:selected').val(),
						code_month : month,
						code_day : day
					},
					dataType : "json",
					success : function(data) {
						if (data.result == 'success') {
							location.href = 'join2.htm';
						} else if (data.result == 'fail') {
							alert('정보가 일치하지 않습니다.');
							location.href = 'join1.htm';
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
			alert("이메일을 입력해주세요.");
			$("#member_email").focus();
		} else if (re_mail.test(mail.val()) != true) {
			alert('[Email 입력 오류] 유효한 이메일 주소를 입력해 주세요.');
			mail.focus();
		} else {
			$.ajax({
				url : "authEmail.htm",
				data : {
					member_email : $('#member_email').val()
				},
				beforeSend:function(){
					$('#email_layerpop').modal('toggle');
				},
				dataType : "json",
				success : function(data) {
					if (data.mailresult == 'success') {
						sessionid = data.sessionID;
						alert('메일이 발송되었습니다. 인증번호를 확인해 주세요.');
					}
				},
				complete : function(){
					$('#email_layerpop').modal('toggle');
				}
			});
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
		if ($('#auth_key').val() == sessionid) {
			location.href = "join3.htm";
		} else if ($('#member_email').val() == "") {
			alert("이메일을 입력해주세요.");
			$("#member_email").focus();
		} else if ($('#auth_key').val() == "") {
			alert("인증번호를 입력해주세요.");
			$("#auth_key").focus();
		} else {
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
		$.ajax({
			url : "checkID.htm",
			data : {
				member_id : $('#member_id').val()
			},
			dataType : "json",
			success : function(data) {
				if (data.checkresult == 'fail') {
					checkid = false;
					alert('중복된 아이디 입니다.');
				} else {
					checkid = true;
					alert('사용 가능한 아이디 입니다.');
				}
			}
		});
	});
	/*
	 * @JavaScript : join
	 * @Date : 2016.11.22
	 * @Author : 김영빈
	 * @Desc
	 * 유효성 검증
	 */
	$(function() {
		var idcheck = 1;
		var reg_pw = /^.*(?=.{6,15})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		var reg_phone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		var reg_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		$('#member_pwd').keyup(
				function() {
					if (!reg_pw.test($('#member_pwd').val())) {
						$("#p-error").html(
								"<div style='color:red'>비밀번호 형식을 맞춰주세요</div>");
					} else {
						$("#p-error").empty();
					}
				});

		$('#member_pwd_confirm')
				.keyup(
						function() {
							if ($("#member_pwd").val() != $(
									"#member_pwd_confirm").val()) {
								$("#p2-error")
										.html(
												"<div style='color:red'>비밀번호가 일치하지 않습니다</div>");
							} else {
								$("#p2-error").empty();
							}
						});

		$('#member_phone')
				.keyup(
						function() {
							if (!reg_phone.test($('#member_phone').val())) {
								$("#ph-error")
										.html(
												"<div style='color:red'>핸드폰번호 형식을 맞춰주세요</div>");
							} else {
								$("#ph-error").empty();
							}
						});
	});

	$('#complete').click(
			function() {
				if ($("#member_id").val() == "") {
					alert('아이디를 입력해주세요');
					$('#member_id').focus();
					return false;
				}

				if (checkid == false) {
					alert('아이디중복체크를 해주세요');
					$('#idc-error').html(
							"<div style='color:red'>아이디 중복 체크 해주세요</div>");
					$('#member_id').focus();
					return false;
				}

				if ($("#member_pwd").val() == "") {
					alert('비밀번호를 입력해주세요');
					$('#member_pwd').focus();
					return false;
				}

				if ($('#member_pwd_confirm').val() != $('#member_pwd_confirm')
						.val()) {
					$('#member_pwd_confirm').focus();
					return false;
				}

				if ($(':radio[name="member_sex"]:checked').length < 1) {
					alert('성별을 선택해주세요');
					$('#member_sex').focus();
					return false;
				}

				if ($("#member_phone").val() == "") {
					alert('핸드폰을 입력해주세요');
					$('#member_phone').focus();
					return false;
				}

				if ($('#sample6_postcode').val() == "") {
					alert('주소를 입력해주세요');
					$('#sample6_postcode').focus();
					return false;
				}

				if ($('#sample6_address').val() == "") {
					$('#sample6_address').focus();
					return false;
				}
				if ($('#files').val() == "") {
					alert("파일 꼭 선택해 !");
					$("#files").focus();
					return false;
				}
				if ($('#files').val() != "") {
					pathMiddle = $('#files').val().lastIndexOf(".");
					pathEnd = $('#files').val().length;
					extName = $('#files').val().substring(pathMiddle + 1,
							pathEnd);
					if (extName != "png" && extName != "jpg") {
						alert("사진파일이 유효하지 않습니다.");
						$("#files").focus();
						return false;
					}
				}
				$('#memberjoin').submit();
				alert("회원가입이 완료되었습니다.")
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
		oncomplete : function(data) {
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
			if (data.userSelectedType === 'R') {
				//법정동명이 있을 경우 추가한다.
				if (data.bname !== '') {
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if (data.buildingName !== '') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
							: data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
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
				span.innerHTML = [ '<img class="thumb" src="', e.target.result,
						'" title="', escape(theFile.name), '"/>' ].join('');
				document.getElementById('list').insertBefore(span, null);
			};
		})(f);

		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}
window.onload = function() {
	document.getElementById('files').addEventListener('change',
			handleFileSelect, false);
}

/*
 * @JavaScript : join
 * @Date : 2016.11.28
 * @Author : 송아름
 * @Desc
 * 회원가입 취소버튼을 클릭했을 경우 이벤트  
 */
$(function() {
	$('#cancel').click(function() {
		var con = confirm("정말 취소하겠습니까?");
		if (con == true) {
			location.href = "../login.htm";
		}
	});

});


/*
 * @JavaScript : join
 * @Date : 2016.11.30
 * @Author : 송아름
 * @Desc
 * 파일 업로드 할 경우 파일 경로와 이름 표시 
*/
(function(global, $) {

	$(makeObjFile);

	function makeObjFile() {
		var inputFile = CustomFiletype();
		inputFile.init($('.filetype'));
	}

	function CustomFiletype() {
		if (this === window)
			return new CustomFiletype;
		this.$fileBox = null;
		this.$fileUpload = null;
	}

	CustomFiletype.prototype = {

		'init' : function(fileClass) {
			this.$fileBox = fileClass || $('.filetype');
			this.initEvent();
		},

		'initEvent' : function() {
			this.fileUpload();
		},

		'fileUpload' : function() {
			var _self = this;

			$.each(_self.$fileBox, function(idx, item) {

				var _$fileBox = _self.$fileBox.eq(idx), _$fileType = _$fileBox
						.find('input[type=file]'), _$fileText = _$fileBox
						.find('input[type=text]');
				_$fileText.attr('disabled', 'disabled');

				_$fileType.on('change', function() {
					var filePath = $(this).val();
					_$fileText.val(filePath);
					;
				})
			})
		}
	}
})(window, jQuery);
