<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<meta name="keywords" content="IP数据库更新"/>
<meta name="description" content="IP数据库更新"/>
<title>IP数据库更新 - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
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
                                                                  data-animation="fadeIn" method="post"
                                                                  data-animation-delay="50" enctype="multipart/form-data">
                                                                <div class="col-md-10" style="padding: 0;margin: 0;">
                                                                    <input type="file" id="file" name="file"
                                                                           placeholder="北京市政一卡通公交卡号"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50">
                                                                    <input type="text" id="version" name="version"
                                                                           placeholder="版本号"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50">
                                                                    <input type="text" id="otp" name="otp"
                                                                           placeholder="OTP密码"
                                                                           style="padding: 5px 20px;"
                                                                           data-animation="fadeIn"
                                                                           data-animation-delay="50">
                                                                </div>
                                                                <div class="col-md-2" style="padding: 0;margin: 0;">
                                                                    <button class="btn" style="width: 100%"
                                                                            data-animation="fadeIn"
                                                                            data-animation-delay="50">
                                                                        <span>提交</span> <i class="fa fa-search"></i>
                                                                    </button>
                                                                </div>
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
        </div>
    </section>
</main>


<@defaultLayout.footer>
</@defaultLayout.footer>