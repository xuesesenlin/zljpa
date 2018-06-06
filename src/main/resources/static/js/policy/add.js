var ckeditor;
$(function(){
    ckeditor = CKEDITOR.replace('bodys');
    init();
});
//加载类型
function init(){
    $('select[name="types"]').find('option').remove();
    $.ajax({
        url: "/policy/policyType/",
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
                    $('select[name="types"]').append("<option value=\""+e.uuid+"\">"+e.typeName+"</option>");
                });
            }else{
                alert("未找到类型");
            }
        },
        error: erryFunction
    });
}
//add
function add(){
    var content = ckeditor.document.getBody().getHtml();
    $('#bodys').html(content);
    $.ajax({
        url: "/policy/policy/",
        async: false,
        type: 'POST',
        data:$('#ro_form_save').serialize(),
        dataType: 'json',
        timeout: 1000,
        cache: false,
        beforeSend:function(XMLHttpRequest){
        },
        success: function (req) {
            if(req.success){
                alert(req.message);
                $('#policy_home').click();
            }else{
                alert(req.message);
            }
        },
        error: erryFunction
    });
}

//    error
function erryFunction() {
    alert("错误!");
};