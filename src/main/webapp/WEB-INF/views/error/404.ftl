<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-red">
<meta name="keywords" content="任霏,网站,博客,互联网,IT,技术,软件,应用,开发,建站" />
<meta name="description" content="404 - Not Found" />
<title>404 - Not Found - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
<script type="text/javascript" src="https://qzone.qq.com/gy/404/data.js" charset="utf-8"></script>
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
                                            <div class="col-lg-7 col-md-7 svm">
                                                <div class="section-info text-left">
                                                    <p><strong>404</strong> Not Found 没有找到</p>
                                                    <p>服务器上无法找到您请求的网页，该网页可能已经被关闭或者您输入的网址错误，您可以尝试使用搜索功能找到您需要的网页</p>
                                                    <!-- Subscribe Form -->
                                                    <div class="row">
                                                        <div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
                                                            <form class="subscribe-form"
                                                                  method="get" action="/Search/">
                                                                <input type="text" id="subscribe-email"
                                                                       name="wd" placeholder="搜索一下">
                                                                <button type="submit"
                                                                        class="btn">
                                                                    <span>搜索</span> <i class="fa fa-search"></i>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <!-- end Subscribe Form -->
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-5 svm" style="vertical-align: top;">

                                                <div class="section-info text-left">
                                                    <!-- START TITLE -->
                                                    <h2 class="section-title text-default animated"
                                                        data-animation="fadeIn" data-animation-delay="60">
                                                        您访问的页面找不回来了
                                                        <br/> 但我们可以一起帮他们回家</h2>
                                                    <!-- END TITLE -->

                                                    <div class="row">
                                                        <div class="col-md-5">
                                                            <img src="" id="404img"
                                                                 style="display: inline-block;max-width: 100%;">
                                                        </div>
                                                        <div class="col-md-7" style="font-size: 14px;">
                                                            <strong style="font-size: 22px;"><span id="name"></span>(<span
                                                                    id="sex"></span>)</strong><br/>
                                                            <hr style="border-top: 1px solid #000;">
                                                            <strong>出生日期：</strong><span id="birth_time"></span><br/>
                                                            <strong>失踪日期：</strong><span id="lost_time"></span><br/>
                                                            <strong>失踪地点：</strong><span id="lost_place"></span><br/>
                                                            <strong>特征描述：</strong><span
                                                                id="child_feature"></span><br/>
                                                            <a href="JavaScript:void(0)" id="404url"
                                                               class="btn animated"
                                                               data-animation-delay="60"
                                                               style="margin-bottom: 5px;" target="_blank">
                                                                查看详情
                                                            </a>
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
<script type="application/javascript">
    $(function () {
        if (jsondata != undefined) {
            var num = Math.random() * jsondata.data.length;
            num = parseInt(num, 10);
            $('#404img').attr('src', jsondata.data[num].child_pic);
            $('#name').html(jsondata.data[num].name);
            $('#sex').html(jsondata.data[num].sex);
            $('#birth_time').html(jsondata.data[num].birth_time);
            $('#lost_time').html(jsondata.data[num].lost_time);
            $('#lost_place').html(jsondata.data[num].lost_place);
            $('#child_feature').html(jsondata.data[num].child_feature);
            $('#404url').attr('href', jsondata.data[num].url);
        }
    });
</script>
</@defaultLayout.footer>