<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">목록!</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">todo Get</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label>번호</label> <input class="form-control" name="tno"
								value="${todo.tno }" readonly>
						</div>
						<div class="form-group">
							<label>제목</label> <input class="form-control" name="title"
								value="${todo.title }" readonly>
						</div>
						<div class="form-group">
							<label>작성자</label> <input class="form-control" name="writer"
								value="${todo.writer }" readonly>
						</div>
						<div class="form-group">
							<label>일정</label> <input type="date" class="form-control" name="deuDate"
								value="${todo.dueDate }" readonly>
						</div>
						<button data-oper="modify" class="btn btn-default">수정</button>
						<button data-oper="list" class="btn btn-info">목록보기</button>

						<form id="operForm" action="/todo/modify" method="get">
							<input type="hidden" id="tno" name="tno" value="${todo.tno}" />
							<input type="hidden" name="pageNum" value="${cri.pageNum }" /> <input
								type="hidden" name="amount" value="${cri.amount }" /> <input
								type="hidden" name="type" value="${cri.type }" /> <input
								type="hidden" name="keyword" value="${cri.keyword }" />
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
			operForm.attr("action", "/todo/modify").submit();
			});
		$("button[data-oper='list']").on("click",function(){
			operForm.attr("action","/todo/list").submit();
		});
		
		});
</script>

<%@ include file="../includes/footer.jsp"%>