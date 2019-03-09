<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user??>
            <#if 1 == user.userType>
                卖家
            <#else>
                买家
        </#if>你好，<span class="name">${user.account}</span>！<a
                href="/logout">[退出]</a>
        <#else>
            请<a href="/api/login">[登录]</a>
        </#if>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if user?? && user.userType==0>
            <li><a href="/orderRecord">账务</a></li>
            <li><a href="/getShoppingCart">购物车</a></li>
            </#if>
            <#if user?? && user.userType==1>
            <li><a href="/public">添加内容</a></li>
            </#if>
        </ul>
    </div>
</div>