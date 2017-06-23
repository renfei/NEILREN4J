<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-gray">
<meta name="keywords" content="任霏,网站,博客,互联网,IT,技术,软件,应用,开发,建站"/>
<meta name="description" content="任霏个人博客，是一个关注分享关于互联网、IT技术、软件应用、程序开发等计算机科技领域的IT科技独立博客站点，作者任霏（NeilRen）免费为软件开发者提供帮助与支持。"/>
<title>联系我们 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>

<!-- START MAIN -->
<main id="awd-site-main">
    <!-- START SECTION -->
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">

                <div class="slides-wrap">
                    <div class="slide-item" data-slide-id="contact">
                        <!-- START CONTAINER -->
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- START SLIDE CONTENT-->
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-1 col-md-6 col-md-push-6 svm">

                                                <div class="section-info text-right">
                                                    <!-- START TITLE -->
                                                    <h2 class="section-title text-default animated"
                                                        data-animation="fadeIn">联系我们</h2>
                                                    <!-- END TITLE -->
                                                    <div class="contact-info">
                                                        <p class="contact-item"><i class="fa fa-qq"></i>
                                                            QQ群:<a href="//shang.qq.com/wpa/qunwpa?idkey=bfbde7e5dec79fd3cdb23c7cf590ca698e3da8b48a71369139ed6aa52f9a7513"
                                                                   target="_blank">130832168</a></p>
                                                        <p class="contact-item"><i class="fa fa-phone"></i> 电话:
                                                            13082843041</p>
                                                        <p class="contact-item"><i class="fa fa-envelope"></i>
                                                            邮箱:<a href="mailto:mail@neilren.com" target="_blank">Mail@NeilRen.Com</a>
                                                        </p>
                                                        <p class="contact-item"><i class="fa fa-map-marker"></i> 河北省
                                                            张家口市
                                                        </p>
                                                    </div>
                                                    <p class="animated" data-animation="fadeIn"
                                                       data-animation-delay="100">
                                                    <h3>编程就像做爱：你要为一次错误负责一辈子。</h3>
                                                    <h5>Programming is like sex: one mistake and you’re providing
                                                        support for a lifetime.</h5>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-md-pull-6 svm">
                                                <div class="section-info text-left">
                                                    <!-- START CONTACT FORM -->
                                                    <form id="contact-form" class="contact-form">
                                                        <div class="row">
                                                            <div class="col-lg-4 col-md-6 col-md-12 animated"
                                                                 data-animation="fadeIn" data-animation-delay="200">
                                                                <input type="text" name="contact-name"
                                                                       placeholder="您的称呼"
                                                                       class="contact-form-name required">
                                                            </div>
                                                            <div class="col-lg-8 col-md-6 col-md-12 animated"
                                                                 data-animation="fadeIn" data-animation-delay="200">
                                                                <input type="email" name="contact-email"
                                                                       placeholder="您的电子邮箱"
                                                                       class="contact-form-email required">
                                                            </div>
                                                            <div class="col-md-12 animated" data-animation="fadeIn"
                                                                 data-animation-delay="150">
									                            <textarea name="contact-message" placeholder="您的留言内容..."
                                                                    class="contact-form-message required"
                                                                    rows="4"></textarea>
                                                                <button class="btn btn-block" type="submit" id="submit"
                                                                        name="submit"><span>发送留言</span>
                                                                    <i class="fa fa-send"></i></button>
                                                            </div>
                                                        </div>
                                                        <div class="contact-notice"></div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- END SLIDE CONTENT-->
                                </div>
                            </div>
                            <!-- END ROW -->
                        </div>

                    </div>
                </div>
            </div>
    </section>
</main>

<@defaultLayout.footer>
</@defaultLayout.footer>