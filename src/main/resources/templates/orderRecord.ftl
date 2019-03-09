<!DOCTYPE html>
<html>
<#include "include/head.ftl">
<body>
<#include "include/support.ftl">
<#include "include/header.ftl">
<#assign total = 0>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <#if !buyList?? || !buyList?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <table class="m-table m-table-row n-table g-b3" id="newTable">
        <thead>
        <tr>
            <th>內容圖片</th>
            <th colspan="2">内容信息</th>
            <th>购买时间</th>
            <th>单价</th>
            <th>购买数量</th>
        </tr>
        </thead>
        <tbody>
            <#list buyList as x>
            <#assign total = total + x.price>
            <tr>
                <td class="img"><a href="/snapshot?id=${x.id}"><img src="${x.pictureUrl}" alt=""></a></td>
                <td class="title"><h4><a href="/snapshot?id=${x.id}">${x.title}</a></h4></td>
                <td class="time"><span class="v-time">${x.orderTime?string("yyyy-MM-dd HH:mm")}</span></td>
                <td class="price"><span class="v-unit">¥</span><span class="value">${x.price}</span></td>
                <td class="num"><span class="v-num">${x.count}</span></td>
<#--
                <td class="payment"><span class="v-unit">¥</span><span class="value">${x.total}</span></td>
-->
            </tr>
            </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="5"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span class="value">${total}</span></td>
        </tr>
        </tfoot>
    </table>
    </#if>
</div>
<#include "include/footer.ftl">
</body>
</html>