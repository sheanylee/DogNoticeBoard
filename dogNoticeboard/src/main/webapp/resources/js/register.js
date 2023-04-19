//1월4일 아이디 중복체크
$('#custom_user_nick').keyup(function(){
	let custom_user_nick = $('#custom_user_nick').val();
	console.log("아이디:"+custom_user_nick);
	$.ajax({
		url : "idcheck",
		type : "post",
		data : custom_user_nick,
		dataType : 'json',
		contentType: 'application/json; charset=utf-8',
		success : function(cnt){
			console.log("중복이면1:"+cnt);
			if(cnt >= 1){
				$("#id_check").html('이미 사용중인 아이디입니다.')
				$("#id_check").attr('color','#dc3545')
				$("#submit").attr('disabled',true)
			} else{
				$("#id_check").html('사용할 수 있는 아이디입니다.')
				$("#id_check").attr('color','#2fb380')
				$("#submit").attr('disabled',false)
			}
		}
	});
});

function register(){
	//1월5일 정규표현식
	let custom_user_name = /^[가-힣]+$/;
	let custom_user_birth = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	let custom_user_nick = /^[a-z0-9_-]{4,10}$/;
	let custom_user_email = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
	let custom_user_address = /(([가-힣A-Za-z·\d~\-\.]{2,}(로|길).[\d]+)|([가-힣A-Za-z·\d~\-\.]+(읍|동)\s)[\d]+)/;
	let custom_user_phone = /^[0-9]{11,11}$/;
	let custom_user_pswd = /^(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$/;	
	
	//1월4일 공백체크 //1월5일 정규표현식
	if($('#custom_user_name').val()==""){
		alert("이름을 입력하세요.");
		$('#custom_user_name').focus();
	}
	else if(!custom_user_name.test($('#custom_user_name').val())){
		alert("이름 형식이 잘못되었습니다.");
		$('#custom_user_name').focus();
	}
	else if($('#custom_user_birth').val()==""){
		alert("생년월일을 입력하세요.");
		$('#custom_user_birth').focus();
	}
	else if(!custom_user_birth.test($('#custom_user_birth').val())){
		alert("생년월일 형식이 잘못되었습니다.");
		$('#custom_user_birth').focus();
	}
	else if($('#custom_user_nick').val()==""){
		alert("아이디를 입력하세요.");
		$('#custom_user_nick').focus();
	}
	else if(!custom_user_nick.test($('#custom_user_nick').val())){
		alert("아이디 형식이 잘못되었습니다.");
		$('#custom_user_nick').focus();
	}
	else if($('#custom_user_email').val()==""){
		alert("이메일을 입력하세요.");
		$('#custom_user_email').focus();
	}
	else if(!custom_user_email.test($('#custom_user_email').val())){
		alert("이메일 형식이 잘못되었습니다.");
		$('#custom_user_email').focus();
	}
	else if($('#custom_user_address').val()==""){
		alert("주소를 입력하세요.");
		$('#custom_user_address').focus();
	}
	else if(!custom_user_address.test($('#custom_user_address').val())){
		alert("주소 형식이 잘못되었습니다.");
		$('#custom_user_address').focus();
	}
	else if($('#custom_user_phone').val()==""){
		alert("전화번호를 입력하세요.");
		$('#custom_user_phone').focus();
	}
	else if(!custom_user_phone.test($('#custom_user_phone').val())){
		alert("전화번호 형식이 잘못되었습니다.");
		$('#custom_user_phone').focus();
	}
	else if($('#custom_user_pswd').val()==""){
		alert("비밀번호를 입력하세요.");
		$('#custom_user_pswd').focus();
	}
	else if(!custom_user_pswd.test($('#custom_user_pswd').val())){
		alert("비밀번호 형식이 잘못되었습니다.");
		$('#custom_user_pswd').focus();
	}
	else if($('#repeatpassword').val()==""){
		alert("비밀번호 재확인을 입력하세요.");
		$('#repeatpassword').focus();
	}
	else if($('#custom_user_pswd').val()!=$('#repeatpassword').val()){
		alert("비밀번호 재확인이 잘못되었습니다.")
		$('#repeatpassword').focus();
	}
	
	//1월3일 컨트롤러로 데이터 넘기기
	else{
		let custom_user_name = $('#custom_user_name').val();
		let custom_user_birth = $('#custom_user_birth').val();
		let custom_user_nick = $('#custom_user_nick').val();
		let custom_user_email = $('#custom_user_email').val();
		let custom_user_address = $('#custom_user_address').val();
		let custom_user_phone = $('#custom_user_phone').val();
		let custom_user_pswd = $('#custom_user_pswd').val();
		let repeatpassword = $('#repeatpassword').val();
		let data={'custom_user_name':custom_user_name,
				  'custom_user_birth':custom_user_birth,
				  'custom_user_nick':custom_user_nick,
				  'custom_user_email':custom_user_email,
				  'custom_user_address':custom_user_address,
				  'custom_user_phone':custom_user_phone,
				  'custom_user_pswd':custom_user_pswd,
				  'repeatpassword':repeatpassword
				  }
		$.ajax({
				url : "registerToLogin",
				type : "post",
				data : data,
				success : function(data){
					alert("회원가입되었습니다.");
					location.href="/dogNoticeboard/user/login";
				},
				error : function(){
					alert("회원가입 실패");
				}
		});
		console.log(data);
	}
}

