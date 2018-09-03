var totalRows = 0;
var currPage = 1;
var baseUrl = "http://47.93.62.169:8090";//var baseUrl = "http://127.0.0.1:8090";;//"http://192.168.199.139:8090";

    function loadData(pageindex) {
        $("#tblist-body").children().remove();    
        var data = baseUrl+'/phoibe/document/list/'+pageindex+'/10?1=1';
        var name = "";
        var owner = "";
        var sel = $(".widget-tab input[type='radio']:checked");
        var auditTimeBegin = "";
        var auditTimeEnd = "";
        if (sel.val() == 1) {
           name = $("#docname").val();
           owner = $("#owner").val();
           var chkValue = $("#con-value li[checked='checked']");
           auditTimeBegin = auditstartdate.value;
           auditTimeEnd = auditenddate.value;
        }
        else {
            name = $("#wait-docname").val();
            owner = $("#wait-owner").val();
            var chkValue = $("#con-value li[checked='checked']");
            auditTimeBegin = waitstartdate.value;
            auditTimeEnd = waitenddate.value;
        }

        if (name != null);
            data = data + "&name=" + name;
        if (owner != "") {
            data = data + "&userRealName=" + owner;
        }
        if (auditTimeBegin != "") {
            data = data + "&auditTimeBegin=" + auditTimeBegin;
        }
        if (auditTimeEnd != "") {
            data = data + "&auditTimeEnd=" + auditTimeEnd;
        }
        $.ajax({
            type: 'GET',
            url: data,
            dataType: 'json',
            async:false,
            success: function (result) {//<div class='font22 title'>中国战法</div>
                var total_rows = result.data.totalCount;
                totalRows = total_rows;
                if (total_rows < 1) currPage = 1;
                var step = 0;
                var row = "";
                $.each(result.data.dataList, function (i, val) {
                    var title = val["name"];
                    var id = val["id"];
                    var pagecount = val["pagecount"];
                    var filesize = val["fileSize"];
                    var status = val["status"];
                    var auditstatus = val["auditStatus"];
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
                        auditstatus="待审核";
                    }
                    else if(auditstatus==2){
                        auditstatus="审核通过"; 
                    }
                    else if(auditstatus==3){
                        auditstatus = "审核不通过";
                        auditstatustyle = "f-red";
                    }

                    var row = "<tr><td class='row-id'>" + id + "</td><td><input type='radio' name='chksel' data-value='" + id + "'/></td><td title='" + title + "'><a href='docdetail.html?tid="+id+"'>" + title + "</a></td><td>" + filesize + "</td><td>" + owner + "</td><td>" + tag + "</td><td>" + auditdate + "</td><td class='" + auditstatustyle + "'>" + auditstatus + "</td><td>" + auditor + "</td></tr>";
                    //alert(row);
                    $("#tblist-body").append(row);
                });
            }
        });

        layui.use(['laypage', 'layer'], function () {
            var laypage = layui.laypage
            , layer = layui.layer;

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
                       loadData(obj.curr);
                   }
               }
            });
        });
    }

    $(function () {

        laydate.render({
            elem: '#auditstartdate'
         , done: function (value, date, endDate) {
             $("#auditstartdate").attr("data-value", value);
         }
        });
        laydate.render({
            elem: '#auditenddate'
             , done: function (value, date, endDate) {
                 $("#auditenddate").attr("data-value", value);
             }
        });

        laydate.render({
            elem: '#waitstartdate'
             , done: function (value, date, endDate) {
                 $("#waitstartdate").attr("data-value", value);
             }
        });
        laydate.render({
            elem: '#waitenddate'
              , done: function (value, date, endDate) {
                  $("#waitenddate").attr("data-value", value);
              }
        });

        loadData(0);
        $("#btnaudit").click(function () {
            var sel = $("#tblist-body tr td input[type='radio']:checked");
            var rowid = $(sel).attr("data-value");
            
            var data = baseUrl + '/phoibe/document/update/checkpass/' + rowid;
            $.ajax({
                type: 'GET',
                url: data,
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (result.code == "SUCCESS");
                    {
                        alert("文档审批通过");
                        loadData(currPage);
                    }
                }
            });
        });

        $("#btnreback").click(function () {
            var sel = $("#tblist-body tr td input[type='radio']:checked");
            var rowid = $(sel).attr("data-value");
            var data = '/phoibe/document/update/checkrefuse/' + rowid;
            //alert(data);
            $.ajax({
                type: 'GET',
                url: data,
                dataType: 'json',
                async: false,
                success: function (result) {
                    if (result.code == "SUCCESS");
                    {
                        alert("驳回文档");
                        loadData(0);
                    }
                }
            });
        });

        $(".btnSearch").click(function () {
            currPage = 1;
            totalRows = 0;
            loadData(0);
           
        });
    });