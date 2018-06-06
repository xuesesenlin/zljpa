var pageN;
$(function(){
    init("1");
});
function page(i){
    if(i == 0)
        pageN = Number(pageN)-1;
    else
        pageN = Number(pageN)+1;

    pageN = Number(pageN) < 1 ? pageN = 1 : pageN;
    init(pageN);
}
//init
function init(pageNow){
    pageN = pageNow;
    $('#pageN').text("当前页数:"+pageN);
    $('#table_data').find('tr').remove();
    $.ajax({
        url: "/role/initData/"+pageNow,
        async: false,
        type: 'post',
        data:{"seach":""},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                	var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                	+"<td style=\"text-align: center;\">"+e.name+"</td>"
                	+"<td style=\"text-align: center;\">"
                	+"<input class=\"btn btn-outline btn-danger btn-xs\" type=\"button\" onclick=\"ro_del('"+e.uuid+"')\" value=\"删除\"/>&nbsp;"
                	+"<input class=\"btn btn-outline btn-warning btn-xs\" type=\"button\" onclick=\"ro_update('"+e.uuid+"','"+e.name+"')\" value=\"修改\"/>&nbsp;"
                	+"<input class=\"btn btn-outline btn-primary btn-xs\" type=\"button\" onclick=\"ro_gl_acc('"+e.uuid+"',this)\" value=\"管理人员\"/>&nbsp;"
                	+"<input class=\"btn btn-outline btn-primary btn-xs\" type=\"button\" onclick=\"ro_gl_qx('"+e.uuid+"',this)\" value=\"管理权限\"/>"
                	+"</td>"
                    +"</tr>";
                    $('#table_data').append(h);
                });
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

//    add
function ro_add(){
    $('#ro_form_add').show();
    $('#ro_form_update').hide();
}
function ro_submit(){
    $.ajax({
        url: "/role/add",
        async: false,
        type: 'post',
        data:$('#ro_form_add').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $('#ro_form_add').hide();
            init(pageN);
        },
        error: erryFunction
    });
}
//del
function ro_del(obj){
    tyyc();
    $('#table_data').find('input[type=\"button\"]').each(function(index,e){
        $(e).css("background-color","");
    });
	var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url: "/role/del",
            async: false,
            type: 'post',
            data:{"id":obj},
            dataType: 'json',
            timeout: 1000,
            cache: false,
            beforeSend:function(XMLHttpRequest){
            },
            success: function (req) {
                init(pageN);
            },
            error: erryFunction
        });
    }
}
//    update
function ro_update(a,b){
    tyyc();
    $('#table_data').find('input[type=\"button\"]').each(function(index,e){
        $(e).css("background-color","");
    });
    $('#ro_form_update').show();
    $('#ro_uuid').val(a);
    $('#ro_name2').val(b);
}
function ro_submit2(){
    $.ajax({
        url: "/role/update",
        async: false,
        type: 'post',
        data:$('#ro_form_update').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $('#ro_form_update').hide();
            init(pageN);
        },
        error: erryFunction
    });
}

//管理人员
function ro_gl_acc(obj,ob){
    tyyc();
    $('#ro_acc_div').show();
    $('#table_data').find('input[type=\"button\"]').each(function(index,e){
        if(e == ob){
            $(e).css("background-color","green");
        }else{
            $(e).css("background-color","");
        }
    });
    findAcc(obj);
}
//管理权限
function ro_gl_qx(obj,ob){
    tyyc();
    $('#ro_or_div').show();
    $('#table_data').find('input[type=\"button\"]').each(function(index,e){
        if(e == ob){
            $(e).css("background-color","green");
        }else{
            $(e).css("background-color","");
        }
    });
    findJur(obj);
}
//获取所有权限
function findJur(obj){
    $('#table_data2').find('tr').remove();
    $.ajax({
        url: "/jurisdiction/initData/1",
        async: false,
        type: 'post',
        data:{"seach":""},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                    +"<td style=\"text-align: center;\">"+e.name+"</td>"
                    +"<td style=\"text-align: center;\"><select onchange=\"chang_sub(this)\" parent_id=\""+obj+"\" data_id=\""+e.uuid+"\" style=\"color:red;\"><option value=\"0\">×</option><option value=\"1\">√</option></select></td>"
                    +"</tr>";
                    $('#table_data2').append(h);
                });
                findByRoleId(obj);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//获取已选择权限
function findByRoleId(obj){
    $.ajax({
        url: "/roleDetailed/findByRoleId",
        async: false,
        type: 'post',
        data:{"roleId":obj},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $('#table_data2').find('tr').find("select").each(function(index,e){
                    var id = $(e).attr("data_id");
                    $(req.data).each(function(index,e1){
                        if(e1.jurId == id){
                            $(e).val("1");
                            $(e).css("color","green");
                        }
                    });
                });
            }
        },
        error: erryFunction
    });
}

//更改权限
function chang_sub(obj){
    var parent_id = $(obj).attr('parent_id');
    var data_id = $(obj).attr('data_id');
    $.ajax({
        url: "/roleDetailed/setRD",
        async: false,
        type: 'post',
        data:{"roleId":parent_id,"jurId":data_id},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                if(req.data == 0){
                    $(obj).val("0");
                    $(obj).css("color","red");
                }else{
                    $(obj).val("1");
                    $(obj).css("color","green");
                }
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//反选权限
function chang_sub_fx(obj){
    $(obj).attr("disabled","disabled");
    $('#table_data2').find('select').each(function (index,e){
        chang_sub(e);
    });
    $(obj).removeAttr("disabled");
}
//获取所有人员
function findAcc(obj){
    $('#table_data3').find('tr').remove();
    $.ajax({
        url: "/account/find",
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
                    var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                    +"<td style=\"text-align: center;\">"+e.account+"</td>"
                    +"<td style=\"text-align: center;\"><select onchange=\"chang_sub2(this)\" parent_id=\""+obj+"\" data_id=\""+e.uuid+"\" style=\"color:red;\"><option value=\"0\">×</option><option value=\"1\">√</option></select></td>"
                    +"</tr>";
                    $('#table_data3').append(h);
                });
                findByRoleId2(obj);
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}
//获取已选择人员
function findByRoleId2(obj){
    $.ajax({
        url: "/roleAcc/findByRoleId",
        async: false,
        type: 'post',
        data:{"roleId":obj},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $('#table_data3').find('tr').find("select").each(function(index,e){
                    var id = $(e).attr("data_id");
                    $(req.data).each(function(index,e1){
                        if(e1.accountId == id){
                            $(e).val("1");
                            $(e).css("color","green");
                        }
                    });
                });
            }
        },
        error: erryFunction
    });
}
//更改人员
function chang_sub2(obj){
    var parent_id = $(obj).attr('parent_id');
    var data_id = $(obj).attr('data_id');
    $.ajax({
        url: "/roleAcc/setRD",
        async: false,
        type: 'post',
        data:{"roleId":parent_id,"accountId":data_id},
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                if(req.data == 0){
                    $(obj).val("0");
                    $(obj).css("color","red");
                }else{
                    $(obj).val("1");
                    $(obj).css("color","green");
                }
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

//统一隐藏
function tyyc(){
    $('#ro_form_add').hide();
    $('#ro_form_update').hide();
    $('#ro_acc_div').hide();
    $('#ro_or_div').hide();
}

//    error
function erryFunction() {
    alert("错误!");
};