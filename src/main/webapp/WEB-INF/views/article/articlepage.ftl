<#-- 引入布局指令的命名空间 -->
<#import "../layout/defaultLayout.ftl" as defaultLayout>
<#-- 调用布局指令 -->
<@defaultLayout.head bgcolour="bg-black">
<meta name="keywords" content="${articleWithBLOBs.getKeyword()!""}"/>
<meta name="description" content="${articleWithBLOBs.getDescribes()?html}"/>
<title>${articleWithBLOBs.getTitle()} - 任霏的个人网站与博客 - NEILREN.COM - 关注分享互联网、IT技术、软件应用等计算机科技领域的IT科技博客</title>
<style>
    #comment-ul {
        font-size: 12px;
    }

    #comment-ul li {
        border-bottom: 1px dashed #999999;
    }

    #comment-ul li a {
        color: #999999;
    }

    #comment-ul li .spandiv {
        margin-top: -15px;
    }

    #comment-ul li ul {
        padding-left: 20px;
    }

    #comment-ul li ul li {
        border-bottom: 0;
    }
</style>
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
                                                                        <span class="entry-byline">浏览:${articleWithBLOBs.getViews()!""}</span>
                                                                        <span class="entry-byline">评论:${articleWithBLOBs.getComment()!"0"}</span>
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
                                                            <#if comment=="true">
                                                                <!--Comment-->
                                                                <div class="entry-content">
                                                                    <div class="entry-info">
                                                                        <h3 class="text-default animated"
                                                                            data-animation="fadeIn"
                                                                            data-animation-delay="50">
                                                                            评论
                                                                        </h3>
                                                                        <span style="font-size: 12px;">注意：本站有缓存机制不能立即显示，系统自动审核您的评论，如果不通过将邮件告知您。
                                                                        以下类型不能通过审核：包含垃圾信息/广告/渉政/暴恐/辱骂/色情/灌水/违禁/医药</span>
                                                                    </div>
                                                                    <ul id="comment-ul">
                                                                        <#if articleCommentList??>
                                                                            <#list articleCommentList as Comment>
                                                                                <li id="comment-${Comment.getId()?c}">
                                                                                    <a href="${Comment.getAuthor_url()!'javascript:void(0)'}"
                                                                                       target="_blank"
                                                                                       rel="nofollow">${Comment.getAuthor_name()?html}</a>：${Comment.getComment_content()?html}
                                                                                    <div class="spandiv">
                                                                                        <span>${Comment.getComment_date()?datetime}</span>
                                                                                        <a href="javascript:void(0)"
                                                                                           onclick="ReplyComment(${Comment.getId()?c},'${Comment.getAuthor_name()?html}','${Comment.getAuthor_url()!'javascript:void(0)'}')">回复</a>
                                                                                    </div>
                                                                                    <#if Comment.getReplyCustomer()??>
                                                                                        <ul>
                                                                                            <#list Comment.getReplyCustomer() as comment>
                                                                                                <li id="comment-${comment.getId()?c}">
                                                                                                    <a href="${comment.getAuthor_url()!'javascript:void(0)'}"
                                                                                                       target="_blank"
                                                                                                       rel="nofollow">${comment.getAuthor_name()?html}</a>
                                                                                                    回复 <a
                                                                                                        href="${comment.getReply_url()!'javascript:void(0)'}"
                                                                                                        target="_blank"
                                                                                                        rel="nofollow">${comment.getReply_name()?html}</a>：${comment.getComment_content()?html}
                                                                                                    <div class="spandiv">
                                                                                                        <span>${comment.getComment_date()?datetime}</span>
                                                                                                        <a href="javascript:void(0)"
                                                                                                           onclick="ReplyComment(${comment.getId()?c},'${comment.getAuthor_name()?html}','${comment.getAuthor_url()!'javascript:void(0)'}')">回复</a>
                                                                                                    </div>
                                                                                                </li>
                                                                                            </#list>
                                                                                        </ul>
                                                                                    </#if>
                                                                                </li>
                                                                            </#list>
                                                                        </#if>
                                                                    </ul>
                                                                    <div class="section-info text-left" id="form">
                                                                        <div class="section-info"
                                                                             id="comment-reply-user-div"
                                                                             style="font-size: 12px;display: none;">
                                                                            您将回复给：<a href="javascript:void(0)"
                                                                                     id="comment-reply-user"
                                                                                     target="_blank" rel="nofollow">undefined</a>
                                                                            &nbsp;&nbsp;
                                                                            <a href="javascript:void(0)"
                                                                               onclick="unReplyComment()">取消回复</a>
                                                                        </div>
                                                                        <!-- START comment FORM -->
                                                                        <form id="comment-form"
                                                                              class="contact-form" method="post">
                                                                            <input type="hidden" name="parent_id"
                                                                                   id="parent_id" value="1">
                                                                            <input type="hidden" name="article_id"
                                                                                   id="article_id"
                                                                                   value="${articleWithBLOBs.getId()?c}">
                                                                            <div class="row">
                                                                                <div class="col-lg-4 col-md-6 col-md-12 animated"
                                                                                     data-animation="fadeIn"
                                                                                     data-animation-delay="200">
                                                                                    <input type="text"
                                                                                           name="comment-name"
                                                                                           placeholder="称呼"
                                                                                           class="contact-form-name required">
                                                                                </div>
                                                                                <div class="col-lg-4 col-md-6 col-md-12 animated"
                                                                                     data-animation="fadeIn"
                                                                                     data-animation-delay="200">
                                                                                    <input type="email"
                                                                                           name="comment-email"
                                                                                           placeholder="电子邮箱"
                                                                                           class="contact-form-email required">
                                                                                </div>
                                                                                <div class="col-lg-4 col-md-12 animated"
                                                                                     data-animation="fadeIn"
                                                                                     data-animation-delay="200">
                                                                                    <input type="text"
                                                                                           name="comment-link"
                                                                                           placeholder="您的链接"
                                                                                           class="contact-form-subject required">
                                                                                </div>
                                                                                <!-- END COLUMN 4 -->
                                                                                <div class="col-md-12 animated"
                                                                                     data-animation="fadeIn"
                                                                                     data-animation-delay="150">
									<textarea name="comment-message" placeholder="说两句吧..."
                                              class="contact-form-message required"
                                              rows="4"></textarea>
                                                                                    <button class="btn btn-block"
                                                                                            type="button"
                                                                                            onclick="return subComment()"
                                                                                            id="submitcomment"
                                                                                            name="submitcomment">
                                                                                        <span>发送评论</span>
                                                                                        <i class="fa fa-send"></i>
                                                                                    </button>
                                                                                </div>
                                                                                <!-- END COLUMN 8 -->
                                                                            </div>
                                                                            <!-- END ROW -->
                                                                            <div class="contact-notice"></div>
                                                                        </form>
                                                                        <!-- END comment FORM -->
                                                                    </div>
                                                                </div>
                                                            </#if>
                                                        </#if>
                                                        <#if advert=="true">
                                                            <div class="row">
                                                                <div class="col-md-3">
                                                                    <div class="row nr_tui animated"
                                                                         data-animation="fadeIn"
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
                                                                    <div class="row nr_tui animated"
                                                                         data-animation="fadeIn"
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
                                                                    <div class="row nr_tui animated"
                                                                         data-animation="fadeIn"
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
                                                                    <div class="row nr_tui animated"
                                                                         data-animation="fadeIn"
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
    function ReplyComment(commentid, username, userlink) {
        $("#comment-reply-user").html(username);
        $("#comment-reply-user").attr("href", userlink);
        $("#parent_id").val(commentid);
        $("#comment-reply-user-div").show();
        window.location.hash = "#form";

    }

    function unReplyComment() {
        $("#comment-reply-user").html("undefined");
        $("#comment-reply-user").attr("href", "javascript:void(0)");
        $("#parent_id").val(1);
        $("#comment-reply-user-div").hide();
    }

    function awdFormValidation(email_address) {
        var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
        return pattern.test(email_address);
    }

    function subComment() {
        var $form = $('#comment-form');

        var input = $(this).find('input, textarea');
        var requiredFields = $(this).find('.required');
        var emailField = $('.contact-form-email');
        var commentNameVal = $('.contact-form-name').val();
        var commentSubjectVal = $('.contact-form-subject').val();
        var commentEmailVal = emailField.val();
        var commentMessageVal = $('.contact-form-message').val();
        var commentNotice = $('.contact-notice');
        var commentParent_id = $('#parent_id').val();
        var commentArticle_id = $('#article_id').val();

        if (commentNameVal == '' || commentEmailVal == '' || commentMessageVal == '') {
            commentNotice.stop(true).hide().html('<i class="icons fa fa-close error"></i> 所有字段都是必填的').fadeIn();
            requiredFields.each(function () {
                $(this).addClass("input-error");
            });

        } else if (!awdFormValidation(commentEmailVal)) {
            commentNotice.stop(true).hide().html('<i class="icons fa fa-close error"></i> 电子邮件地址无效').fadeIn();
            emailField.addClass("input-error");
            $('#comment-email').focus();
        }
        else {
            $.ajax({
                        type: 'POST',
                        url: '/Article/Comment',
                        data: {
                            author_name: commentNameVal,
                            author_email: commentEmailVal,
                            comment_content: commentMessageVal,
                            author_url: commentSubjectVal == "" ? "null" : commentSubjectVal,
                            parent_id: commentParent_id,
                            article_id: commentArticle_id
                        },
                        error: function () {
                            commentNotice.stop(true).hide().html('<i class="icons fa fa-close error"></i> 提交失败，发生网络错误').fadeIn();
                            return false;
                        },
                        success: function (data) {
                            if (data.state == 200) {
                                commentNotice.stop(true).hide().html('<i class="icons fa fa-check valid"></i> 信息已提交').fadeIn();
                                $form[0].reset();
                                input.blur();
                                alert(data.message);
                            } else {
                                alert(data.message);
                            }
                            return false;
                        }
                    }
            );
            return false;
        }
        return false;
    }
</script>
</@defaultLayout.footer>