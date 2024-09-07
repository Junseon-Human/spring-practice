<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form role="form" action="/board/modify" method="post">
							<input type="hidden" name="pageNum" value="${cri.pageNum }" />
							<input type="hidden" name="amount" value="${cri.amount }" />
							<input type="hidden" name="type" value="${cri.type }" />
							<input type="hidden" name="keyword" value="${cri.keyword }" />
							<div class="form-group">
								<label>번호</label> <input class="form-control" name="bno"
									value="${board.bno }" readonly>
							</div>
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title"
									value="${board.title }">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" name="content">${board.content }</textarea>
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control" name="writer"
									value="${board.writer }" readonly>
							</div>
							<div class="form-group">
								<label>등록일자</label> <input class="form-control" name="regdate"
									value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate }"/>'
									readonly>
							</div>
							<div class="form-group">
								<label>수정일자</label> <input class="form-control"
									name="updatedate"
									value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updatedate }"/>'
									readonly>
							</div>
							<button type="submit" data-oper="modify" class="btn btn-default">수정</button>
							<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
							<button type="submit" data-oper="list" class="btn btn-info">목록보기</button>


							<form id="operForm" action="/board/modify" method="get">
								<input type="hidden" id="bno" name="bno" value="${board.bno}" />
							</form>
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
	
$(()=> {
	const formObj = $("form");
	
	$('button').on("click", function(e){
		e.preventDefault();		// submit button의 기본 동작을 막음
		const operation = $(this).data("oper"); // data-oper의 값을 가져옴.
		console.log("operation======>", operation);
	
	if(operation === 'remove') {
		formObj.attr("action","/board/remove")
			   .attr("method", "post");
	} else if (operation === 'modify') {
		formObj.attr("action", "/board/modify")
		       .attr("method", "post");
	} else if (operation === 'list') {
		const pageNumTag = $("input[name='pageNum']").clone();
		const amountTag = $("input[name='amount']").clone();
		const typeTag = $("input[name='type']").clone();
		const keywordTag = $("input[name='keyword']").clone();
		
		formObj.attr("action","/board/list")
		       .attr("method", "get");
		formObj.empty();
		formObj.append(pageNumTag);
		formObj.append(amountTag);
		formObj.append(typeTag);
		formObj.append(keywordTag);
	}
	
	formObj.submit();
	});
});	
	
</script>

<%@ include file="../includes/footer.jsp"%>