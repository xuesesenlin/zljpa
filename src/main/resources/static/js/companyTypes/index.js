$(function(){
//    init
    init();
});
//init
function init(){
    $('#table_data').find('tr').remove();
    $.ajax({
        url: path+"/companyTypes/companyTypes",
        async: false,
        type: 'GET',
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                $(req.data).each(function(index,e){
                    var h = "<tr><td style=\"text-align: center;\">"+(index+1)+"</td>"
                    +"<td style=\"text-align: center;\">"+e.companyTypes+"</td></tr>";
                    $('#table_data').append(h);
                });
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
function save(){
    $.ajax({
        url: path+"/companyTypes/companyTypes",
        async: false,
        type: 'POST',
        dataType: 'json',
        data:$('#form_save').serialize(),
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){},
        success: function (req) {
            if(req.success){
                init();
                $('#form_save').hide();
            }else
                alert(req.message);
        },
        error: erryFunction
    });
}
//    error
function erryFunction() {
    alert("错误!");
};