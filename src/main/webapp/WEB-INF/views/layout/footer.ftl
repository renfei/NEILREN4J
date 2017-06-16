<#macro footer>
<footer id="awd-site-footer">
    <div class="copyright animated" data-animation="fadeInUp" data-animation-delay="500">
        <p style="font-size: 12px;letter-spacing:normal;font-weight: 100;">Copyright &copy; 2012-${.now?string("yyyy")} NEILREN.COM All rights reserved.<br/>
            <a href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow">冀ICP备12003293号</a>
            -
            <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=13070902000223" target="_blank" rel="nofollow">冀公网安备13070902000223号</a>
        </p>
    </div>
    <nav class="social-icons animated" data-animation="fadeInUp" data-animation-delay="500">
        <ul>
            <li title="圆象"><a target="_blank" href="#"><i class="fa fa-leaf"></i></a></li>
            <li title="QQ"><a href="###"><i class="fa fa-qq"></i></a></li>
            <li title="WeChat"><a href="###"><i class="fa fa-weixin"></i></a></li>
            <li title="微博"><a href="###"><i class="fa fa-weibo"></i></a></li>
            <li title="开始"><a href="###"><i class="fa fa-windows"></i></a></li>
        </ul>
    </nav>
</footer>
</div>

<!-- JS -->
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/vendor.js"></script>
<script type="text/javascript" src="/static/js/options.js"></script>
<script type="text/javascript" src="/static/js/main.js"></script>
    <#nested>
</body>
</html>

</#macro>