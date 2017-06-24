<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<meta name="keywords" content="任霏,网站,博客,互联网,IT,技术,软件,应用,开发,建站"/>
<meta name="description" content="任霏个人博客，是一个关注分享关于互联网、IT技术、软件应用、程序开发等计算机科技领域的IT科技独立博客站点，作者任霏（NeilRen）免费为软件开发者提供帮助与支持。"/>
<title>关于我们 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>

<main id="awd-site-main">
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">

                <div class="slides-wrap">
                    <div class="slide-item" data-slide-id="subscribe">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-2 col-md-6 col-md-offset-1 col-md-push-5 svm">
                                                <div class="section-info text-right">
                                                    <h2 class="section-title text-default animated"
                                                        data-animation="fadeIn" data-animation-delay="60">订阅 <br/> 我们的消息
                                                    </h2>
                                                    <p class="animated" data-animation="fadeIn"
                                                       data-animation-delay="100">加入我们的邮件列表.
                                                        <strong>快速和容易的获取最新消息.</strong><br/> 你将成为第一个知道的人<br/> 项目动态、业界热点.
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="col-md-5 col-md-pull-7 svm">
                                                <div class="section-info text-left">
                                                    <p>您将收到一封确认邮件，<strong>别忘了</strong>点击电子邮件中<strong>激活</strong>连接，来激活您的订阅。</p>
                                                    <div class="row">
                                                        <div class="col-md-12 col-md-offset-0 col-sm-8 col-sm-offset-2">
                                                            <form id="subscribe-form" class="subscribe-form"
                                                                  method="post">
                                                                <input type="email" id="subscribe-email"
                                                                       name="subscribe-email" placeholder="请留下你的邮箱地址">
                                                                <button type="submit" id="subscribe-submit"
                                                                        class="btn btn-inverse" onclick="alert('该功能暂未启用')">
                                                                    <span>订阅</span> <i class="fa fa-envelope"></i>
                                                                </button>
                                                                <div class="form-notice subscribe-notice"></div>
                                                            </form>
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