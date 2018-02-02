<#macro footer>
<footer id="awd-site-footer">
    <div class="copyright animated" data-animation="fadeInUp" data-animation-delay="500">
        <p style="font-size: 12px;letter-spacing:normal;font-weight: 100;">Copyright &copy; 2012-${.now?string("yyyy")}
            NEILREN.COM All rights reserved.<br/>
            <a href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow">冀ICP备12003293号-5</a>
            -
            <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=13070902000223" target="_blank"
               rel="nofollow">冀公网安备13070902000223号</a>
            -
            <a href="http://creativecommons.org/licenses/by-nc-nd/3.0/cn/" target="_blank"
               rel="nofollow">CC BY-NC-ND/3.0/CN</a>
        </p>
    </div>
    <nav class="social-icons animated" data-animation="fadeInUp" data-animation-delay="500">
        <ul>
        <#--<li title="NEILREN4J">-->
        <#--<iframe class="github-btn" src="https://ghbtns.com/github-btn.html?user=NeilRen&amp;repo=NEILREN4J&amp;type=watch&amp;count=true"-->
        <#--allowtransparency="true" frameborder="0" scrolling="0" width="80px" height="30px"-->
        <#--style="padding-top: 10px;margin-bottom: -5px;"></iframe></li>-->
            <li title="知识共享署名-非商业性使用-禁止演绎 3.0 中国大陆许可协议">
                <a href="http://creativecommons.org/licenses/by-nc-nd/3.0/cn/"
                   title="知识共享署名-非商业性使用-禁止演绎 3.0 中国大陆许可协议"><img
                        src="//cdn.neilren.com/NEILREN_V2/img/CC_80x15.png"
                        alt="知识共享署名-非商业性使用-禁止演绎 3.0 中国大陆许可协议"/>
                </a>
            </li>
            <li title="Github"><a target="_blank" href="//github.com/NeilRen"><i class="fa fa-github"></i></a></li>
            <li title="QQ"><a target="_blank"
                              href="//shang.qq.com/wpa/qunwpa?idkey=bfbde7e5dec79fd3cdb23c7cf590ca698e3da8b48a71369139ed6aa52f9a7513"><i
                    class="fa fa-qq"></i></a></li>
            <li title="微博"><a target="_blank" href="//weibo.com/NeilRen"><i class="fa fa-weibo"></i></a></li>
        </ul>
    </nav>
</footer>
</div>

<!-- JS -->
<script type="text/javascript" src="//cdn.neilren.com/neilren4j/1.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdn.neilren.com/neilren4j/1.0.0/js/vendor.js"></script>
<script type="text/javascript" src="//cdn.neilren.com/neilren4j/1.0.0/js/options.js"></script>
<script type="text/javascript" src="//cdn.neilren.com/neilren4j/1.0.0/js/main.js"></script>
    <#nested>
</body>
</html>

</#macro>