<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 줄바꾸기 -->
<% pageContext.setAttribute("br", "<br/>");%>
<% pageContext.setAttribute("cn", "\n");%>

<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>boardDetail</title>

    <!-- Custom fonts for this template -->
    <link href="${path }/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${path }/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${path }/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
       		<c:import url="/WEB-INF/views/common/sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
				<c:import url="/WEB-INF/views/common/topbar.jsp"/>
  
                <!-- Begin Page Content -->
                 <div class="container-fluid">
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                            <tr>
		                            	<th class="table-active" width="15%">게시판 타입</th>
		                            	<th>자유게시판</th>
		                            </tr>
		                            <tr>
		                            	<th class="table-active" width="15%">작성자</th>
		                            	<th>${BoardDto.board_writer}</th>
		                            </tr>
		                            <tr>
		                            	<th class="table-active" width="15%">제목</th>
		                            	<th>${BoardDto.board_title}</th>
		                            </tr>
		                            <tr>
		                            	<th class="table-active" width="15%">내용</th>
		                            	<th>${fn:replace(BoardDto.board_text, cn, br)}</th>
		                            </tr>
						            <c:choose>
				                        <c:when test="${BoardDto.board_img_path != null}">
			                            	<tr>
			                            		<th class="table-active" width="15%">파일</th>
			                            		<th>
										            <img src="${path}/resources/img/${BoardDto.board_img_path}" height="130"><br>
						                            ${BoardDto.board_img_path}
			                            		</th>
			                            	</tr>
										</c:when>
									</c:choose>
                                </table>
		                        <button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="location.href='${path}/board/board?pageNumber=${pageNumber}'">목록</button>
		                        <c:choose>
                        			<c:when test="${getDto.custom_user_nick == BoardDto.board_writer or getDto.custom_user_auth == '1'}">
		                        		<button class="btn btn-danger my-2 my-sm-0" type="button" onclick="location.href='${path}/board/remove?board_seq=${BoardDto.board_seq}&board_img_path=${BoardDto.board_img_path}'">삭제</button>
									</c:when>
								</c:choose>
		                        <c:choose>
                        			<c:when test="${getDto.custom_user_nick == BoardDto.board_writer}">
		                        		<button class="btn btn-primary my-2 my-sm-0" type="button"onclick="location.href='${path}/board/edit?board_seq=${BoardDto.board_seq}&board_img_path=${BoardDto.board_img_path}'">수정</button>
									</c:when>
								</c:choose>
		                        <c:choose>
                        			<c:when test="${getDto.custom_user_nick != null}">
		                        		<button style="background-color:#FF85CA;" class="btn btn my-2 my-sm-0" type="button" onclick="location.href='${path}/board/recommend?board_seq=${BoardDto.board_seq}&pageNumber=${pageNumber}&custom_user_nick=${getDto.custom_user_nick}'">추천</button>
									</c:when>
								</c:choose>
								<b style="color: #4374D9">&nbsp;추천수 : ${BoardDto.recommend}</b>
                            </div>
                        
                        	<!-- 2월 27일 게시판 댓글 -->
		                    <div>
		                    <hr>
			                댓글 (${count})
			                <c:if test="${getDto.custom_user_nick != null}">
			                    <form action="writeReview" method="post">
			                   		<input type="hidden" name="review_writer" value="${getDto.custom_user_nick}">
			                    	<input type="hidden" name="board_seq" value="${BoardDto.board_seq}">
									<div class="input-group">
				                    	<textarea class="form-control" name="review_text" rows="2" style="resize:none;" maxlength="500" required="required"></textarea>
										<button class="btn btn-primary" type="submit">확인</button>
									</div>
				                </form>	
			                 </c:if>
			                    <table width="100%" style="table-layout:fixed; word-break:break-all;">
									<c:if test="${fn:length(list)==0}">
										<tr>
											<td colspan="3">댓글이 없습니다.</td>
										</tr>
									</c:if>
			                    	<c:forEach var="review" items="${list}">
				                    	<tr valign="top">
				                    		<td width="13%">${review.review_writer}</td>
				                    		<td width="70%" id="updateReview">
				                    			${fn:replace(review.review_text, cn, br)} 
				                    			<c:if test="${getDto.custom_user_nick == review.review_writer or getDto.custom_user_nick == 'admin'}">
				                    				<a style="font-size:2px; font-weight:bold; color:red;" href="deleteReview?board_seq=${BoardDto.board_seq}&review_seq=${review.review_seq}">삭제</a>
				                    			</c:if>
				                    		</td>
				                    		<fmt:formatDate var="review_mod_date" value="${review.review_mod_date}" type="date" pattern="yyyy-MM-dd HH:mm"/>
				                    		<td width="17%" align="right">${review_mod_date}</td>
				                    	</tr>
			                    	</c:forEach>
			                    </table>
		                    </div>
					        	<div style="text-align:center;">
						        	<div style="display:inline-block;">
						            	<ul class="pagination pagination-sm">
						                	${pageInfo_review.pagingHtml}
						                </ul>
						        </div>
					        </div>                    
                        </div>
                    </div>
                    
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="${path }/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${path }/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${path }/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${path }/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${path }/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="${path }/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${path }/resources/js/demo/datatables-demo.js"></script>

</body>
</html>