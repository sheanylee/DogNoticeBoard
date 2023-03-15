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

    <title>Register</title>

    <!-- Custom fonts for this template-->
    <link href="${path}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	
</head>

<body class="bg-gradient-primary">

    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            
                            <!-- 1월3일 -->
	                        <div class="input-group">
	                            <input type="text" name="custom_user_name" id="custom_user_name" class="form-control bg-light border-0 small" placeholder="Name"
	                                aria-label="Name" aria-describedby="basic-addon2">
	                            <input type="text" name="custom_user_birth" id="custom_user_birth" class="form-control bg-light border-0 small" placeholder="Birth ex)20000101"
	                                aria-label="Birth" aria-describedby="basic-addon2">
	                        </div>		
	                        <div class="input-group">
	                            <input type="text" name="custom_user_nick" id="custom_user_nick" class="form-control bg-light border-0 small" placeholder="ID 영문/숫자 4~10자"
	                                aria-label="ID" aria-describedby="basic-addon2">
	                            <div><font id="id_check" size="2"></font></div>
	                        </div>
	                        <div class="input-group">
	                            <input type="text" name="custom_user_email" id="custom_user_email" class="form-control bg-light border-0 small" placeholder="Email Address"
	                                aria-label="Email Address" aria-describedby="basic-addon2">
	                        </div>
	                        <div class="input-group">
	                            <input type="text" name="custom_user_address" id="custom_user_address" class="form-control bg-light border-0 small" placeholder="Address"
	                                aria-label="Address" aria-describedby="basic-addon2">
	                        </div>
	                        <div class="input-group">
	                            <input type="text" name="custom_user_phone" id="custom_user_phone" class="form-control bg-light border-0 small" placeholder="Phone ex)01012345678"
	                                aria-label="Phone" aria-describedby="basic-addon2">
	                        </div>
	                        <div class="input-group">
	                            <input type="password" name="custom_user_pswd" id="custom_user_pswd" class="form-control bg-light border-0 small" placeholder="Password 영문+숫자+특수문자 8자 이상"
	                                aria-label="Password" aria-describedby="basic-addon2">
	                        </div>
	                        <div class="input-group">   
	                            <input type="password" name="repeatpassword" id="repeatpassword" class="form-control bg-light border-0 small" placeholder="Repeat Password"
	                                aria-label="RepeatPassword" aria-describedby="basic-addon2">
	                        </div>
	                        <div class="input-group">
	                            <button type="button" id="submit" onclick="register()" class="form-control bg-dark border-0 small" 
	                                aria-label="Register Account" aria-describedby="basic-addon2">
	                                Register Account
	                            </button>
	                        </div>
	                                                
                            <hr>
                            <div class="text-center">
                                <a class="small" href="${path }/user/forgetPswd">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="${path }/user/login">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<script src="${path }/resources/js/register.js"></script>
	
    <!-- Bootstrap core JavaScript-->
    <script src="${path}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/resources/js/sb-admin-2.min.js"></script>


	
</body>

</html>