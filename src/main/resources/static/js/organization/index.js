$(document).ready(function() {

    $('#jstree1').jstree({
        'core': {
            'check_callback': true
        },
        'plugins': ['types', 'dnd'],
        'types': {
            'default': {
                'icon': 'fa fa-user'
            },
            'html': {
                'icon': 'fa fa-file-code-o'
            },
            'svg': {
                'icon': 'fa fa-file-picture-o'
            },
            'css': {
                'icon': 'fa fa-file-code-o'
            },
            'img': {
                'icon': 'fa fa-file-image-o'
            },
            'js': {
                'icon': 'fa fa-file-text-o'
            }

        }
    });

    $('#using_json').jstree({
        'core': {
            'data': [
                'Empty Folder',
                {
                    'text': 'Resources',
                    'state': {
                        'opened': true
                    },
                    'children': [{
                            'text': 'css',
                            'children': [{
                                    'text': 'animate.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'bootstrap.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'main.css',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'style.css',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'js',
                            'children': [{
                                    'text': 'bootstrap.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'inspinia.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'jquery.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'jsTree.min.js',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'custom.min.js',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'html',
                            'children': [{
                                    'text': 'layout.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'navigation.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'navbar.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'footer.html',
                                    'icon': 'none'
                                },
                                {
                                    'text': 'sidebar.html',
                                    'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        }
                    ]
                },
                'Fonts',
                'Images',
                'Scripts',
                'Templates',
            ]
        }
    });
});
var json_data="";
//点击树展开、合并
function click_tree(obj){
    $('#jgrygl_div').hide();
    var lx = $(obj).attr('lx');
    var parents = $(obj).attr('parents');
    if(lx == 0){
        $(obj).attr('lx','1');
        findByParents(parents);
        $(obj).parent().append(json_data);
    }else{
        $(obj).attr('lx','0');
        $(obj).parent().find('ul').remove();
    }
}
//tree model
function tree_model(a,b,c){
    var h = "<ul>"
    +"<li data-jstree='{\"type\":\"css\"}'>"
    +"<span class=\"btn btn-outline btn-default btn-xs\" onclick=\"click_tree(this)\" lx=\"0\" parents=\""+b+"\" data_id=\""+c+"\">"+a+"</span>"
    +"&nbsp;<a class=\"btn btn-outline btn-danger btn-xs\" onclick=\"or_del('"+b+"',this)\">删除</a>"
    +"&nbsp;<a class=\"btn btn-outline btn-warning btn-xs\" onclick=\"or_update('"+b+"','"+a+"','"+c+"')\">修改</a>"
    +"&nbsp;<a class=\"btn btn-outline btn-primary btn-xs\" onclick=\"or_add('"+b+"')\">新增</a>"
    +"&nbsp;<a class=\"btn btn-outline btn-primary btn-xs\" onclick=\"or_add_acc('"+c+"')\">管理人员</a>"
    +"</li>"
    +"</ul>";
    return h;
}
//新增
function or_add(b){
    $('#or_form_add').show();
    $('#or_form_update').hide();
    $('#or_parents').val(b);
    $('#jgrygl_div').hide();
}
//修改
function or_update(b,a,c){
    $('#or_form_add').hide();
    $('#or_form_update').show();
    $('#or_name2').val(a);
    $('#or_uuid').val(c);
    $('#jgrygl_div').hide();
}
function or_submit(){
    $.ajax({
        url:path+"/organization/add",
        dataType:"json",
        async:false,
        data:$('#or_form_add').serialize(),
        type:"post",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $('#or_name').val('');
                $('#or_form_add').hide();
                alert("成功");
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}
function or_submit2(){
    $.ajax({
        url:path+"/organization/update",
        dataType:"json",
        async:false,
        data:$('#or_form_update').serialize(),
        type:"post",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $('#or_name2').val('');
                $('#or_form_update').hide();
                alert("成功");
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}

function or_del(s,obj){
    $('#jgrygl_div').hide();
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url:path+"/organization/del",
            dataType:"json",
            async:false,
            data:{"id":s},
            type:"post",
            success:function(req){
                //请求成功时处理
                if(req.success){
                    alert("成功");
                    $(obj).parent().remove();
                }else{
                    alert(req.message);
                }
            },
            error:function(){
                //请求出错处理
                alert('错误');
            }
        });
    }
}

//根据父级查询子级
function findByParents(s){
    json_data = "";
    $.ajax({
        url:path+"/organization/findByParents",
        dataType:"json",
        async:false,
        data:{"parents":s},
        type:"post",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    json_data += tree_model(e.name,e.uuid,e.uuid);
                });
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}

//管理人员
function or_add_acc(obj){
    $('#jgrygl_div').show();
    $('#jgrygl_div').attr("data_id",obj);
    $('#jgrygl_div_table1').show();
    $('#jgrygl_div_table2').hide();
    $('#table_data1').find("tr").remove();
    $.ajax({
        url:path+"/organAcc/findByOrganId",
        dataType:"json",
        async:false,
        data:{"organId":obj},
        type:"post",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#table_data1').append("<tr><td>"+(index+1)+"</td><td>"+e.accId+"</td>"
                    +"<td><input class=\"btn btn-outline btn-danger btn-xs\" type=\"button\" value=\"删除\" onclick=\"click_del('"+e.uuid+"','"+obj+"')\"/></td></tr>");
                });
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}
//添加人员
function organ_acc_add(){
    var data_id = $('#jgrygl_div').attr("data_id");
    $('#jgrygl_div_table1').hide();
    $('#jgrygl_div_table2').show();
    $('#table_data2').find("tr").remove();
    $.ajax({
        url:path+"/organAcc/findByNotOrgan",
        dataType:"json",
        async:false,
        type:"get",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#table_data2').append("<tr><td>"+(index+1)+"</td><td>"+e.account+"</td>"
                    +"<td><input class=\"btn btn-outline btn-primary btn-xs\" type=\"button\" value=\"选择\" onclick=\"click_sz('"+e.uuid+"','"+data_id+"')\"/></td></tr>");
                });
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}
//选择人员
function click_sz(a,b){
    $.ajax({
        url:path+"/organAcc/add",
        dataType:"json",
        async:false,
        data:{"organId":b,"accId":a},
        type:"post",
        success:function(req){
            //请求成功时处理
            if(req.success){
                organ_acc_add();
            }else{
                alert(req.message);
            }
        },
        error:function(){
            //请求出错处理
            alert('错误');
        }
    });
}
//从当前组织机构中删除人员
function click_del(obj,ob){
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url:path+"/organAcc/del",
            dataType:"json",
            async:false,
            data:{"id":obj},
            type:"post",
            success:function(req){
                //请求成功时处理
                if(req.success){
                    or_add_acc(ob);
                }else{
                    alert(req.message);
                }
            },
            error:function(){
                //请求出错处理
                alert('错误');
            }
        });
    }
}