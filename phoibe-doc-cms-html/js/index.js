var baseUrl = "http://47.93.62.169:8090";
//""http://47.93.62.169:8090";//
function bindZhanfa() {
    $("#zgzhanfa").children().remove();
    $.ajax({
        type: 'GET',
        url: baseUrl + '/phoibe/document/list/user/1/1',// '/phoibe/document/list/user/1/2',
        dataType: 'json',
        success: function (result) {//<div class='font22 title'>中国战法</div>
            var total_rows = result.data.totalCount;
            var step = 0;
            var row = "";
            $.each(result.data, function (i, val) {
                var title = val["name"];
                var pagecount = val["pagecount"];
                var status = val["status"];
                var tid = val["id"];
                var docstatus = "";
                if (status == 0) {
                    docstatus = "上传中";
                }
                else{
                    docstatus = "上传完成";
                }

                var row = "<li><i class='i-star'></i><a title='" + title + "' href='docdetail.html?tid=" + tid + "'>" + cutString(title, 10) + "</a></li><li>" + pagecount + "</li><li>" + docstatus + "</li>";
                $("#zgzhanfa").append(row);

            });
        }
    });
}
function bindResouDoc() {//<div class='font22 title'>中国战法</div>
    $.ajax({
        type: 'GET',
        url: baseUrl + '/phoibe/document/list/1/19?f=hot',

        dataType: 'json',
        success: function (result) {//<div class='font22 title'>中国战法</div>
            var total_rows = result.data.totalCount;
            var step = 0;
            var row = "";
            $.each(result.data.dataList, function (i, val) {
                var title = val["name"];
                var format = val["format"];
                var tid = val["id"];
                step = step + 1;

                var icon = "";
                if (format == "pdf") {
                    icon = "<i class='pdf'></i>";
                }
                else if (format == "doc" || format == "docx") {
                    icon = "<i class='doc'></i>";
                }
                else {
                    icon = "<i class='exls'></i>";
                }

                row = row + "<li>" + icon + "<a href='docdetail.html?tid=" + tid + "'>" + title + "</a></li>";
                if (step == total_rows) {
                    var trow = "<div class='col3  clearfix'><div class='ul-header'><div class='ul-img fl'><img src='images/index-head.png'/></div><div class='ul-header-right fl'><div class='ul-header-name'>李明</div><span class='ul-header-docnum'>10689</span>篇文档</div></div><ul class='list1'>" + row + "</ul></div>";
                    $("#resou-doc").append(trow)
                    return;
                }
                if (step % 3 == 0) {
                    var trow = "<div class='col3  clearfix'><div class='ul-header'><div class='ul-img fl'><img src='images/index-head.png'/></div><div class='ul-header-right fl'><div class='ul-header-name'>李明</div><span class='ul-header-docnum'>10689</span>篇文档</div></div><ul class='list1'>" + row + "</ul></div>";;
                    $("#resou-doc").append(trow)
                    row = "";
                }

            });
        }
    });
}

