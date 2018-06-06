function uploadFile(){
    var plpt = 0;
    var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
       alert("请选择文件");
       return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj); //加入文件对象
//    formFile.append("impDate",document.getElementById("impDate").value);

    $.ajax({
        url: path+"/patentDataImport/patentDataImport",
        data: formFile,
        type: "post",
        dataType: "json",
//        async:false,//同步
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        //这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
    　　xhr: function() {
    　　　　var xhr = $.ajaxSettings.xhr();
    　　　　if (xhr.upload) {
    　　　　　　xhr.upload.onprogress = function(progress) {
                    if (progress.lengthComputable) {
                        plpt = progress.loaded / progress.total * 100;
                        if(plpt == 100){
                            $('#scjd').html("上传完成，开始解析数据");
                        }else{
                            $('#scjd').html("上传进度:"+plpt+"%");
                        }
                    }
                };
                xhr.upload.onloadstart = function() {
                    $('#scjd').html("开始上传");
                };
     　　　 }
              return xhr;
     　 },
        beforeSend:function(XMLHttpRequest){
            $('#parentDataImp_div').hide();
            $('#parentDataImp_div2').show();
        },
        success: function (result) {
           alert(result.message);
           $('#parentDataImp_div').show();
           $('#parentDataImp_div2').hide();
        },
        error:function(){
           alert("导入失败");
           $('#parentDataImp_div').show();
           $('#parentDataImp_div2').hide();
        }
    })
}
//选择文件后
function change_file(obj){
    $('#file_name').text("文件名称："+obj.files[0].name);
    $('#file_size').text("文件大小(KB)："+obj.files[0].size/1024);
//    if((obj.files[0].size/1024) > 30){
//        $('#file_size').css("color","red");
//        $('#file_error').text("导入文件大小需小于等于5MB,5120KB");
//        obj.value = '';
//    }else{
//        $('#file_size').css("color","black");
//        $('#file_error').text("");
//    }
}