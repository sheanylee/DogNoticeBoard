<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>board</title>

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
	                	
	                		<!-- 1월10일 -->
		                    <form class="d-flex" action="board" method="post">
								<div>
									<div class="input-group">
										<input class="form-control me-sm-2" id="keyword" name="keyword" style="width:300px;" type="search" placeholder="작성자, 제목, 내용으로 검색하세요." value="${keyword}">
										<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
										<div style="display: inline-block; margin: 0 5px;  float: right;">
											<c:choose>
												<c:when test="${getDto.custom_user_nick != null}">
													<button class="btn btn-primary my-2 my-sm-0" type="button" onclick="location.href='${path}/board/write'">작성</button>
												</c:when>
											</c:choose>
								            <c:choose>
								            	<c:when test="${getDto.custom_user_auth == '1'}">
													<button class="btn btn-danger my-2 my-sm-0" type="button" onclick="removeCheckbox(${general_board.board_writer})">삭제</button>
												</c:when>
											</c:choose>
										</div>
									</div>	
								</div>
		                    </form>
	                    	<div style="margin:10px;"><font color="#4374D9">게시글 : ${count}개</font></div>
	                            <div class="table-responsive">
		                            <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-3" style="text-align:center;">
											<table class="table table-hover">
											  <thead>
											    <tr class="table-active">
											      <th scope="col"><input type="checkbox" name="checkbox" onclick="checkAll(this)"></th>
											      <th scope="col" width="10%">글 번호</th>
											      <th scope="col" width="10%">작성자</th>
											      <th scope="col" width="20%">제목</th>
											      <th scope="col" width="40%">내용</th>
											      <th scope="col" width="20%">수정일</th>
											    </tr>
											  </thead>
											  <tbody>
											  	<c:if test="${fn:length(list)==0}">
												  	<tr>
												  		<td colspan="6">검색된 게시글이 없습니다.</td>
												  	</tr>
											  	</c:if>
											  	<c:forEach var="general_board" items="${list}">
												    <tr>
												      <th scope="row"><input type="checkbox" name="checkbox" value="${general_board.board_seq}" imgValue="${general_board.board_img_path}"></th>
												      <td>${general_board.no}</td>
												      <c:set var="board_writer" value="${general_board.board_writer}" />
												      <td>${fn:substring(board_writer,0,10)}</td>
												      <c:set var="board_title" value="${general_board.board_title}" />
												      <td onclick="location.href='${path}/board/detail?board_seq=${general_board.board_seq}&pageNumber=${pageInfo.pageNumber}'">${fn:substring(board_title,0,7)}</td>
												      <c:set var="board_text" value="${general_board.board_text}" />
												      <td onclick="location.href='${path}/board/detail?board_seq=${general_board.board_seq}&pageNumber=${pageInfo.pageNumber}'">
												      	${fn:substring(board_text,0,27)} <c:if test="${general_board.review_count!=0}"><font color="red">(${general_board.review_count})</font></c:if>
													  	<c:if test="${general_board.board_img_path != null}">
													  		<img src="첨부파일 있는 글 표시하려고 걍 넣어놈">
													  	</c:if>
												      </td>
												      <fmt:formatDate var="board_mod_date" value="${general_board.board_mod_date}" type="date" pattern="yyyy-MM-dd HH:mm"/>
												      <td>${board_mod_date}</td>
												    </tr>
											    </c:forEach>
											  </tbody>
											</table>
		                            </div>
					                <div style="text-align:center;">
						                <div style="display:inline-block;">
						                	<ul class="pagination pagination-lg">
						                		${pageInfo.pagingHtml}
						                	</ul>
						                </div>
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

	<script src="${path }/resources/js/board.js"></script>

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