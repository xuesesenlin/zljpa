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
    +"<span class=\"btn btn-outline btn-default btn-xs\" onclick=\"click_tree(this)\" lx=\"0\" parents=\""+b+"\">"+a+"</span>"
    +"&nbsp;<a class=\"btn btn-outline btn-danger btn-xs\" onclick=\"or_del('"+b+"',this)\">删除</a>"
    +"&nbsp;<a class=\"btn btn-outline btn-warning btn-xs\" onclick=\"or_update('"+b+"','"+a+"')\">修改</a>"
    +"&nbsp;<a class=\"btn btn-outline btn-primary btn-xs\" onclick=\"or_add('"+b+"','"+c+"')\">新增</a>"
    +"</li>"
    +"</ul>";
    return h;
}
//新增
function or_add(b,c){
    $('#or_form_add').show();
    $('#or_form_update').hide();
    $('#or_parents').val(b);
    $('#or_types').val(Number(c)+1);
}
//修改
function or_update(b,a){
    $('#or_form_add').hide();
    $('#or_form_update').show();
    $('#or_name2').val(a);
    $('#or_uuid').val(b);
}
function or_submit(){
    $.ajax({
        url:path+"/pmc/pmc",
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
        url:path+"/pmc/pmc",
        dataType:"json",
        async:false,
        data:$('#or_form_update').serialize(),
        type:"put",
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
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            url:path+"/pmc/pmc/"+s,
            dataType:"json",
            async:false,
            type:"delete",
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
        url:path+"/pmc/pmc/list",
        dataType:"json",
        async:false,
        data:{"patentUuid":s},
        type:"get",
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    json_data += tree_model(e.names,e.uuid,e.types);
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