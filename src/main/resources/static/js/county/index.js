$(function(){
//日期控件
//日期控件
laydate.render({
  elem: '#drnyStar' //指定元素
  ,type: 'month' //指定到月份
  ,format: 'yyyy-MM'//格式
});
laydate.render({
  elem: '#drnyEnd' //指定元素
  ,type: 'month' //指定到月份
  ,format: 'yyyy-MM'//格式
});
laydate.render({
  elem: '#sqrqStar' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});
laydate.render({
  elem: '#sqrqEnd' //指定元素
  ,format: 'yyyy-MM-dd'//格式
});
//获取专利类型
    getPatentTypes();
//    申请人类型
    getPatentPeoTypes();
//    获取省份
    getSSX('#szss','0');
getCompanyTypes();
});
//获取企业类型
function getCompanyTypes(){
    $.ajax({
        url: path+"/companyTypes/companyTypes/",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){},
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    $('#qylx').append("<option value=\""+e.companyTypes+"\">"+e.companyTypes+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
function query_data(){
    $('#table_data').find('tr').remove();
    $('#table_data2').find('tr').remove();
    $('#table_data3').find('tr').remove();
    $.ajax({
        url: path+"/county/county",
        async: false,
        type: 'GET',
        dataType: 'json',
        data:$('#form_query').serialize(),
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){},
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    pjsj(e);
                });
                alert('数据生成成功');
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function pjsj(obj){
    if(obj.applyAuthorization.search('申请') != -1){
        var i = 0;
        $('#table_data').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                i = 1;
        });
        if(i == 0)
            $('#table_data').append('<tr><td>'+obj.area+'</td><td>0</td><td>0</td><td>0</td><td>0</td></tr>');

        $('#table_data').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                if(obj.patentTypes.search('发明'))
                    $(e1).find('td:eq(1)').text(Number($(e1).find('td:eq(1)').text())+Number(1));
                if(obj.patentTypes.search('实用新型'))
                    $(e1).find('td:eq(2)').text(Number($(e1).find('td:eq(2)').text())+Number(1));
                if(obj.patentTypes.search('外观'))
                    $(e1).find('td:eq(3)').text(Number($(e1).find('td:eq(3)').text())+Number(1));
                $(e1).find('td:eq(4)').text(Number($(e1).find('td:eq(1)').text())
                                            +Number($(e1).find('td:eq(2)').text())
                                            +Number($(e1).find('td:eq(3)').text()));
        });
    }
    if(obj.applyAuthorization.search('授权') != -1){
        var i = 0;
        $('#table_data2').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                i = 1;
        });
        if(i == 0)
            $('#table_data2').append('<tr><td>'+obj.area+'</td><td>0</td><td>0</td><td>0</td><td>0</td></tr>');

        $('#table_data2').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                if(obj.patentTypes.search('发明'))
                    $(e1).find('td:eq(1)').text(Number($(e1).find('td:eq(1)').text())+Number(1));
                if(obj.patentTypes.search('实用新型'))
                    $(e1).find('td:eq(2)').text(Number($(e1).find('td:eq(2)').text())+Number(1));
                if(obj.patentTypes.search('外观'))
                    $(e1).find('td:eq(3)').text(Number($(e1).find('td:eq(3)').text())+Number(1));
                $(e1).find('td:eq(4)').text(Number($(e1).find('td:eq(1)').text())
                                            +Number($(e1).find('td:eq(2)').text())
                                            +Number($(e1).find('td:eq(3)').text()));
        });
    }
    if(obj.applyAuthorization.search('有效') != -1){
        var i = 0;
        $('#table_data3').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                i = 1;
        });
        if(i == 0)
            $('#table_data3').append('<tr><td>'+obj.area+'</td><td>0</td><td>0</td><td>0</td><td>0</td></tr>');

        $('#table_data3').find('tr').each(function(index1,e1){
            if($(e1).find('td:eq(0)').text() == obj.area)
                if(obj.patentTypes.search('发明'))
                    $(e1).find('td:eq(1)').text(Number($(e1).find('td:eq(1)').text())+Number(1));
                if(obj.patentTypes.search('实用新型'))
                    $(e1).find('td:eq(2)').text(Number($(e1).find('td:eq(2)').text())+Number(1));
                if(obj.patentTypes.search('外观'))
                    $(e1).find('td:eq(3)').text(Number($(e1).find('td:eq(3)').text())+Number(1));
                $(e1).find('td:eq(4)').text(Number($(e1).find('td:eq(1)').text())
                                            +Number($(e1).find('td:eq(2)').text())
                                            +Number($(e1).find('td:eq(3)').text()));
        });
    }
}
//导出查询结果
function export_result_data(){
    var a = "";
    $('#table_data').find('tr').each(function(index,e){
        $(e).find('td').each(function (index1,e1){
            a += $(e1).text()+",";
        });
        a += "]";
    });
    var b = "";
    $('#table_data2').find('tr').each(function(index,e){
        $(e).find('td').each(function (index1,e1){
            b += $(e1).text()+",";
        });
        b += "]";
    });
    var c = "";
    $('#table_data3').find('tr').each(function(index,e){
       $(e).find('td').each(function (index1,e1){
           c += $(e1).text()+",";
       });
       c += "]";
    });
    $('#exp_a').val(a);
    $('#exp_b').val(b);
    $('#exp_c').val(c);
    $('#form_exp').submit();
}
//获取专利类型
function getPatentTypes(){
    $.ajax({
        url: path+"/patentDataPut/patentTypes/",
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
                    $('#zllx').append("<option value=\""+e.typesName+"\">"+e.typesName+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//获取专利人类型
function getPatentPeoTypes(){
    $.ajax({
        url: path+"/patentDataPut/applyPeoTypes/",
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
                    $('#sqrlx').append("<option value=\""+e.typesName+"\">"+e.typesName+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//获取省市县-联动
function getSSXLD(a,obj){
    $(a).find('.ld').remove();
    if(a == '#szsc')
        $('#szsx').find('.ld').remove();
    if(obj != ''){
        getSSX(a,$(obj).val());
    }
}
//获取省市县
function getSSX(a,obj){
    $.ajax({
        url: path+"/patentDataPut/pmc/ssx/"+obj,
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
                    $(a).append("<option class='ld' value=\""+e.names+"\">"+e.names+"</option>");
                });
            }
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};