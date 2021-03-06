<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>


<div class="table-responsive m40" id='table-cont'>
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: 8%"><a href="#" onclick="checkall()">全选</a> / <a href="#" onclick="delcheckall()">取消</a>
            </th>
            <th style="width: 3%">编号</th>
            <th style="width: 3%">城市</th>
            <th style="width: 3%">图标</th>
            <th style="width: 3%">平台</th>
            <th style="width: 3%">名称</th>
            <th style="width: 5%">类型</th>
            <th style="width: 5%">点击量</th>
            <th style="width: 5%">删除状态</th>

            <th style="width: 25%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item"
                           varStatus="xh">
                    <tr>
                        <input type="hidden" id="id" value="${item.id}"/>
                        <td><input type="checkbox" name="pid" value="${item.id}"></td>
                        <td>${xh.count }</td>
                        <td>${item.cityName }</td>
                        <td><c:if test="${not empty item.iconImg }">
                            <img src="${fileUrl}${item.iconImg }"style="width:40px;height:40px" alt=""></c:if>
                        </td>
                        <td><c:forEach items="${sources}" var="s">
                            <c:if test="${item.source == s.id}">${s.name}</c:if>
                        </c:forEach></td>
                        <td>${item.menuName }</td>
                        <td>${item.menuTypeName }</td>
                        <td>${item.clickNum }</td>
                        <td>
                            <c:forEach items="${isDeletes}" var="s1">
                                <c:if test="${s1.id==item.isDelete}">${s1.name}</c:if>
                            </c:forEach>
                        </td>

                        <td>
                            <c:if test="${item.isDelete==0}">
                            <a target="contentF" class="edit_img" title="编辑" onclick="to_edit(${item.id })"></a>
                            </c:if>
                            <c:if test="${item.isDelete==0}">
                                <a target="contentF" onclick="deleteOrRecover(${item.id },1)" class="delete_img"></a>
                            </c:if>
                            <c:if test="${item.isDelete==1}">
                                <a target="contentF" class="recove_img" onclick="deleteOrRecover(${item.id },0)"></a>
                            </c:if>
                            <c:choose>
                                <c:when test="${item.isShow==0}">
                                    <a target="contentF" class="onoff_img" title="状态：显示"
                                       onclick="hiddenOrShow(${item.id },1)"></a>
                                </c:when>
                                <c:otherwise>
                                    <a target="contentF" class="offon_img" title="状态：隐藏"
                                       onclick="hiddenOrShow(${item.id },0)"></a>
                                </c:otherwise>
                            </c:choose>
                            <a target="contentF" title="上移" class="up_img"
                               onclick="sort(${item.id },${item.orderBy},1)"></a>
                            <a target="contentF" title="下移" class="down_img"
                               onclick="sort(${item.id },${item.orderBy},2)"></a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="14">没有找到数据!</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<!-- 分页 -->
<c:if test="${!empty paginator}">
    <c:set var="paginator" value="${paginator}"/>
    <c:set var="target" value="listDiv"/>
    <%@include file="../layout/pagination.jsp" %>
</c:if>

<script type="text/javascript">
    $(function () {
        var headIndex = $("#headIndex").height();
        $("#table-cont").css({maxHeight: allHeight - headIndex - 100 - 17});
        var tableCont = document.querySelector('#table-cont');

        /**
         * scroll handle
         * @param {event} e -- scroll event
         */
        function scrollHandle(e) {
            var scrollTop = this.scrollTop;
            this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
        }

        tableCont.addEventListener('scroll', scrollHandle);
    })

    function deleteOrRecover(id, flag) {
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/update?id=" + id + "&isDelete=" + flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }

    function hiddenOrShow(id, flag) {
        //0隐藏，1显示
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/update?id=" + id + "&isShow=" + flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }


    function to_edit(id) {
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['500px', '450px'],
            content: ['${ctx}/rest/menu/to_update?id=' + id, 'no']
        });
    }

    //排序
    function sort(id, orderById, sort) {
        var data = $("#searchForm").serialize();
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/sort?sort=" + sort + "&orderBy=" + orderById + "&id=" + id,
            async: false, // 此处必须同步
            dataType: "json",
            data: data,
            success: function (data) {
                if (data.state == 0) {
                    $('#searchForm').submit();
                } else {
                    layer.msg("操作频繁！", {icon: 2});
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }

    function checkall() {
        $("input[name='pid']").each(function () {
            this.checked = true;
        });
    }


    function delcheckall() {
        $("input[name='pid']").each(function () {
            this.checked = false;
        });
    }

    function batchDelete(status) {
        var ids = [];
        $("input[name='pid']").each(function () {
            if (this.checked) {
                ids.push($(this).val());
            }
        });
        if(ids.length==0){
            layer.msg("请选择所操作的数据！", {icon: 2})
            return;
        }
        $.ajax({
            type: "post",
            url: "${ctx}/rest/menu/batchUpdate?ids=" + ids + "&isDelete=" + status,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state == 0) {
                    layer.msg("操作成功！", {icon: 1});
                    $('#searchForm').submit();
                }
            },
            error: function (data) {
                if (data.responseText != null) {
                    layer.msg(data.responseText, {icon: 2});
                } else {
                    layer.msg("操作失败！", {icon: 2});
                }
            }
        })
    }

</script>
</body>
</html>