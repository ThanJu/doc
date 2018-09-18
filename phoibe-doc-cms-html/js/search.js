var totalRows = 0;
var currPage = 1;
function loadData(type,pageindex) {
    $("#doc-content").children().remove()
    var docname = $("#docname").val();
    var owner = $("#owner").val();
    var data = 'phoibe/document/list/' + pageindex + '/10?1=1';
    if (getUrlString("s") == "1" && type!=1) {
        name = getUrlString("name");
    }

    if (name!=null);
    data = data + "&name=" + docname;
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
   
    if (getUrlString("s") == "1" && type!=1) {
        doctypevalue = getUrlString("format");
   }

    if (doctypevalue != "undefined" && doctypevalue != null) {
        data = data + "&format=" + doctypevalue.toLowerCase();
    }
         $.ajax({
             type: 'GET',
             url: data,
             async: false,
             dataType: 'json',
             success: function (result) {
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
                     if (status == 1) {
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
                     var row = "<div class='row'><a class='title' href='docdetail.html?tid="+id+"'>"+title+"</a><ul><li>上传时间:"+createtime+"</li><li>格式:"+format+"</li><li>46条评论</li><li>评分44</li><li>大小:"+filesize+"</li><li>文档拥有者:" + owner + "</li></ul></div>";
                     $("#doc-content").append(row);
                 });
             }
         });

         layui.use(['laypage', 'layer'], function () {
             var laypage = layui.laypage
             , layer = layui.layer;
             //自定义首页、尾页、上一页、下一页文本
             laypage.render({
                 elem: 'notice_pages'
               , count: totalRows
               , curr: currPage
               , first: '首页'
               , last: '尾页'
               , prev: '<em>←</em>'
               , next: '<em>→</em>'
                , jump: function (obj, first) { //触发分页后的回调
                    if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                         currPage = obj.curr;
                        loadData(1, obj.curr);
                    }
                }
             });
         });
     }
     $(function () {
         loadData(0,1);

         $("#btnSearch").click(function () {
             currPage = 1;
             totalRows = 0;
             loadData(1,1);
			 parent.iframeLoad();
         });
     });