<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登陆 - NEILREN.COM 站点控制台</title>
    <meta name="author" content="DeathGhost"/>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css" tppabs="/static/css/login.css"/>
    <style>
        body {
            height: 100%;
            background: #16a085;
            overflow: hidden;
        }

        canvas {
            z-index: -1;
            position: absolute;
        }
    </style>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/Particleground.js" tppabs="js/Particleground.js"></script>
    <script>
        $(document).ready(function () {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });
            //测试提交，对接程序删除即可
            $(".submit_btn").click(function () {
                var otp = $("#otp").val();
                var gac = $("#gac").val();
                var ver = $("#J_codetext").val();
                if (ver == "") {
                    alert("验证码必填！");
                    return false;
                } else if (otp == "" && gac == "") {
                    alert("GAC和OTP必填一项！");
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: "/console/login",
                    data: {otp: otp, gac: gac, ver: ver},
                    dataType: "json",
                    success: function (data) {
                        if (data.state != 200) {
                            alert(data.message);
                        } else {
                            //
                            window.location.href = "/console";
                        }
                    }
                });
            });
        });

        function refcaptcha() {
            $("#myCanvas").attr('src', "/console/captcha?random=" + Math.random());
        }
    </script>
</head>
<body>
<dl class="admin_login">
    <dt>
        <strong>NEILREN.COM 站点控制台</strong>
        <em>NeilRen.Com Console</em>
    </dt>
    <dd class="gac_icon">
        <input type="text" id="gac" placeholder="GoogleAuthenticator" class="login_txtbx"/>
    </dd>
    <dd class="otp_icon">
        <input type="text" id="otp" placeholder="OTP(OneTimePassword)" class="login_txtbx"/>
    </dd>
    <dd class="val_icon">
        <div class="checkcode">
            <input type="text" id="J_codetext" placeholder="验证码" maxlength="6" class="login_txtbx">
        </div>
        <img src="/console/captcha" class="J_codeimg" id="myCanvas" width="140" height="42" onclick="refcaptcha()">
    </dd>
    <dd>
        <input type="button" value="立即登陆" class="submit_btn"/>
    </dd>
</dl>
</body>
</html>