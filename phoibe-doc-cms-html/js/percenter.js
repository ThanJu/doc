
var totalRows = 0;
var currPage = 0;
var wartype = "";
function bindNearRead() {
    var data = GAL_URL+'phoibe/document/list/1/10'
    $.ajax({
        type: 'GET',
        url: data,
        dataType: 'json',
        success: function (result) {
            var total_rows = result.data.totalCount;
            totalRows = total_rows;
            var step = 0;
            var row = "";
            var step = 0;
            $.each(result.data.dataList, function (i, val) {
                step = step + 1;
                var title = val["name"];
                var format = val["format"];
                var createtime = val["createTime"];
                var tid=val["id"];
                var pagecount = val["pagecount"];
                var status = val["status"];
                var docstatus = "";
                var icon = "";
                if (format == "pdf") {
                    icon = "<i class='pdf'></i>";
                }
                else if (format == "doc" || format == "docx") {
                    icon = "<i class='doc'></i>";
                }
				else{
						icon = "<i class='exls'></i>";//
					}

                row = row + "<li>" + icon + "<a title='" + title + "' href='docdetail.html?tid=" + tid + "'>" + cutString(title, 12) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;<b class='f-blue fr' style='margin-right:8px;' ><a title='" + createtime + "'>" + cutString(createtime, 12) + "</a></b></li>";
               // alert(row);
                if (step == total_rows) {
                    var trow = "<div class='col3'><ol class='list1'>" + row + "</ol></div>";
                    $("#nearread").append(trow)
                    return;
                }
                if (step % 3 == 0) {
                    var trow = "<div class='col3'><ol class='list1'>" + row + "</ol></div>";;
                    $("#nearread").append(trow)
                    row = "";
                }
            });
        }
    });
}

function bindDym() {
        $.ajax({
            type: 'GET',
            url: GAL_URL+'phoibe/document/list/1/10',
            dataType: 'json',
            success: function (result) {
                var total_rows = result.data.totalCount;
                var step = 0;
                var row = "";
                var step = 0;
                $.each(result.data.dataList, function (i, val) {
                    step=step+1;
                    var title = val["name"];
                    var format = val["format"];
                    var pagecount = val["pagecount"];
                    var tid =val["id"]
                    var status = val["status"];
                    var docstatus = "";
                    if (status == 0) {
                        docstatus = "上传中";
                    }
                    else if (status > 1) {
                        docstatus = "上传完成";
                    }
					
					//alert(docstatus);

                    var icon = "";
                    if (format == "pdf") {
                        icon = "<i class='pdf'></i>";
                    }
                    else if (format == "doc" || format == "docx") {
                        icon = "<i class='doc'></i>";
                    }
					else{
						icon = "<i class='exls'></i>";//
					}
                    row = row + "<li>" + icon + "<a title='" + title + "' href='docdetail.html?tid=" + tid + "'>" + cutString(title, 20) + "</a>&nbsp;&nbsp;&nbsp;&nbsp;<b class='f-blue fr' style='margin-right:8px;'>" + docstatus + "</b></li>";

                    if (step == total_rows) {
                        var trow = "<div class='col3'><ol class='list1'>" + row + "</ol></div>";
                        $("#docdym").append(trow)
                        return;
                    }
                    if (step % 3 == 0) {
                        var trow = "<div class='col3'><ol class='list1'>" + row + "</ol></div>";;
                        $("#docdym").append(trow)
                        row = "";
                    }
                });
            }
        });
}

function loadData(pageindex) {

    $("#tblist-body").children().remove();

    var data = GAL_URL+'phoibe/document/list/' + pageindex + '/10?f=audit';
    data = data + wartype;
    $.ajax({
            type: 'GET',
            url: data,
            dataType: 'json',
            async: false,
            success: function (result) {//<div class='font22 title'>中国战法</div>
                var total_rows = result.data.totalCount;
                totalRows = total_rows;
                var step = 0;
                var row = "";
                $.each(result.data.dataList, function (i, val) {
                    var title = val["name"];
                    var id = val["id"];
                    var format = val['format'];
                    var pagecount = val["pagecount"];
                    var filesize = val["fileSize"];
                    var status = val["status"];
                    var createtime = val["createTime"];
                    var auditstatus = val["auditStatus"];
                    var owner = "admin";
                    var auditdate = val["auditTime"];
                    var auditor = "admin";
                    var tid = val["id"];
                    var tag = "";
                    var docstatus = "";
                    var auditstatustyle = "f-blue";
                    if (status == 101) {
                        docstatus = "上传中";
                    }

                    else if (status == 100) {
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

                   
                    var row = "<tr><td style='width:50px'><input type='radio' data-value='" + id + "' name='chksel'/></td><td><a href='docdetail.html?tid="+id+"'>" + title + "</a></td><td>" + filesize + "</td><td>" + format + "</td><td>" + tag + "</td><td>上传</td><td>" + createtime + "</td><td>" + auditdate + "</td><td class='"+auditstatustyle+"'>"+auditstatus+"</td><td></td></tr>";
                    $("#tblist-body").append(row);
                    parent.iframeLoad();
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
              ,curr:currPage
              , first: '首页'
              , last: '尾页'
              , prev: '<em>←</em>'
              , next: '<em>→</em>'
               , jump: function (obj, first) { //触发分页后的回调
                   if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                       currPage = obj.curr;
                       loadData(obj.curr - 1);
                     
                   }
               }
            });
        });
       
    }
    $(function () {
        loadData(0);
        bindDym();
        bindNearRead();

        $("#move").click(function () {
            var sel = $("#tblist-body tr td input[type='radio']:checked");
            var rowid = $(sel).attr("data-value");
            alert(rowid);
        });

        $("#del").click(function () {
            var sel = $("#tblist-body tr td input[type='radio']:checked");
            var rowid = $(sel).attr("data-value");
            alert(rowid);
        });
        $("#uploadfile").click(function () {
            $(window.parent.document).find(".bodyMask").fadeIn();
        });
        $("#wartype").change(function(){
        	var wartypevalue = $("#wartype option:selected").val();
        	wartype = "&combatType=" + wartypevalue;
        	loadData(0);
        });
    });