
var totalRows = 0;
var currPage = 0;
//
function loadData(pageindex) {
    $("#doc-content").children().remove()
    var name = $("#docname").val();
    var winner = $("#winner").val();
    var warstate = $("#warstate").val();
    var waraddr = $("#waraddr").val();
    var wartime = $("#wartime").val();
    var loser = $("#loser").val();
    var warnum = $("#warnum").val();
    var owner = $("#owner").val();
    var data = GAL_URL+'phoibe/document/list/'+pageindex+'/10?1=1';
    
    if (name!=null);
    data = data + "&name=" + name;
    if (warstate!=null);
    data = data + "&warstate=" + warstate;
    if (waraddr!=null);
    data = data + "&waraddr=" + waraddr;
    if (wartime!=null);
    data = data + "&wartime=" + wartime;
    if (winner!=null);
    data = data + "&winner=" + winner;
    if (loser!=null);
    data = data + "&loser=" + loser;
    if (warnum!=null);
    data = data + "&warnum=" + warnum;
    if (owner != "") {
        data = data + "&userRealName=" + owner;
    }
    var wartypevalue = $("#wartype option:selected").val();
    if (wartypevalue != 0) {
        data = data + "&combatType=" + wartypevalue;
    }
    var armtypevalue = $("#armtype option:selected").val();
    if (armtypevalue != 0) {
        data = data + "&arms=" + armtypevalue;
    }
    var chkValue = $("#con-value li[checked='checked']");
    var doctypevalue = chkValue.html();
   
    if (doctypevalue != "undefined" && doctypevalue != null) {
        data = data + "&format=" + doctypevalue.toLowerCase();
    }
    //设置默认条件
    //剔除文件未上传的 statsu = 101 
     data = data + "&statsu!=101";
     
         $.ajax({
             type: 'GET',
             url: data,
             async:false,
             dataType: 'json',
             success: function (result) {//<div class='font22 title'>中国战法</div>
                 var total_rows = result.data.totalCount;
                 totalRows = total_rows;
                 var step = 0;
                 var row = "";
                 $.each(result.data.dataList, function (i, val) {
                     var title = val["name"];
                     var format = val["format"];
                     var id = val["id"];
                     var pagecount = val["pagecount"];
                     var filesize = val["fileSize"];
                     var status = val["status"];
                     var auditstatus = val["auditStatus"];
                     var createtime = val["createTime"];
                     var owner = "admin";
                     var auditdate = "2018-08-26";
                     var auditor = "admin";
                     var tag = "";
                     var docstatus = "";
                     var auditstatustyle = "f-blue";
                     if (status == 101) {
                         docstatus = "上传中";
                     }

                     else if (status == 2) {
                         docstatus = "上传完成";
                     }
                     if (auditstatus == 1) {
                         auditstatustyle = "f-red";
                         auditstatus = "待审核";
                     }
                     else if (auditstatus == 2) {
                         auditstatus = "审核通过";
                     }
                     else if (auditstatus == 3) {
                         auditstatus = "审核不通过";
                         auditstatustyle = "f-red";
                     }
                     var row = "<div class='row'><a class='title' href='docdetail.html?tid="+id+"'>" + title + "</a><ul><li>上传时间:&nbsp;&nbsp;" + createtime + "</li><li>格式:&nbsp;&nbsp;" + format + "</li><li>46条评论</li><li>评分44</li><li>大小:&nbsp;&nbsp;" + filesize + "</li><li>文档拥有者:&nbsp;&nbsp;" + owner + "</li></ul></div>";
                     $("#docmgr-content").append(row);
                 });
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
                        loadData(obj.curr - 1);
                        parent.iframeLoad();
                    }
                }
             });
         });


     }
$(function () {
         loadData(0);
         $("#btnSearch").click(function () {
             loadData(0);
			 parent.iframeLoad();
         });
     });