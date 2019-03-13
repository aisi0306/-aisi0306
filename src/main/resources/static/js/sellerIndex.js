$(function () {

});



/**
 * 删除内容
 * @param id 内容ID
 */
function del(id) {
    layer.open({
        title: '提示',
        content: '确认删除该内容吗？',
        btn: ['确定', '关闭'],
        yes: function (index) {
            var param = {"cid": id};
            console.log(param);
            $.ajax({
                url: "/content/delete",
                type: "post",
                data: param,
                success: function (data) {
                    console.log(data);
                    if(data.code == "200") {
                        layer.open({
                            title: '提示',
                            content: '删除成功',
                            btn: ['确定'],
                            icon: 1,
                            btn1: function (index) {
                                window.location.reload(true);
                            }
                        });
                    } else {
                        layer.open({
                            title: '提示',
                            content: data.message,
                            icon: 2
                        });
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
        },
        cancel: function (index) {
            layer.close(index);
        }
    });
}