<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../layout/tablib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        .table-add tr td a {
            display: inline-block;
            width: 25px;!important;
            height: 20px;
            margin: 0 5px;
            background-size: contain!important;
        }
        td
        {
            white-space: nowrap;
        }
        th
        {
            white-space: nowrap;
        }
    </style>
</head>
<body>


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
        <tr>
            <th style="width: 8%"></th>
            <th style="width: 15%">预览图</th>
            <th style="width: 10%">状态</th>
            <th style="width: 32%">楼盘名称</th>
            <th style="width: 10%">楼盘ID</th>
            <th style="width: 15%">录盘人</th>
            <th style="width: 10%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item"
                           varStatus="xh">
                    <tr>
                        <input type="hidden" id="id" value="${item.id}"/>
                        <td>${xh.count }</td>
                        <td><img src="${item.imgUrl }" style="width:40px;height:40px" alt=""></td>
                        <td><c:choose>
                            <c:when test="${item.isShow==0 }">
                                显示
                            </c:when>
                            <c:otherwise>
                                隐藏
                            </c:otherwise>
                        </c:choose></td>
                        <td>${item.title }</td>
                        <td>${item.housesId }</td>
                        <td>${item.operatorName }</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.isShow==0}">
                                    <a target="contentF" class="onoff_img" title="状态：显示" onclick="hiddenOrShow(${item.id },1)"></a>
                                </c:when>
                                <c:otherwise>
                                    <a target="contentF" class="offon_img" title="状态：隐藏" onclick="hiddenOrShow(${item.id },0)"></a>
                                </c:otherwise>
                            </c:choose>
                            <a target="contentF"   class="edit_img" title="编辑" onclick="to_edit(${item.id })"></a>
                            <a target="contentF"  class="delete_img" title="删除" onclick="delete1(${item.id })"></a>
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
    function hiddenOrShow(id, flag){
        //0隐藏，1显示
        $.ajax({
            type: "post",
            url: "${ctx}/rest/tradeFair/update?id="+id+"&isShow="+flag,
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }


    function delete1(id){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/tradeFair/update?id="+id+"&isDelete=1",
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    layer.msg("删除成功！", {icon: 2});
                    $('#searchForm').submit();
                }
            },
            error: function () {
                layer.msg("操作失败！", {icon: 2});
            }
        })
    }

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['450px', '520px'],
            content: ['${ctx}/rest/tradeFair/to_update?id='+id,'no']
        });
    }


</script>
</body>
</html>