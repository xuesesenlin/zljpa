$(function(){
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
    $('.table_data_data').remove();
    $.ajax({
        url: path+"/applicantTypeStatistics/applicantTypeStatistics",
        async: false,
        type: 'POST',
        dataType: 'json',
        data:$('#form_query').serialize(),
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                	pjsj(e);
                });
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function pjsj(obj){
    if(obj.applyAuthorization.search('申请') != -1){
        var i = 0;
        $('#table_data_a').find('th').each(function(index,e){
            if($(e).text() == obj.appPeoTypes){
                i = 1;
                $('#table_data_b').find('td').eq(index).text(Number($('#table_data_b').find('td').eq(index).text())+Number(1));
            }
        });
        if(i == 0){
            $('#table_data_a').append('<th style="text-align:center;" class="table_data_data">'+obj.appPeoTypes+'</th>');
            $('#table_data_b').append('<td style="text-align:center;" class="table_data_data">1</td>');
        }
    }

    if(obj.applyAuthorization.search('授权') != -1){
        var i = 0;
        $('#table_data2_a').find('th').each(function(index,e){
            if($(e).text() == obj.appPeoTypes){
                i = 1;
                $('#table_data2_b').find('td').eq(index).text(Number($('#table_data2_b').find('td').eq(index).text())+Number(1));
            }
        });
        if(i == 0){
            $('#table_data2_a').append('<th style="text-align:center;" class="table_data_data">'+obj.appPeoTypes+'</th>');
            $('#table_data2_b').append('<td style="text-align:center;" class="table_data_data">1</td>');
        }
    }

    if(obj.applyAuthorization.search('有效') != -1){
            var i = 0;
            $('#table_data3_a').find('th').each(function(index,e){
                if($(e).text() == obj.appPeoTypes){
                    i = 1;
                    $('#table_data3_b').find('td').eq(index).text(Number($('#table_data3_b').find('td').eq(index).text())+Number(1));
                }
            });
            if(i == 0){
                $('#table_data3_a').append('<th style="text-align:center;" class="table_data_data">'+obj.appPeoTypes+'</th>');
                $('#table_data3_b').append('<td style="text-align:center;" class="table_data_data">1</td>');
            }
        }
}
//导出查询结果
function export_result_data(){
    $('#data_a').val(pjsj_exp_a());
    $('#data_b').val(pjsj_exp_b());
    $('#data_c').val(pjsj_exp_c());
    $('#form_exp').submit();
}
function pjsj_exp_a(){
    var a = "";
    $('#table_data_a').find('th').each(function(index,e){
        a += $(e).text()+":"+$('#table_data_b').find('td').eq(index).text()+",";
    });
    return a;
}
function pjsj_exp_b(){
    var b = "";
    $('#table_data2_a').find('th').each(function(index,e){
        b += $(e).text()+":"+$('#table_data2_b').find('td').eq(index).text()+",";
    });
    return b;
}
function pjsj_exp_c(){
    var c = "";
    $('#table_data3_a').find('th').each(function(index,e){
        c += $(e).text()+":"+$('#table_data3_b').find('td').eq(index).text()+",";
    });
    return c;
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