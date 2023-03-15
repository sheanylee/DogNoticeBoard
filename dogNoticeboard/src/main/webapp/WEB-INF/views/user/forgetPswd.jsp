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

    <title>Forgot Your Password?</title>

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
                    <div class="col-lg-5 d-none d-lg-block bg-password-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Forgot Your Password?</h1>
                                We get it, stuff happens. Just enter your email address and cellphone velow and reset your password!
                            </div>
                            <br>
							<form id="inputDto" action="forgetPswd" method="post">
		                        <div class="input-group">
		                            <input type="text" name="custom_user_nick" id="custom_user_nick" class="form-control bg-light border-0 small" placeholder="ID"
		                                aria-label="ID" aria-describedby="basic-addon2" required="required">
		                        </div>
		                        <div class="input-group">
		                            <input type="text" name="custom_user_phone" id="custom_user_phone" class="form-control bg-light border-0 small" placeholder="Phone"
		                                aria-label="Phone" aria-describedby="basic-addon2" required="required">
		                        </div>
		                        <div class="input-group">
		                            <button type="submit" id="submit" class="form-control bg-dark border-0 small" 
		                                aria-label="Register Account" aria-describedby="basic-addon2">
		                                Issuing a temporary password
		                            </button>
		                        </div>
	                        </form>                        
                            <hr>
                            <div class="text-center">
                                <a class="small" href="${path }/user/register">Create an Account!</a>
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

    <!-- Bootstrap core JavaScript-->
    <script src="${path}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path}/resources/js/sb-admin-2.min.js"></script>


	
</body>

</html>