function bindRecommDoc() {
    $.ajax({
        type: 'GET',
        url: baseUrl + '/phoibe/document/list/1/19?f=handpick',

        dataType: 'json',
        success: function (result) {//<div class='font22 title'>中国战法</div>
            var total_rows = result.data.totalCount;
            var step = 0;
            var row = "";
            $.each(result.data.dataList, function (i, val) {
                var title = val["name"];
                var format = val["format"];
                var tid = val["id"];
                step = step + 1;

                var icon = "";
                if (format == "pdf") {
                    icon = "<i class='pdf'></i>";
                }
                else if (format == "doc" || format == "docx") {
                    icon = "<i class='doc'></i>";
                }
                else {
                    icon = "<i class='exls'></i>";
                }
                row = row + "<li>" + icon + "<a href='docdetail.html?tid=" + tid + "'>" + title + "</a></li>";
                if (step == total_rows) {
                    var trow = "<div class='col3'><ul class='list1'>" + row + "</ul></div>";;
                    $("#recom-doc").append(trow)
                    return;
                }
                if (step % 3 == 0) {
                    var trow = "<div class='col3'><ul class='list1'>" + row + "</ul></div>";;
                    $("#recom-doc").append(trow)
                    row = "";
                }

            });
        }
    });
}
function bindDym() {
    //alert(baseUrl + '/phoibe/document/list/user/1/10');
    $.ajax({
        type: 'GET',
        url: baseUrl + '/phoibe/document/list/user/1/10',
        //url: 'http://199.139.199.154:8090/phoibe/document/selectDoucumentList',
        dataType: 'json',
        success: function (result) {
            $.each(result.data, function (i, val) {
                var docname = val["name"];
                var createTime = val["createTime"];
                var tid = val["id"];
                var d = new Date();
                var curTime = d.getTime();
                var username = "admin";
                var i = 1;

                var date3 = curTime - Date.parse(createTime);  //时间差的毫秒数

                //计算出相差天数
                var days = Math.floor(date3 / (24 * 3600 * 1000));
                var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数
                var minutes = Math.floor(leave2 / (60 * 1000));

                var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
                var hours = Math.floor(leave1 / (3600 * 1000))
                //计算相差分钟数
                var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
                var minutes = Math.floor(leave2 / (60 * 1000))
                //计算相差秒数
                var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
                var seconds = Math.round(leave3 / 1000)
                var minutesTip = "";
                if (seconds > 0) minutesTip = seconds + " 秒前上传";
                if (minutes > 0) minutesTip = minutes + "分钟" + minutesTip;
                if (hours > 0) minutesTip = hours + "小时" + minutesTip;
                if (days > 0) minutesTip = days + "天" + minutesTip;

                var row = "<li><a href='docdetail.html?tid=" + tid + "' title='" + docname + "'>" + cutString(docname, 16) + "</a></li><li><span>" + username + "</span></li><li>" + minutesTip + "</li>";
                $("#lst-dym").append(row);
            });
        }
    });
}
function getDocNum() {
    $.ajax({
        type: 'GET',
        url: baseUrl + '/phoibe/document/count',
        dataType: 'json',
        success: function (result) {
            if (result.code == "SUCCESS");
            //alert(result.data);
            $("#docnum").html(result.data);
        }
    });
}

$(function () {
    getDocNum();
    bindRecommDoc();
    bindDym();
    bindZhanfa();
    bindResouDoc();
    $("#upload").click(function () {
        $(".bodyMask").fadeIn();
    });
    $(".closed").click(function () {
        $(".bodyMask").hide();
    });

    $("#btnSearch").click(function () {
        var searchKey = $("#search-key").val();
        var chkValue = $("#con-value li[checked='checked']");

        var url = 'search.html?s=1';
        var doctypevalue = chkValue.html();
        url = url + "&name=" + searchKey;
        if (doctypevalue != null)
            url = url + "&format=" + doctypevalue.toLowerCase();
        window.location.href = url;
        /*if ($("#frm-main").attr("src") == null) {
            window.location.href = url;
        }
        $("#frm-main").attr("src", url);
        document.parent().getElementById("frm-main").height = 0;

        document.parent().getElementById("frm-main").height=document.getElementById("frm-main").contentWindow.document.body.scrollHeight+100;
        alert(document.parent().html());*/
    });

    $("#submit").click(function () {
        var form = $("#ajaxform");
        var path = baseUrl + "/phoibe/document/upload";
        form.attr("action", path)
        var files = $("#file").get(0).files[0]; //获取file控件中的内容
        var fd = new FormData();
        if ("" == $("#title").val()) {
            alert("请输入标题");
            return
        }
        fd.append("title", $("#title").val());
        fd.append("combat_type", $("#combat_type").val());
        fd.append("arms", $("#arms").val());
        fd.append("description", $("#description").val());
        fd.append("file", files);
        $.ajax({
            url: path,
            type: form.attr("method"),
            data: fd,
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.success) {
                    alert("提交成功");
                }
            }
        });
    });
});