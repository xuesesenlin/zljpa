$(function(){

    $('#login_button').click(function(){
        $.ajax({
            url: "/login/login",
            async: false,
            type: 'post',
            data:$('#login_form').serialize(),
            dataType: 'json',
            timeout: 1000,
            cache: false,
            success: function (req) {
                if(req.success){
                    window.location.href="/home/index";
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