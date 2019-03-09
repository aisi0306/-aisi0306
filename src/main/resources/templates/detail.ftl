<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<div class="g-doc">
<#if !product??>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
<#else>
    <div class="n-show f-cb" id="showContent">
        <input type="hidden" id="cid" value="${product.id}">
        <div class="img"><img src="${product.pictureUrl}" alt="${product.title}" ></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.remark}</p>
            <div class="salePrice">
                价格：
                <span class="v-unit">¥</span>
                <span class="v-value">${product.price}</span>
            </div>
            <div class="num">
                数量：
                <span id="plusNum" class="lessNum"><a>-</a></span>
                <input type="text" id="allNum" class="totalNum" value="1" />
                <span id="addNum" class="moreNum"><a>+</a></span>
            </div>
            <div class="oprt f-cb">
               <#-- <button class="u-btn u-btn-primary" id="buy">
                    立即购买</button>-->
                &emsp;&emsp;
                <button class="u-btn u-btn-primary" id="add">
                    加入购物车</button>
            </div>
                </#if>

        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.text}
    </div>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="/js/detail.js"></script>
</body>
</html>