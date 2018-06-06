var pageN;
$(function(){
    getCompanyTypes();
});
//query
function click_query(){
    $('#keyworda').val("");
    $('#keywordb').val("");
    $.ajax({
        url: path+"/keyEnterprises/keyEnterprises/"+$('#qylx').val(),
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
    $.ajax({
        url: path+"/keyEnterprises/keyEnterprises",
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
function getCompanyTypes(){
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
                    var h = "<option value=\""+e.companyTypes+"\">"+e.companyTypes+"</option>";
                    $('#qylx').append(h);
                });
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