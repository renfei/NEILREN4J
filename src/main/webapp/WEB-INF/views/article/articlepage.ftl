<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>
<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-black">
<meta name="keywords" content="${articleWithBLOBs.getKeyword()!""}"/>
<meta name="description" content="${articleWithBLOBs.getDescribes()?html}"/>
<title>${articleWithBLOBs.getTitle()} - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
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
                                                <div class="section-info text-left">
                                                    <div class="countdown">
                                                        <div class="animated" data-animation="fadeIn"
                                                             data-animation-delay="60">
                                                        <#if articleWithBLOBs??>
                                                            <div class="entry-content">
                                                                <h3 class="text-default animated"
                                                                    data-animation="fadeIn" data-animation-delay="50">
                                                                ${articleWithBLOBs.getTitle()!""}
                                                                </h3>
                                                                <div class="row entry-info">
                                                                    <div class="col-xs-6">
                                                                        <span class="entry-date">${articleWithBLOBs.getArticleDat()?date}</span>
                                                                        <span class="entry-byline">By <a
                                                                                href="${articleWithBLOBs.getAuthorUrl()!"javascript:void(0)"}"
                                                                                target="_blank">${articleWithBLOBs.getAuthor()!""}</a></span>
                                                                        <span class="entry-byline">Views:${articleWithBLOBs.getViews()!""}</span>
                                                                    </div>
                                                                    <div class="col-xs-6 rating-wrap text-right">
                                                                        <div class="score-callback"
                                                                             data-id="${articleWithBLOBs.getId()?c}"
                                                                             data-score="${articleWithBLOBs.getGrade()!""}"
                                                                             style="float: right;"></div>
                                                                    </div>
                                                                </div>
                                                                <p class="animated" data-animation="fadeIn"
                                                                   data-animation-delay="100">
                                                                ${articleWithBLOBs.getContent()}
                                                                </p>
                                                            </div>
                                                        </#if>
                                                        <#if advert=="true">
                                                        <div class="row">
                                                            <div class="col-md-3">
                                                                <div class="row nr_tui animated" data-animation="fadeIn"
                                                                     data-animation-delay="50">
                                                                    <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3D0IANeWQDylccQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2Fra0XwlAn73dJRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozO54LQ%2FVw1L9X5LHh3Z8M%2BWS6ALZVeqlk9XUfbPSJC%2F06deTzTIbffYpyF7ku%2BxKgGargQjSAC4C6cUF%2FXAmem"
                                                                       target="_blank">
                                                                        <img src="//cdn.neilren.com/neilren4j/tui/aliyun/baokuan/200-200.jpg"
                                                                             alt="阿里云爆款"/>
                                                                    </a>
                                                                    <a href="/About/Advert" target="_blank">
                                                                        <span>广告</span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="row nr_tui animated" data-animation="fadeIn"
                                                                     data-animation-delay="50">
                                                                    <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3DmBRxE1vhSjYcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2FrBReyEv7GSVZRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozO54LQ%2FVw1L9X5LHh3Z8M%2BWS6ALZVeqlk9XUfbPSJC%2F06deTzTIbffYpyF7ku%2BxKguktBpDNMjUsC9aO5Uou70LpxQX9cCZ6Y%3D"
                                                                       target="_blank">
                                                                        <img src="//cdn.neilren.com/neilren4j/tui/aliyun/gaoxingneng/200-200.jpg"
                                                                             alt="阿里云高性能"/>
                                                                    </a>
                                                                    <a href="/About/Advert" target="_blank">
                                                                        <span>广告</span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="row nr_tui animated" data-animation="fadeIn"
                                                                     data-animation-delay="50">
                                                                    <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3DTVecZLJIjL8cQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2Frj9VwkpMyEfFRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozcHtRpEUy6RHVyxRO0gvF4QxJtmCgOmCLXl8Q7TEjBF%2FKAgNLUle0RTNHcuSwiZDTGDmntuH4VtA%3D%3D"
                                                                       target="_blank">
                                                                        <img src="//cdn.neilren.com/neilren4j/tui/aliyun/RDS/200-200.jpg"
                                                                             alt="阿里云RDS"/>
                                                                    </a>
                                                                    <a href="/About/Advert" target="_blank">
                                                                        <span>广告</span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <div class="row nr_tui animated" data-animation="fadeIn"
                                                                     data-animation-delay="50">
                                                                    <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3DYztZ1F4lg9UcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2FrYknTRG9bvKRRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozcHtRpEUy6RNUAFIvVRZRlpMw2ZCfu0snXb7YcYklj7BMEKX%2BSpkiu7%2Fur8YErC2DGDF1NzTQoPw%3D%3D"
                                                                       target="_blank">
                                                                        <img src="//cdn.neilren.com/neilren4j/tui/aliyun/dns/200-200.jpg"
                                                                             alt="阿里云DNS"/>
                                                                    </a>
                                                                    <a href="/About/Advert" target="_blank">
                                                                        <span>广告</span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </#if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-5 svm" style="vertical-align: top;">
                                                <div class="section-info text-left">
                                                    <div class="row">
                                                        <form class="subscribe-form animated"
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
                                                <#if tagList??>
                                                    <h2 class="text-default animated" data-animation="fadeIn"
                                                        data-animation-delay="50">
                                                        Popular Tags
                                                    </h2>
                                                    <#list tagList as tag>
                                                        <a href="/tag/${tag.getEnName()!""}" class="btn animated"
                                                           data-animation-delay="60" style="margin-bottom: 5px;">
                                                        ${tag.getZhName()!""}
                                                        </a>
                                                    </#list>
                                                </#if>
                                                <#if advert=="true">
                                                    <div class="row nr_tui animated" data-animation="fadeIn"
                                                         data-animation-delay="50">
                                                        <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3D1JOWsKFToB4cQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2FrXjzWB9WGMqhRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozO54LQ%2FVw1L9X5LHh3Z8M%2BWS6ALZVeqlk9XUfbPSJC%2F06deTzTIbffMLokHRX1EtqlarZr4RXuLi6cUF%2FXAmem"
                                                           target="_blank">
                                                            <img src="//cdn.neilren.com/neilren4j/tui/aliyun/xuesheng/800-200.jpg"
                                                                 alt="阿里云学生优惠"/>
                                                        </a>
                                                        <a href="/About/Advert" target="_blank">
                                                            <span>广告</span>
                                                        </a>
                                                    </div>
                                                </#if>
                                                <#if articleTop10ByDateList??>
                                                    <h2 class="text-default animated" data-animation="fadeIn"
                                                        data-animation-delay="50">
                                                        Most Recent
                                                    </h2>
                                                    <#list articleTop10ByDateList as articleTop10ByDate>
                                                        <a href="/Article/${articleTop10ByDate.getId()?c}"
                                                           class="animated" data-animation-delay="60"
                                                           style="display:block;margin-bottom: 5px;font-size: 14px">
                                                            <#if articleTop10ByDate.getTitle()?length gt 28>
                                                            ${articleTop10ByDate.getTitle()?substring(0,28)?html}...
                                                            <#else>
                                                            ${articleTop10ByDate.getTitle()!""}
                                                            </#if>
                                                        </a>
                                                    </#list>
                                                </#if>

                                                <#if articleTop10ByViewsList??>
                                                    <h2 class="text-default animated" data-animation="fadeIn"
                                                        data-animation-delay="50">
                                                        Top Views
                                                    </h2>
                                                    <#list articleTop10ByViewsList as articleTop10ByViews>
                                                        <a href="/Article/${articleTop10ByViews.getId()?c}"
                                                           class="animated" data-animation-delay="60"
                                                           style="display:block;margin-bottom: 5px;font-size: 14px">
                                                            <#if articleTop10ByViews.getTitle()?length gt 28>
                                                            ${articleTop10ByViews.getTitle()?substring(0,28)?html}...
                                                            <#else>
                                                            ${articleTop10ByViews.getTitle()!""}
                                                            </#if>
                                                        </a>
                                                    </#list>
                                                </#if>
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
                                                <#if advert=="true">
                                                    <div class="row nr_tui animated" data-animation="fadeIn"
                                                         data-animation-delay="50">
                                                        <a href="https://s.click.taobao.com/t?e=m%3D2%26s%3DmBRxE1vhSjYcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAoOBw57ELw%2FrBReyEv7GSVZRPAWXiEIX1TxQHrjNFTalRZZz3lSCminhMVVlNrXVQTDVuRn8ddiDsEVVC24eqozO54LQ%2FVw1L9X5LHh3Z8M%2BWS6ALZVeqlk9XUfbPSJC%2F06deTzTIbffYpyF7ku%2BxKguktBpDNMjUsC9aO5Uou70LpxQX9cCZ6Y%3D"
                                                           target="_blank">
                                                            <img src="//cdn.neilren.com/neilren4j/tui/aliyun/gaoxingneng/730-270.jpg"
                                                                 alt="阿里云高性能"/>
                                                        </a>
                                                        <a href="/About/Advert" target="_blank">
                                                            <span>广告</span>
                                                        </a>
                                                    </div>
                                                </#if>
                                                <#if archivesList??>
                                                    <h2 class="text-default animated" data-animation="fadeIn"
                                                        data-animation-delay="50">
                                                        Archives
                                                    </h2>
                                                    <#list archivesList as archives>
                                                        <a href="/Archives/${archives.getDateYmd()}" class="animated"
                                                           data-animation-delay="60"
                                                           style="display:block;margin-bottom: 5px;">
                                                        ${archives.getDateYmd()!""}（${archives.getNumber()!""}）
                                                        </a>
                                                    </#list>
                                                </#if>
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
<script type="text/javascript" src="//cdn.neilren.com/neilren4j/1.0.0/js/jquery.raty.min.js"></script>
<script>
    $(function () {

        $.fn.raty.defaults.path = '//cdn.neilren.com/neilren4j/1.0.0/img';

        $('.score-callback').raty({

            score: function () {

                return $(this).attr('data-score');

            },
            click: function (score, evt) {
                $.ajax({
                    type: "POST",
                    url: "/Article/Grade",
                    data: {
                        longId: $(this).attr('data-id'), grade: score
                    }
                });
            }

        });

    });
</script>
</@defaultLayout.footer>