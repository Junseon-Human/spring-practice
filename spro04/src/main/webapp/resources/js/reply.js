/**
 *
 */

console.log("Reply Module............");
const replyService = (function () {
  function add(reply, callback, error) {
    $.ajax({
      type: "post",
      url: "/replies/new",
      data: JSON.stringify(reply),
      contentType: "application/json; charset=UTF-8",
      success: function (result, status, xhr) {
        if (callback) {
          callback(result);
        }
      },
      error: function (xhr, status, er) {
        if (error) {
          error(er);
        }
      },
    });
  }

  function getList(param, callback, error) {
    const bno = param.bno;
    const page = param.page || 1;
    // || : param.page 가 없으면 1 넣어
    $.getJSON("/replies/pages/" + bno + "/" + page + ".json", function (data) {
      if (callback) {
        // callback(data); // 댓글 목록만 가져오는경우
        callback(data.replyCnt, data.list); // 페이징 처리 된 댓글 목록 가져오기
      }
    }).fail(function (xhr, status, err) {
      if (error) {
        error(err);
      }
    });
  }

  function remove(rno, callback, error) {
    $.ajax({
      type: "delete",
      url: "/replies/" + rno,
      success: function (deleteResult, status, xhr) {
        if (callback) {
          callback(deleteResult);
        }
      },
      error: function (xhr, status, err) {
        if (error) {
          error(err);
        }
      },
    });
  }

  function update(reply, callback, error) {
    console.log("--------- update ------------" + reply.rno);

    $.ajax({
      type: "put",
      url: "/replies/" + reply.rno,
      data: JSON.stringify(reply),
      contentType: "application/JSON; charset=UTF-8",
      success: function (result, status, xhr) {
        if (callback) {
          callback(result);
        }
      },
      error: function (xhr, status, er) {
        if (error) {
          error(er);
        }
      },
    });
  }

  function get(rno, callback, error) {
    $.get("/replies/" + rno + ".json", function (result) {
      if (callback) {
        callback(result);
      }
    }).fail(function (xhr, status, err) {
      if (error) {
        error(err);
      }
    });
  }

  function displayTime(timeValue) {
    const today = new Date();
    const gap = today.getTime() - timeValue;
    let dateObj = new Date(timeValue);

    if (gap < 1000 * 60 * 60 * 24) {
      // padStart : (2, "0") 2자리로 표시하되 값이 한자리면 앞에 0 을 채움
      const hh = dateObj.getHours().toString().padStart(2, "0");
      const mm = dateObj.getMinutes().toString().padStart(2, "0");
      const ss = dateObj.getSeconds().toString().padStart(2, "0");
      return hh + ":" + mm + ":" + ss;
    } else {
      const yy = dateObj.getFullYear();
      const MM = dateObj.getMonth() + 1;
      const dd = dateObj.getDate();

      // padStart를 쓰지않을경우 삼항연산자로 0 을 추가해서 입력함 (고전적인 방법임)
      return [
        yy,
        "/",
        (MM > 9 ? "" : "0") + MM,
        "/",
        dd > 9 ? "" : "0" + dd,
      ].join("");
    }
  }

  return {
    add: add,
    getList: getList,
    remove: remove,
    update: update,
    get: get,
    displayTime: displayTime,
  };
})();
