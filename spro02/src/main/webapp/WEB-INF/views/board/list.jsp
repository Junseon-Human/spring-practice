<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 목록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id="regBtn" type="button"
					class="btn btn-primary btn-xs pull-right">게시글 등록</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="board">
							<tr>
								<td>${board.bno }</td>
								<td><a href="/board/get?bno=${board.bno}">${board.title }</a></td>
								<td>${board.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate }" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updatedate }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /.table-responsive -->

				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a href="#">Previous</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }">
							<li class="page-item"><a class="page-link" href="#">${num}</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a href="#">Next</a></li>
						</c:if>
					</ul>
				</div>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script type="text/javascript">
	$(document).ready(
			function() {
				const result = "${result}";

				checkModal(result);

				// 들어온 매개변수를 전부 초기화
				history.replaceState({}, null, null);

				$("#regBtn").on("click", function() {
					self.location = "/board/register";
				})

				// 등록, 수정, 삭제 후 모달처리
				// history.state = 이전 기록이 있으면
				function checkModal(result) {
					if (result === '' || history.state) {
						return;
					}

					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + " 번이 등록 되었습니다.");
					}

					$("#myModal").modal("show");
				}
			})
</script>
<%@ include file="../includes/footer.jsp"%>