
var totalRows = 0;
var currPage = 1;
var tid = getUrlString("tid");
function getInfo() {

    var url = "phoibe/document/fetch/" + tid;
    //alert(url);
    $.ajax({
        type: 'GET',
        url: url,
        async: false,
        dataType: 'json',
        success: function (result) {
            if (result.code = "SUCCESS") {
                if (result.data.format == "doc" || result.data.format == "docx") {
                    $("#icontitle").attr("class", "doc");
                }
                if (result.data.format == "pdf") {
                    $("#icontitle").attr("class", "pdf");
                }
                
                var hrefurl = "openword.html?filePath="+result.data.filePath;
                
                hrefurl= encodeURI(hrefurl)
                
                var pdfval = result.data.filePath.indexOf(".pdf");//验证文件后缀是否为pdf
            	//alert(localhost+"/docword/"+filename);
                if(pdfval > 0){
                	hrefurl = "http://"+ window.location.host +"/docword/"+result.data.filePath;
                }
                $(".perview").attr("href",hrefurl);
                $("#date").html(result.data.createTime);
                $("#format").html(result.data.format);
                $("#size").html(result.data.fileSize);
                $("#owner").html(result.data.userRealName);
                $("#doctitle").html(result.data.name);
                var desc = result.data.description;
                if(desc!="undefined" && desc!="")
                $("#doc-content").html("<p>"+desc+"</p>");
            }
        }
    });
}

function loadData(pageindex) {
    $("#comm-content").children().remove();
    var docid = getUrlString("tid");
    var url = "phoibe/comment/list/"+docid+"/"+pageindex+"/10";
	//alert(url);
            $.ajax({
                type: 'GET',
                url: url,
                async: false,
                dataType: 'json',
                success: function (result) {
                    var total_rows = result.data.totalCount;
                    totalRows = total_rows;
                 
                    $.each(result.data.dataList, function (i, val) {
                        var content = val["commentContent"];
                        var createTime = val["createTime"];
                        var title = $("#doctitle").html();
                        var name = "admin";
                        var step = 0;
                        var row = "";
                        var row = "<div class='row'><div class='imghead'><img src='images/head.png' /></div><span class='name'>" + name + "</span><span class='com-date'>"+createTime+"</span><br /><span class='doctitle'>《" + title + "》</span><span id='commconten'>"+content+"</span></div>";
                        //alert(row);
                        $("#comm-content").append(row);
                    })
                }
            });

            layui.use(['laypage', 'layer'], function () {
                var laypage = layui.laypage
                , layer = layui.layer;
                laypage.render({
                    elem: 'notice_pages'
                  , count: totalRows
                  , curr: currPage//当前页,
                  , first: '首页'
                  , last: '尾页'
                  , prev: '<em>←</em>'
                  , next: '<em>→</em>'
                   , jump: function (obj, first) { //触发分页后的回调
                       if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                           currPage = obj.curr;
                           loadData(obj.curr);
                       }
                   }
                });
            });
}

function initstar() {
    var num = finalnum = tempnum = 0;
    var lis = document.getElementsByTagName("li");
    //num:传入点亮星星的个数
    //finalnum:最终点亮星星的个数
    //tempnum:一个中间值
    function fnShow(num) {
        finalnum = num || tempnum;//如果传入的num为0，则finalnum取tempnum的值
        for (var i = 0; i < lis.length; i++) {
            lis[i].className = i < finalnum ? "light" : "";//点亮星星就是加class为light的样式
        }
    }
    for (var i = 1; i <= lis.length; i++) {
        lis[i - 1].index = i;
        lis[i - 1].onmouseover = function () { //鼠标经过点亮星星。

            fnShow(this.index);//传入的值为正，就是finalnum
        }
        lis[i - 1].onmouseout = function () { //鼠标离开时星星变暗
            fnShow(0);//传入值为0，finalnum为tempnum,初始为0
        }
        lis[i - 1].onclick = function () { //鼠标点击,同时会调用onmouseout,改变tempnum值点亮星星
            tempnum = this.index;
            $("#comentscore").val(tempnum);
        }
    }
}
$(function () {
    initstar();
        getInfo();
        loadData(1);
        $(".download").click(function () {
            url = "phoibe/document/download?Id="+tid;
            window.location.href = url;
        });
        $("#submit").click(function () {
            var url = "phoibe/comment/save";
            var content = $("#comment-content").val();
            var score = $("#comentscore").val();
            var url = url + "?documentId="+tid+"&commentContent="+content+"&score="+score+"&userId=1";
            $.ajax({
                type: 'POST',
                url: url,
                async: false,
                dataType: 'json',
                success: function (result) {
                    if (result.code == "SUCCESS") {
                        alert("评论成功");
                        loadData(1);
                    }
                }
            });
        });
});   

      