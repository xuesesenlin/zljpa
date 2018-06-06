var pageN;
$(function(){
    change_getCity();
});
//query
function click_query(){
    $('#keyworda').val("");
    $('#keywordb').val("");
    $.ajax({
        url: path+"/regionCode/regionCode/"+$('#province2').val(),
        async: false,
        type: 'GET',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $('input[name="uuid"]').val(req.data[0].uuid);
                $('#keyworda').val(req.data[0].keyworda);
                $('#keywordb').val(req.data[0].keywordb);
            }else{
                $('input[name="uuid"]').val("");
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

function click_put(){
    if($('#province2').val() == 0){
        alert("请完善地域信息");
    }else{
        $.ajax({
            url: path+"/regionCode/regionCode",
            async: false,
            type: 'post',
            data:$('#ro_form_put').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
            },
            success: function (req) {
                if(req.success){
                    alert(req.message);
                    $('#ro_form_add').hide();
                }else
                    alert(req.message);
            },
            error: erryFunction
        });
    }
}
//获取省级
function change_getCity(){
    $.ajax({
        url: path+"/regionCode/pmc/0",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#province').append("<option class=\"options\" value=\""+e.uuid+"\">"+e.names+"</option>");
                });
            }else
                alert('未查询到省市县信息');
        },
        error: erryFunction
    });
}
function change_getCity1(){
    $('#province1').find('.options').remove();
    $('#province2').find('.options').remove();
    var a = $('#province').val();
    if(a != 0){
        $.ajax({
            url: path+"/regionCode/pmc/"+a,
            async: false,
            type: 'get',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
            },
            success: function (req) {
                if(req.success){
                    $(req.data).each(function(index,e){
                        $('#province1').append("<option class=\"options\" value=\""+e.uuid+"\">"+e.names+"</option>");
                    });
                }else
                    alert('未查询到省市县信息');
            },
            error: erryFunction
        });
    }
}
//获取区级
function change_getCity2(){
    $('#province2').find('.options').remove();
    var a = $('#province1').val();
    if(a != 0){
        $.ajax({
            url: path+"/regionCode/pmc/"+a,
            async: false,
            type: 'get',
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
            },
            success: function (req) {
                if(req.success){
                    $(req.data).each(function(index,e){
                        $('#province2').append("<option class=\"options\" value=\""+e.uuid+"\">"+e.names+"</option>");
                    });
                }else
                    alert('未查询到省市县信息');
            },
            error: erryFunction
        });
    }
}
//    error
function erryFunction() {
    alert("错误!");
};