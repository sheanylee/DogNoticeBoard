<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    <!-- Custom fonts for this template-->
       <link href="${path}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                    </div>
                                    
                                    <!-- 1월5일 -->
                                    <form id="inputDto" action="login" method="post">
				                        <div class="input-group">
				                            <input type="text" name="custom_user_nick" id="custom_user_nick" class="form-control bg-light border-0 small" placeholder="ID"
				                                aria-label="ID" aria-describedby="basic-addon2" required="required">
				                        </div>	
				                        <div class="input-group">
				                            <input type="password" name="custom_user_pswd" id="custom_user_pswd" class="form-control bg-light border-0 small" placeholder="Password"
				                                aria-label="Password" aria-describedby="basic-addon2" required="required">
				                        </div>	
				                        <div class="input-group">
				                            <button type="submit" id="submit" class="form-control bg-dark border-0 small" 
				                                aria-label="Login" aria-describedby="basic-addon2">
				                                Login
				                            </button>
				                        </div>
			                        </form>
			                        
			                        <!-- 4월 10일 카카오 간편로그인 -->
			                        <div class="text-center">
				                        <img src="${path}/resources/img/kakao_login_medium_wide.png" onclick="kakaoLogin();" width="100%" height="40px">
			                        </div>
									
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="${path }/user/forgetPswd">Forgot Password?</a>
                                    </div>
         							<div class="text-center">
                                        <a class="small" href="${path }/user/register">Create an Account!</a>
                                    </div>
                                	</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

	<!-- 4월 10일 카카오 간편로그인 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
	Kakao.init('0e60c8b6c3cfdac7de6f3e9c58684604'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	function kakaoLogin() {
	    Kakao.Auth.login({
	      success: function (response) {
	        Kakao.API.request({
	          url: '/v2/user/me',
	          success: function (response) {
	        	  var custom_user_name = response.kakao_account.profile.nickname;
	        	  var custom_user_nick = response.kakao_account.email;
	        	  var custom_user_email = response.kakao_account.email;
	        	  var custom_user_pswd = response.id;
	        	  
	        	  console.log(custom_user_name+"/"+custom_user_nick+"/"+custom_user_pswd+"/"+custom_user_email);
					$.ajax({
						url : "idcheck",
						type : "post",
						data : custom_user_nick,
						dataType : 'text',
						contentType: 'application/json; charset=utf-8',
						success : function(cnt){
							console.log("가입된카카오계정이면1/아니면0:"+cnt);
							if(cnt==0){
								let data={'custom_user_name':custom_user_name,
										  'custom_user_nick':custom_user_nick,
										  'custom_user_email':custom_user_email,
										  'custom_user_pswd':custom_user_pswd}
								$.ajax({
									url : "kakaoRegister",
									type : "post",
									data : data,
									success : function(data){
										alert("카카오 계정으로 가입되었습니다.");
										location.href="/dogNoticeboard/main";
									},
									error : function(){
										alert("회원가입 실패");
									}
								});
							}
							let data={'custom_user_nick':custom_user_nick,
									  'custom_user_pswd':custom_user_pswd}
							$.ajax({
									url : "kakaoLogin",
									type : "post",
									data : data,
									success : function(data){
										alert("카카오 계정으로 로그인되었습니다.");
										location.href="/dogNoticeboard/main";
									},
									error : function(){
										alert("로그인 실패");
									}
							});
						}
					});
	          },
	          fail: function (error) {
	            console.log(error)
	          },
	        })
	      },
	      fail: function (error) {
	        console.log(error)
	      },
	    })
	  }
	</script>

    <!-- Bootstrap core JavaScript-->
    <script src="${path}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/resources/js/sb-admin-2.min.js"></script>



</body>

</html>