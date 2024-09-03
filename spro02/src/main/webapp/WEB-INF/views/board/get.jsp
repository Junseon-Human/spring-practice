<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 상세조회</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Get</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>번호</label> <input class="form-control" name="bno"
								value="${board.bno }" readonly>
						</div>
						<div class="form-group">
							<label>제목</label> <input class="form-control" name="title"
								value="${board.title }" readonly>
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="5" name="content" readonly>${board.content }</textarea>
						</div>
						<div class="form-group">
							<label>작성자</label> <input class="form-control" name="writer"
								value="${board.writer }" readonly>
						</div>
						<button data-oper="modify" class="btn btn-default">수정</button>
						<button data-oper="list" class="btn btn-info">목록보기</button>
						
						<form id="operForm" action="/board/modify" method="get">
							<input type="hidden" id="bno" name="bno" value="${board.bno}" />
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

<script type="text/javascript">
	$(()=>{
		const operForm = $("#operForm");
		$("button[data-oper='modify']").on("click", function(){
			operForm.attr("action", "/board/modify").submit();
			});
		$("button[data-oper='list']").on("click",function(){
			operForm.attr("action","/board/list").submit();
		});
		});
</script>

<%@ include file="../includes/footer.jsp"%>