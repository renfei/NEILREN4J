<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>

<#-- 调用布局指令 -->
<@defaultLayout.head  title="500 - 内部服务器错误" keywords="500 - 内部服务器错误" description="500 - 内部服务器错误" bgcolour="bg-red">
</@defaultLayout.head>

<@defaultLayout.header>
</@defaultLayout.header>
<main id="awd-site-main">
    <!-- START SECTION -->
    <section id="awd-site-content">
        <div class="sections-block">
            <div class="slides">
                <div class="slides-wrap">
                    <!-- ABOUT SECTION -->
                    <div class="slide-item" data-slide-id="about">
                        <!-- START CONTAINER -->
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">

                                    <!-- START SLIDE CONTENT-->
                                    <div class="slide-content">
                                        <div class="row">
                                            <div class="col-md-6 svm">
                                                <div class="section-info text-left">
                                                    <!-- START TITLE -->
                                                    <h2 class="section-title text-default animated"
                                                        data-animation="fadeIn" data-animation-delay="60">500 -
                                                        Internal Server Error</h2>
                                                    <h3 class="text-default animated" data-animation="fadeIn"
                                                        data-animation-delay="60">500 - 内部服务器错误</h3>
                                                    <!-- END TITLE -->
                                                    <p class="animated" data-animation="fadeIn"
                                                       data-animation-delay="100">
                                                        喔！该死！服务器那边遇到了意外的情况，暂时无法完成您的请求！程序遇到了致命错误无法继续执行下去，看来某些人又要加班了，工程师正在来的路上，客官不要方...</p>
                                                </div>
                                            </div>
                                            <div class="col-md-5  svm">
                                                <div class="section-info text-right">
                                                    <!-- START FEATURES-LIST -->
                                                    <div class="features-list">

                                                        <!-- FEATURE -->
                                                        <div class="featured-item animated" data-animation="fadeIn"
                                                             data-animation-delay="150">
                                                            <div class="featured-text">
                                                                <h3>这是由什么引起的？</h3>

                                                                <p>
                                                                    该错误是由于远程服务器端遇到致命错误发生的，短暂无法提供服务，您可以立即尝试刷新或稍后再来尝试
                                                                </p>
                                                            </div>
                                                        </div>

                                                        <div class="featured-item">
                                                            <!-- FEATURE -->
                                                            <div class="featured-item animated"
                                                                 data-animation="fadeIn"
                                                                 data-animation-delay="150">
                                                                <div class="featured-text">
                                                                    <h3>我能做些什么？</h3>

                                                                    <p>
                                                                        该错误是由我们程序崩溃引起的，给您带来不便请谅解，您可以联系我们报告这一事故是如何发生的：E-mail:mail@neilren.com
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
    <!-- END SECTION -->
</main>

<@defaultLayout.footer>
</@defaultLayout.footer>