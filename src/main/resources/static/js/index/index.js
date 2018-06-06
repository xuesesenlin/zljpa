$(function(){
//    weather();
});
function cli(a){
    var dt = $(a).attr('data-target');
    $.ajax({
        headers:{"L":"1","Content-Type":"text/plain;charset=UTF-8"},
        url:path+dt,
        cache:false,
        success:function(data){
            $('.content').empty();
            $('.content').html(data);
        }
    }).done(function(d){
    }).fail(function() {
        $('.content').html("未找到资源");
    }).always(function() {
    });
}
function weather(){
    $.ajax({
        url:path+'/weather/weather/石家庄市',
        cache:false,
        dataType:"json",
        success:function(req){
            if(req.data.status == 200){
                var model1 = "<tr><td>"+req.data.data.yesterday.date+"</td>"
                        +"<td>"+req.data.data.yesterday.high+"</td>"
                        +"<td>"+req.data.data.yesterday.low+"</td>"
                        +"<td>"+req.data.data.yesterday.fx+"</td>"
                        +"<td>"+req.data.data.yesterday.fl+"</td>"
                        +"<td>"+req.data.data.yesterday.aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.yesterday.type+"</td></tr>";
                $('#tq_database').append(model1);

                var model2 = "<tr><td><span style=\"color:red;\">(今日)</span>"+req.data.data.forecast[0].date+"</td>"
                        +"<td>"+req.data.data.forecast[0].high+"</td>"
                        +"<td>"+req.data.data.forecast[0].low+"</td>"
                        +"<td>"+req.data.data.forecast[0].fx+"</td>"
                        +"<td>"+req.data.data.forecast[0].fl+"</td>"
                        +"<td>"+req.data.data.forecast[0].aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.forecast[0].type+"</td></tr>";
                $('#tq_database').append(model2);

                var model3 = "<tr><td>"+req.data.data.forecast[1].date+"</td>"
                        +"<td>"+req.data.data.forecast[1].high+"</td>"
                        +"<td>"+req.data.data.forecast[1].low+"</td>"
                        +"<td>"+req.data.data.forecast[1].fx+"</td>"
                        +"<td>"+req.data.data.forecast[1].fl+"</td>"
                        +"<td>"+req.data.data.forecast[1].aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.forecast[1].type+"</td></tr>";
                $('#tq_database').append(model3);

                var model4 = "<tr><td>"+req.data.data.forecast[2].date+"</td>"
                        +"<td>"+req.data.data.forecast[2].high+"</td>"
                        +"<td>"+req.data.data.forecast[2].low+"</td>"
                        +"<td>"+req.data.data.forecast[2].fx+"</td>"
                        +"<td>"+req.data.data.forecast[2].fl+"</td>"
                        +"<td>"+req.data.data.forecast[2].aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.forecast[2].type+"</td></tr>";
                $('#tq_database').append(model4);

                var model5 = "<tr><td>"+req.data.data.forecast[3].date+"</td>"
                        +"<td>"+req.data.data.forecast[3].high+"</td>"
                        +"<td>"+req.data.data.forecast[3].low+"</td>"
                        +"<td>"+req.data.data.forecast[3].fx+"</td>"
                        +"<td>"+req.data.data.forecast[3].fl+"</td>"
                        +"<td>"+req.data.data.forecast[3].aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.forecast[3].type+"</td></tr>";
                $('#tq_database').append(model5);

                var model6 = "<tr><td>"+req.data.data.forecast[4].date+"</td>"
                        +"<td>"+req.data.data.forecast[4].high+"</td>"
                        +"<td>"+req.data.data.forecast[4].low+"</td>"
                        +"<td>"+req.data.data.forecast[4].fx+"</td>"
                        +"<td>"+req.data.data.forecast[4].fl+"</td>"
                        +"<td>"+req.data.data.forecast[4].aqi+"</td>"
                        +"<td onmouseover=\"onmouse(this)\" onmouseout=\"outmouse()\">"+req.data.data.forecast[4].type+"</td></tr>";
                $('#tq_database').append(model6);
            }
        }
    }).done(function(d){
    }).fail(function() {
    }).always(function() {
    });
}
function onmouse(obj){
    var a = $(obj).text();
    $('#tq_img').show();
    $('#tq_img').attr('src','');
    if(a == '雷阵雨')
        $('#tq_img').attr('src',path+'/img/tq/tq_lzy.gif');
    if(a == '中雨')
        $('#tq_img').attr('src',path+'/img/tq/tq_zy.gif');
    if(a == '晴')
        $('#tq_img').attr('src',path+'/img/tq/tq_qt.gif');
    if(a == '阴')
        $('#tq_img').attr('src',path+'/img/tq/tq_y.gif');
    if(a == '多云')
        $('#tq_img').attr('src',path+'/img/tq/tq_dy.gif');
    if(a == '雾霾' || a == '霾')
        $('#tq_img').attr('src',path+'/img/tq/tq_m.gif');
}
function outmouse(){
    $('#tq_img').attr('src','');
    $('#tq_img').hide();
}