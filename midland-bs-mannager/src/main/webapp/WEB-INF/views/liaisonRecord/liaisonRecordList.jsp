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


<div class="table-responsive m40">
    <table class="table table-bordered table-add">
        <thead>
            <tr>
                <th style="width: 8%">编号</th>
				<th style="width: 8%">姓名</th>
				<th style="width: 8%">手机号码</th>
                <th style="width: 8%">邮箱</th>
                <th style="width: 8%">主旨</th>
				<th style="width: 8%">留言</th>
				<th style="width: 8%">提交时间</th>
                <%--<th style="width: 10%">操作</th>--%>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td>${xh.count}</td>
						<td>${item.name}</td>
						<td>${item.phone}</td>
                        <td>${item.email}</td>
                        <td><c:forEach items="${categorys}" var="s">
                            <c:if test="${item.category == s.id}">${s.name}</c:if>
                        </c:forEach></td>
						<td>${item.leavingMessage}</td>
						<td>${item.addTime}</td>
						<%--<td>--%>
                            <%--<a target="contentF" onclick="to_edit(${item.id })">编辑</a>--%>
                            <%--<a target="contentF" onclick="delete1(${item.id })">删除</a>--%>
                        <%--</td>--%>
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

    function delete1(id){
        $.ajax({
            type: "post",
            url: "${ctx}/rest/liaisonRecord/update?id="+id+"&isDelete=1",
            async: false, // 此处必须同步
            dataType: "json",

            success: function (data) {
                if (data.state==0){
                    $('#searchForm').submit();
                }else{
                    layer.msg("操作失败！", {icon: 2});
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

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['修改'],
            shade: 0.3,
            area: ['500px', '700px'],
            content: ['${ctx}/rest/liaisonRecord/to_update?id='+id,'no']
        });
    }


</script>
</body>
</html>