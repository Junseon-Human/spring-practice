<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 등록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Register</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form action="/board/register" method="post">
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" name="content"></textarea>
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control" name="writer">
							</div>
							<button type="submit" class="btn btn-default">등록</button>
							<button type="reset" class="btn btn-default">취소</button>
						</form>
					</div>
					<!-- end col-lg-6 -->
				</div>
				<!-- end row -->
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel-default -->
	</div>
	<!-- end col-lg-12 -->
</div>
<!-- end-row -->

<%@ include file="../includes/footer.jsp"%>