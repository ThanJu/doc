
var totalRows = 10;
var currPage = 1;

function loadData(pageindex) {
    
    $("#tblist-body").children().remove();
/*
    var name = $("#docname").val();
    var owner = $("#owner").val();
    var data = 'phoibe/document/list/'+pageindex+'/10?1=1';

    if (name!=null);
    data = data + "&name=" + name;
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
    }*/

         /*$.ajax({
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
                     var row = "<div class='row'><a class='title' href='docdetail.html?tid="+id+"'>" + title + "</a><ul><li>上传时间:&nbsp;&nbsp;" + createtime + "</li><li>格式:&nbsp;&nbsp;" + format + "</li><li>46条评论</li><li>评分44</li><li>大小:&nbsp;&nbsp;" + filesize + "</li><li>文档拥有者:&nbsp;&nbsp;" + owner + "</li></ul></div>";
                      $("#doc-content").append(row);
                 });
             }
         });*/
		 for(var i=0;i<totalRows;i++){
			   //var row = "<div class='row'><a class='title' href='docdetail.html?tid="+id+"'>" + title + "</a><ul><li>上传时间:&nbsp;&nbsp;" + createtime + "</li><li>格式:&nbsp;&nbsp;" + format + "</li><li>46条评论</li><li>评分44</li><li>大小:&nbsp;&nbsp;" + filesize + "</li><li>文档拥有者:&nbsp;&nbsp;" + owner + "</li></ul></div>";
               //var row = "<tr><td style='width:50px'><input type='radio' data-value='" + id + "' name='chksel'/></td><td><a href='docdetail.html?tid="+id+"'>" + title + "</a></td><td>" + filesize + "</td><td>" + format + "</td><td>" + tag + "</td><td>上传</td><td>" + createtime + "</td><td>" + auditdate + "</td><td class='"+auditstatustyle+"'>"+auditstatus+"</td><td></td></tr>";
              var id = i;
			  var name="标签名称";
			  var createtime = "创建时间";
			  var hitnum = "点击量";
			  var status= "状态";
			  var row = "<tr><td class='chksel'><input type='radio' name='chksel' data-value='"+id+"'/></td><td>"+name+"</td><td>"+createtime+"</td><td>"+hitnum+"</td><td>"+status+"</td></tr>";
			   $("#tblist-body").append(row);
		 }


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
$(function () {
		$("#btnadd").click(function () {
            $(".bodyMask").fadeIn();
        });
		$("#btnmodify").click(function () {
			$(".model-title").html("修改标签");
            $(".bodyMask").fadeIn();
        });
        $(".closed").click(function () {
            $(".bodyMask").hide();
        });

         loadData(0);
         $("#btnSearch").click(function () {
             loadData(0);
			 parent.iframeLoad();
         });
     });