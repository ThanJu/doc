/**
 *
 */
function uploadCompleteUpdate(dId,filemd5,filename){
    $.ajax({
        url: "phoibe/document/completeSave/"+dId,
        type: "post",
        dataType: "json",
        contentType:"application/json;charset=UTF-8",
        success: function (data)
        {
            if(data.success){
                emptyformw();//清空表单
            }
        }
    });
}

function checkSubmit(){
    var form = $("#ajaxform");

    if($("#formthelist").find(".item").length==0){
        alert("请上传文档");
        return
    }
    if(""==$("#name").val()){
        alert("请输入标题");
        return
    }
    return true;
}
function formSubmit(filemd5,filename,fileext,filesize){
    var form = $("#ajaxform");

    var formdata ={};
    for (var i = 0; i < form.serializeArray().length; i++) {
        var key = form.serializeArray()[i].name;
        var value = form.serializeArray()[i].value;
        formdata[key] = value;
    }
    $("#formthelist").find(".item").each(function(){
        formdata.filemd5 = filemd5;
        formdata.filename = filename;
        formdata.fileext = fileext;
        formdata.filesize = filesize;
        formdata.div_file_id = $(this).attr("id").replace("form_","");
    });
    $.ajax({
        url: "phoibe/document/save",
        type: form.attr("method"),
        data: JSON.stringify(formdata),
        dataType: "json",
        async:false,
        contentType:"application/json;charset=UTF-8",
        success: function (data)
        {
            if(data.success){
                $("#"+data.div_file_id).attr("did",data.dId);
                //$("#submit").hide();
                //alert("提交成功");
            }
        }
    });
}
function emptyformw(){
    $("#ajaxform")[0].reset();

    $("#formthelist").html("");
    $("#thelist").html("");
    $("#submit").show();
    $("#picker").show();
}
$(function() {

    $(".closed").click(function () {
        $(".bodyMask").hide();
        $(".uploadTaskListBox").hide();
    });
    $(".uplaodTaskBox").click(function () {
        $(".uploadTaskListBox").fadeIn();
    });
    $(".tag-li").click(function() {
        if ($(this).hasClass('tag-li-in')) {
            $(this).removeClass('tag-li-in');
        } else {
            $(this).addClass('tag-li-in');
        }
    });
    $("#submit").click(function() {
        //checkSubmit();
    })

    var $btn = $('.ctlBtn');
    var $stopBtn = $('.stopBtn');
    var $continueBtn = $('.continueBtn');
    $($stopBtn).hide();
    $($continueBtn).hide();
    var $formthelist = $('#formthelist');
    var $thelist = $('#thelist');
    var chunkSize = 5 * 1024 * 1024;

    // HOOK 这个必须要再uploader实例化前面
    WebUploader.Uploader.register({
        'before-send-file' : 'beforeSendFile',
        'before-send' : 'beforeSend'
    }, {
        beforeSendFile : function(file) {
            console.log("beforeSendFile");
            // Deferred对象在钩子回掉函数中经常要用到，用来处理需要等待的异步操作。
            var task = new $.Deferred();
            uploader.md5File(file).progress(function(percentage) { // 及时显示进度
                console.log('计算md5进度:', percentage);
                getProgressBar(file, percentage, "MD5", "MD5");
                $('#' + file.id).find('p.state').text("文件解析中...");
                $('#form_' + file.id).find('p.state').text("文件解析中...");

            }).then(function(val) { // 完成
                console.log('md5 result:', val);
                file.md5 = val;

                formSubmit(file.md5,file.name,file.ext,file.size);

                // 模拟用户id
                // file.uid = new Date().getTime() + "_" + Math.random() * 100;
                file.uid = WebUploader.Base.guid();
                // 进行md5判断
                $.post("phoibe/uopload/checkFileMd5", {
                    filename : file.name,
                    uid : file.uid,
                    md5 : file.md5
                }, function(data) {
                    console.log(data.status);
                    var status = data.status.value;
                    task.resolve();
                    if (status == 101) {
                        // 文件不存在，那就正常流程
                    } else if (status == 100) {
                        // 忽略上传过程，直接标识上传成功；
                        uploader.skipFile(file);
                        file.pass = true;
                    } else if (status == 102) {
                        // 部分已经上传到服务器了，但是差几个模块。
                        file.missChunks = data.data;
                    }
                });
            });
            return $.when(task);
        },
        beforeSend : function(block) {
            console.log("block")
            var task = new $.Deferred();
            var file = block.file;
            var missChunks = file.missChunks;
            var blockChunk = block.chunk;
            console.log("当前分块：" + blockChunk);
            console.log("missChunks:" + missChunks);
            if (missChunks !== null && missChunks !== undefined
                && missChunks !== '') {
                var flag = true;
                for (var i = 0; i < missChunks.length; i++) {
                    if (blockChunk == missChunks[i]) {
                        console.log(file.name + ":" + blockChunk
                            + ":还没上传，现在上传去吧。");
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    task.reject();
                } else {
                    task.resolve();
                }
            } else {
                task.resolve();
            }
            return $.when(task);
        }
    });

    // 实例化
    var uploader = WebUploader.create({
        pick : {
            id:'#picker',
            multiple:false
        },
        formData : {
            uid : 0,
            md5 : '',
            chunkSize : chunkSize
        },
        // dnd: '#dndArea',
        // paste: '#uploader',
        swf : 'js/webuploader-0.1.5/Uploader.swf',
        chunked : true,
        chunkSize : chunkSize, // 字节 1M分块
        chunkRetry:5,
        threads : 1,
        server : 'phoibe/uopload/fileUpload',
        auto : false,
        // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
        disableGlobalDnd : true
        // 50 M
    });
    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function(file) {
        console.log("fileQueued");

        $thelist.html('<div id="' + file.id + '"  size="'+file.size+'" filename="' + file.name
            + '" class="item">' + '<h4 class="info">' + file.name + '</h4>'
            + '<div><progress max="100"  value="0" id="' + file.id
            + '-progress"></progress><p class="state">等待上传...</p><div>'
            + '</div>');
        $formthelist.html('<div id="form_' + file.id + '"  filesize="'+file.size+'" fileext="'+file.ext+'" filename="' + file.name
            + '" class="item">' + '<h4 class="info">' + file.name + '</h4>'
            + '<div><progress max="100"  value="0" id="form_' + file.id
            + '-progress"></progress><p class="state"></p><div>'
            + '</div>');

        $($stopBtn).hide();
        $($continueBtn).hide();
        $("#picker").hide();
    });

    // 当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次。
    uploader.onUploadBeforeSend = function(obj, data) {
        console.log("onUploadBeforeSend");
        var file = obj.file;
        data.md5 = file.md5 || '';
        data.uid = file.uid;
    };
    // 上传中
    uploader.on('uploadProgress', function(file, percentage) {
        getProgressBar(file, percentage, "FILE", "上传进度");
        var progressPercentage = percentage * 100;
        $('#' + file.id).find('p.state').text("文件上传中..."+progressPercentage+"%");
        $('#form_' + file.id).find('p.state').text("文件上传中..."+progressPercentage+"%");
    });
    // 上传返回结果
    uploader.on('uploadSuccess', function(file) {
        var text = '已上传';
        if (file.pass) {
            text = "文件妙传功能，文件已上传。"
        }
        $('#' + file.id).find('p.state').text(text);
        $('#form_' + file.id).find('p.state').text(text);
        $('#' + file.id).attr("filemd5", file.md5);

        $($stopBtn).hide();
        $($continueBtn).hide();

        var dId = $('#' + file.id).attr("did");
        uploadCompleteUpdate(dId,file.md5,file.name);
    });
    uploader.on('uploadError', function(file) {
        $('#' + file.id).find('p.state').text('上传出错');
        $('#form_' + file.id).find('p.state').text("上传出错");
    });
    uploader.on('uploadComplete', function(file) {
        // 隐藏进度条
        fadeOutProgress(file, 'MD5');
        fadeOutProgress(file, 'FILE');
    });
    // 文件上传
    $btn.on('click', function() {
        var pd = checkSubmit();
        if(pd){
            console.log("上传...");
            $($stopBtn).show();
            uploader.upload();
            console.log("上传成功");
        }

    });
    // 暂停上传
    $stopBtn.on('click', function() {
        uploader.stop(true);
        console.log("暂停上传");
        $($stopBtn).hide();
        $($continueBtn).show();
    });
    // 继续上传
    $continueBtn.on('click', function() {
        uploader.upload();
        console.log("继续上传");
        $($continueBtn).hide();
        $($stopBtn).show();

    });

    /**
     * 生成进度条封装方法
     *
     * @param file
     *            文件
     * @param percentage
     *            进度值
     * @param id_Prefix
     *            id前缀
     * @param titleName
     *            标题名
     */
    function getProgressBar(file, percentage, id_Prefix, titleName) {
        var $li = $('#' + file.id + '-progress');
        var $form_li = $('#form_' + file.id + '-progress');
        var progressPercentage = percentage * 100;
        $li.val(progressPercentage);
        $form_li.val(progressPercentage);
    }

    /**
     * 隐藏进度条
     *
     * @param file
     *            文件对象
     * @param id_Prefix
     *            id前缀
     */
    function fadeOutProgress(file, id_Prefix) {
        $('#' + file.id + '-progress').fadeOut();
    }

});