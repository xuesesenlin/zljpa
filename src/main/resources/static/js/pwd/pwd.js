$(function(){
    $('#pwd_button').click(function(){
        $.ajax({
            url: "/account/pwd",
            async: false,
            type: 'post',
            data:$('#pwd_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    alert('更改成功，请从新登录');
                    window.location.href="/logout";
                }else{
                    alert(req.message);
                }
            },
            error: erryFunction
        });
    });
    function erryFunction() {
        alert("错误!");
    };
});