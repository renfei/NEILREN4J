<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<meta name="keywords" content="北京,市政,交通,一卡通,公交卡,交易,查询,接口"/>
<meta name="description" content="查询北京市政一卡通交通公交卡交易记录，余额，地铁累计金额。"/>
<title>北京市政一卡通公交卡交易查询接口 - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
<script type="application/javascript">
    function inquiry() {
        var cardID = $('#cardID').val();
        if (cardID == '') {
            alert('请填写一卡通卡号');
            return false;
        } else if (isNaN(cardID)) {
            alert('请填写正确的一卡通卡号');
            return false;
        }
        $('#dtview tbody').html('')
        $.ajax({
            url: "https://api.neilren.com/BMAC/cardRecord",
            type: "post",
            data: {cardID: cardID},
            dataType: "json",
            success: function (data) {
                if (data == undefined || data.length == 0) {
                    alert('查不到信息！');
                    return false;
                }
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += "<tr>";
                    html += "<th scope=\"row\">" + (i + 1) + "</th>";
                    html += "<td>" + (data[i].type == 0 ? '消费' : '充值') + "</td>";
                    html += "<td>" + (data[i].date == undefined ? '' : data[i].date) + "</td>";
                    html += "<td>" + (data[i].money == undefined ? '' : data[i].money) + "</td>";
                    html += "<td>" + (data[i].scene == undefined ? '' : data[i].scene) + "</td>";
                    html += "<td>" + (data[i].balance == undefined ? data[i].after : data[i].balance) + "</td>";
                    html += "<td>" + (data[i].cumulative == undefined ? '' : data[i].cumulative) + "</td>";
                    html += "</tr>";
                }
                $('#dtview tbody').html(html);
            },
            error: function () {
                alert('查询失败！未查询到任何信息');
                return false;
            }
        });
        return false;
    }
</script>
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>

<main id="awd-site-main">
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">

                <div class="slides-wrap">
                    <div class="slide-item" data-slide-id="home">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-lg-8 col-md-7 svm">
                                                <div class="section-info text-left" style="margin: 0;">
                                                    <div class="countdown">
                                                        <div class="animated" data-animation="fadeIn"
                                                             data-animation-delay="60">
                                                            <form class="subscribe-form animated" style="margin-top: 0;"
                                                                  data-animation="fadeIn"
                                                                  data-animation-delay="50">
                                                                <div class="col-md-10" style="padding: 0;margin: 0;">
                                                                    <input type="text" id="cardID"
                                                                           placeholder="北京市政一卡通公交卡号"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50">
                                                                </div>
                                                                <div class="col-md-2" style="padding: 0;margin: 0;">
                                                                    <button class="btn" style="width: 100%"
                                                                            onclick="return inquiry()"
                                                                            data-animation="fadeIn"
                                                                            data-animation-delay="50">
                                                                        <span>查询</span> <i class="fa fa-search"></i>
                                                                    </button>
                                                                </div>
                                                            </form>
                                                            <div class="row" style="margin-left: 0;margin-right: 0;">
                                                                <table id="dtview" class="table table-hover">
                                                                    <thead>
                                                                    <tr>
                                                                        <th>#</th>
                                                                        <th>类型</th>
                                                                        <th>日期</th>
                                                                        <th>金额</th>
                                                                        <th>场景</th>
                                                                        <th>余额</th>
                                                                        <th>地铁累计</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-5 svm" style="vertical-align: top;">
                                                <div class="section-info text-left" style="margin-top: 0;">
                                                    <div class="row">
                                                        <form class="subscribe-form animated" style="margin-top: 0;"
                                                              method="get" action="/Search/" data-animation="fadeIn"
                                                              data-animation-delay="50">
                                                            <div class="col-md-10" style="padding: 0;margin: 0;">
                                                                <input type="text" id="subscribe-email"
                                                                       name="wd"
                                                                       placeholder="搜索一下"
                                                                       style="padding: 5px 20px;"
                                                                       data-animation="fadeIn"
                                                                       data-animation-delay="50">
                                                            </div>
                                                            <div class="col-md-2" style="padding: 0;margin: 0;">
                                                                <button type="submit" class="btn" style="width: 100%"
                                                                        data-animation="fadeIn"
                                                                        data-animation-delay="50">
                                                                    <span>搜索</span> <i class="fa fa-search"></i>
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4 animated" data-animation="fadeIn"
                                                             data-animation-delay="100">
                                                            <a href="https://github.com/NeilRen" target="_blank"><img
                                                                    src="//cdn.neilren.com/neilren4j/1.0.0/img/NeilRenInGithub.png"
                                                                    alt="https://github.com/NeilRen" l></a>

                                                        </div>
                                                        <div class="col-md-8 animated" data-animation="fadeIn"
                                                             data-animation-delay="100">
                                                            <h3>NeilRen in Github</h3>
                                                            <p style="font-size: 12px;">
                                                                使用开源，回归开源；开源是极客们向技术垄断发起的挑战；是程序员们的饕餮狂欢。<br/>
                                                                <a href="https://github.com/NeilRen" target="_blank">https://github.com/NeilRen</a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <h2 class="text-default animated" data-animation="fadeIn"
                                                            data-animation-delay="50">
                                                            WeChat
                                                        </h2>
                                                        <div class="col-md-6" data-animation="fadeIn"
                                                             data-animation-delay="100">
                                                            <img src="//cdn.neilren.com/neilren4j/1.0.0/img/wechat_neilrencom.svg">
                                                        </div>
                                                        <div class="col-md-6" data-animation="fadeIn"
                                                             data-animation-delay="100">
                                                            <h3>微信订阅号</h3>
                                                            <p style="font-size: 12px;">
                                                                扫描二维码或者搜索微信：NeilRenCom<br/>
                                                                关注 NEILREN 微信订阅号，软件开发、技术资讯、建站交流，新鲜的你总是第一个知道。
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>


<@defaultLayout.footer>
</@defaultLayout.footer>