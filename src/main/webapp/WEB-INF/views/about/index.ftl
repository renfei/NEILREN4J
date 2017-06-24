<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-blue">
<meta name="keywords" content="任霏,网站,博客,互联网,IT,技术,软件,应用,开发,建站"/>
<meta name="description" content="任霏个人博客，是一个关注分享关于互联网、IT技术、软件应用、程序开发等计算机科技领域的IT科技独立博客站点，作者任霏（NeilRen）免费为软件开发者提供帮助与支持。"/>
<title>关于我们 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
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
                    <div class="slide-item" data-slide-id="about">
                        <!-- START CONTAINER -->
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">

                                    <!-- START SLIDE CONTENT-->
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-lg-5 col-lg-offset-2 col-md-6 col-md-offset-1 col-md-push-5 svm">
                                                <div class="section-info text-right">
                                                    <h2 class="section-title text-default animated"
                                                            data-animation="fadeIn" data-animation-delay="60">关于我们</h2>
                                                    <p class="animated" data-animation="fadeIn"
                                                       data-animation-delay="100">
                                                        NEILREN.COM 是任霏的个人网站，最早创立于2012年，曾使用过bennett-ren.com、ren-fei.com，网站程序曾使用过WordPress(PHP)三年，大学毕业后自主编写ASP.NET MVC(C#)两年，当前使用的是
                                                        自主整合的SpringMVC、MyBatis、Memcache的SSM框架(Java)。
                                                    </p>
                                                    <h3 class="section-title text-default animated"
                                                        data-animation="fadeIn" data-animation-delay="60">关于任霏</h3>
                                                    <p class="animated" data-animation="fadeIn"
                                                       data-animation-delay="100">
                                                        嗨~ 很高兴认识你。我是一只程序猿，河北省张家口人，那里有坝上草原、滑雪场(2022冬奥会)、张北数据中心；在石家庄待了四年；后来去了北京北漂；专业呢就是软件技术，学C#.NET毕业的，不过后来自学
                                                        转了Java，因为 .NET 的工作越来越难找了，不过桌面窗口程序我还是喜欢用 WPF.NET 来做的。
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="col-md-5 col-md-pull-7 svm">
                                                <div class="section-info text-left">
                                                    <!-- START FEATURES-LIST -->
                                                    <div class="features-list">

                                                        <!-- FEATURE -->
                                                        <div class="featured-item animated" data-animation="fadeIn"
                                                             data-animation-delay="150">
                                                            <div class="featured-text">
                                                                <h3><i class="fa fa-rocket"></i> 关于NEILREN4J</h3>

                                                                <p>
                                                                    个人网站经过5年多的历史，使用过WordPress，使用过 .NET MVC，在转 Java 专业后决定自己整合一个 Java 版的个人站，因为使用了大量的开源软件，有些开源协议要求使用者开源，
                                                                    所以我选择拥抱开源，在<a href="https://github.com/NeilRen" target="_blank" >Github</a>上公开代码，边做边公开，也希望能认识更多的朋友，指出我的不足，毕竟刚入行的小学生，给这个程序起名
                                                                    <a href="https://github.com/NeilRen/NEILREN4J" target="_blank" >NEILREN4J</a>。
                                                                </p>
                                                            </div>
                                                        </div>

                                                        <div class="featured-item">
                                                            <!-- FEATURE -->
                                                            <div class="featured-item animated" data-animation="fadeIn"
                                                                 data-animation-delay="150">
                                                                <div class="featured-text">
                                                                    <h3><i class="icon-heart"></i> 使用并鸣谢开源项目</h3>
                                                                    <p>
                                                                        <a href="https://www.centos.org/" target="_blank" >CentOS</a>;
                                                                        <a href="http://nginx.org/" target="_blank" >Nginx</a>;
                                                                        <a href="http://tomcat.apache.org/" target="_blank" >TomCat</a>;
                                                                        <a href="https://www.java.com/" target="_blank" >Java</a>;
                                                                        <a href="https://spring.io/" target="_blank" >Spring</a>;
                                                                        <a href="http://www.mybatis.org/" target="_blank" >MyBatis</a>;
                                                                        <a href="http://freemarker.org/" target="_blank" >FreeMarker</a>;
                                                                        <a href="http://memcached.org/" target="_blank" >Memcached</a>;
                                                                        <a href="https://mariadb.org/" target="_blank" >MaraDB</a>;
                                                                        <a href="https://logging.apache.org/log4j/" target="_blank" >Log4J</a>;
                                                                        <a href="http://junit.org/" target="_blank" >Junit</a>;
                                                                        <a href="http://jquery.com/" target="_blank" >jQuery</a>;
                                                                        <a href="http://getbootstrap.com/" target="_blank" >Bootstrap</a>;
                                                                        <a href="http://lucene.apache.org/" target="_blank" >Lucene</a>;
                                                                        <a href="https://github.com/alibaba/druid" target="_blank" >Druid</a>;
                                                                        <a href="https://github.com/alibaba/fastjson" target="_blank" >FastJson</a>;
                                                                        <a href="http://shiro.apache.org/" target="_blank" >Shiro</a>;
                                                                        <a href="https://www.openssl.org/" target="_blank" >OpenSSL</a>;
                                                                        <a href="https://code.google.com/archive/p/ik-analyzer/" target="_blank" >IKAnalyer</a>;
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <!-- END FEATURES-LIST -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END SLIDE CONTENT-->
                                </div>
                            </div>
                            <!-- END ROW -->
                        </div>
                        <!-- END CONTAINER -->
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<@defaultLayout.footer>
</@defaultLayout.footer>