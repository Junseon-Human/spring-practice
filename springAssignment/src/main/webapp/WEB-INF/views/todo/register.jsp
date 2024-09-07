<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">일정 등록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Todo Register</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form action="/todo/register" method="post">
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title" required>
							</div>
							<div class="form-group">
								<label>일정날짜</label> <input type="date" class="form-control" name="dueDate" required>
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control" name="writer" required>
							</div>
							<button type="submit" class="btn bbtn-primary">등록</button>
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