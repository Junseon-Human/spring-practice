<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">일정 목록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				일정맨들어요
				<button id="regBtn" type="button"
					class="btn btn-primary btn-xs pull-right">일정 등록</button>
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
							<th>일정날짜</th>
							<th>일정수행여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="todo">
							<tr>
								<td>${todo.tno }</td>
								<td><a class="move" href="${todo.tno}">${todo.title }</a></td>
								<td>${todo.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${todo.dueDate }" /></td>
								<c:if test="${todo.finished eq '0'}">
									<td>수행중</td>
								</c:if>
								<c:if test="${todo.finished eq '1'}">
									<td>완료</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- /.table-responsive -->

				<!--  search bar -->
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">

							<form class="navbar-form navbar-right" id="searchForm"
								action="/todo/list" method="get">
								<input type="hidden" name="pageNum"
									value="${pageMaker.cri.pageNum }" /> <input type="hidden"
									name="amount" value="${pageMaker.cri.amount }" />

								<div class="form-group">
									<select name="type" class="form-control">
										<option value="">--------------------</option>
										<option value="T"
											${pageMaker.cri.type eq 'T' ? 'selected' : '' }>제목</option>

										<option value="W"
											${pageMaker.cri.type eq 'W' ? 'selected' : '' }>작성자</option>
										<option value="TW"
											${pageMaker.cri.type eq 'TW' ? 'selected' : '' }>제목
											or 작성자</option>

										<option value="TWC"
											${pageMaker.cri.type eq 'TW' ? 'selected' : '' }>제목
											or 작성자</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" name="keyword" class="form-control"
										placeholder="검색" value="${pageMaker.cri.keyword }">
								</div>
								<button type="submit" class="btn btn-default">검색</button>
							</form>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
				<!--  /.search bar -->


				<div class="pull-right ">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage - 1}">Previous</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }">
							<li class="paginate_button"><a class="page-link"
								href="${num }">${num}</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>

				<form id="actionForm" action="/todo/list" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum }" /> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount }" /> <input
						type="hidden" name="type" value="${pageMaker.cri.type}" /> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}" />
				</form>

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
	$(document)
			.ready(
					function() {
						const result = "${result}";

						checkModal(result);

						// 들어온 매개변수를 전부 초기화
						history.replaceState({}, null, null);

						$("#regBtn").on("click", function() {
							self.location = "/todo/register";
						});

						/* 페이지 이동 처리 */
						const actionForm = $("#actionForm");
						$(".paginate_button a").on(
								"click",
								function(e) {
									e.preventDefault();
									console.log("paginate button click!");
									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						/* 게시글 상세 조회에 페이지 정보 넘기기 */
						$(".move")
								.on(
										"click",
										function(e) {
											e.preventDefault();
											$("input[name='tno']").remove();
											actionForm
													.append("<input type='hidden' name='tno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/todo/get");
											actionForm.submit();
										});

						/* 검색 버튼의 이벤트 처리 */
						const searchForm = $("#searchForm");
						$("#searchForm button").on(
								"click",
								function(e) {
									if (!searchForm.find("option:selected")
											.val()) {
										alert("검색 종류를 선택하세요")
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요")
										return false;
									}

									searchForm.find("input[name='pageNum']")
											.val("1");
									e.preventDefault();

									searchForm.submit();
								});

						// 등록, 수정, 삭제 후 모달처리
						// history.state = 이전 기록이 있으면
						function checkModal(result) {
							if (result === '' || history.state) {
								return;
							}

							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ " 번이 등록 되었습니다.");
							}

							$("#myModal").modal("show");
						}
					})
</script>
<%@ include file="../includes/footer.jsp"%>
</body>
</html>