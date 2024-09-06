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
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글
					등록</button>
			</div>
			<div class="panel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno="41">
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted">2024-09-06 15:01</small>
							</div>
							<p>Good job!!!!!!!!!</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>댓글</label> <input class="form-control" name="reply"
						value="new reply!!!!" />
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name="replyer"
						value="new user" />
				</div>
				<div class="form-group">
					<label>작성일</label> <input class="form-control" name="replydate"
						value="" />
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-primary">등록</button>
				<button id="modalCloseBtn" type="button" class="btn btn-default"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
	console.log("=====================");
	console.log("JS TEST");


	let bnoValue = ${board.bno};
	
	$(()=>{
		
		
		const replyUL = $(".chat");
		showList(1);
		
		function showList(page) {
			replyService.getList({bno:bnoValue, page:page || 1}, function(list){
				let str = "";
				if (list == null || list.length == 0) {
					replyUL.html("");
					return;
				}
				for (let i = 0, len = list.length || 0; i < len; i++) {
					str += "<li class='left clearfix' data-rno='" + list[i].rno +"'>";
					str += "<div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
					str += "<small class='pull-right text-muted'>" +
					replyService.displayTime(list[i].replydate) + "</small></div>" ;
					str += "<p>" + list[i].reply + "</p></div></li>"
				}
				
				replyUL.html(str);
			});
		}
		
		const modal = $(".modal");
		const modalInputReply = modal.find("input[name='reply']");
		const modalInputReplyer = modal.find("input[name='replyer']");
		const modalInputeplydate = modal.find("input[name='replydate']");
		
		const modalModBtn = $("#modalModBtn");
		const modalRemoveBtn = $("#modalRemoveBtn");
		const modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e){
			modal.find("input").val("");
			modalInputReplyer.removeAttr("readonly");
			modalInputeplydate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalRegisterBtn.show();
			$(".modal").modal("show");
		})
		
		
		
			replyService.add(
        //reply
        {reply: "JS TEST", replyer: "tester", bno : bnoValue},
      //callback
        function(result) {
            alert("RESULT: " + result);
        }
    );
		
		replyService.getList({bno:bnoValue, page:1}, function(list){
			for (let i = 0, len = list.length || 0; i < len; i++) {
				console.log(list[i]);
			}
		});
		
		replyService.remove(14, function(count){
			console.log(count);
			
			if (count == "success") {
				alert("삭제 성공!");
			}
			
		}, function(err) {
			alert("삭제 실패" + err);
		});
		
		replyService.update({
			rno: 6,
			bno: bnoValue,
			reply: "Modified reply........."
			}, function(result) {
				alert("수정 완료");	
			});
		
		//댓글 번호로 조회
		replyService.get(25, function(data) {
			console.log(".........data : " + data);
		});
		
		
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