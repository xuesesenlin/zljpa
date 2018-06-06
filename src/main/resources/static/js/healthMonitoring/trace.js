$(function(){
    init();
});
//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/trace",
        async: false,
        type: 'get',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            $(req).each(function(index,e){
                $('#table_data').append('<tr>'
                +'<td style="text-align: center;">'+getSmpFormatDateByLong(e.timestamp,true)+'</td>'
                +'<td style="text-align: center;">'+e.info.method+'</td>'
                +'<td style="text-align: center;">'+e.info.path+'</td>'
                +'<td style="text-align: center;">'+e.info.timeTaken+'</td>'
                +'<td style="text-align: center;">'+e.info.headers.request.host+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.connection+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.accept+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.user-+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.agent+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.referer+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.request.cookie+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Access-+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Control-+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Allow-+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Headers+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Context+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Content-+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Type+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.Date+'</td>'
//                +'<td style="text-align: center;">'+e.info.headers.response.status+'</td>'
                +'</tr>');
                if(index == 23)
                    return false;
            });
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};