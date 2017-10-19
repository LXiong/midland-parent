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
                <th style="width: 10%"><a href="#" onclick="checkall()" >全选</a> / <a href="#" onclick="delcheckall()" >全部取消</a></th>
                <th style="width: 8%">编号</th>
				<th style="width: 8%">姓名</th>
				<th style="width: 8%">联系方式</th>
				<th style="width: 8%">岗位</th>
				<th style="width: 8%">邮箱</th>
				<th style="width: 8%">接收时间</th>
                <th style="width: 8%">工作城市</th>
                <th style="width: 10%">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${!empty requestScope.items }">
                <c:forEach items="${requestScope.items }" var="item" varStatus="xh">
                    <tr>
						<input type="hidden" id="id" value="${item.id}"/>
                        <td><input type="checkbox" name="pid" value="${item.id}"><input type="hidden" name="url${item.id}" value="${item.enclosureUrl}" ></td>
                        <td>${xh.count}</td>
						<td>${item.deliverer}</td>
						<td>${item.phone}</td>
						<td>${item.post}</td>
						<td>${item.email}</td>
						<td>${item.addTime}</td>
                        <td>${item.cityName}</td>
						<td>
                            <a target="contentF" title="回复" class="reply_img" onclick="to_edit(${item.id })"></a>
                            <a target="contentF" title="下载简历" class="updown_img" href="${ctx}/rest/resumeManager/fileDownload"></a>
                            <a target="contentF" class="delete_img" onclick="delete1(${item.id })"></a>
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

    function delete1(id){
        layer.open({
            type: 1,
            skin: 'layer-style',
            area: ['350px','200px'],
            shadeClose: false, //点击遮罩关闭
            title:['删除市场调研'],
            resize: false,
            scrollbar:false,
            content:
            '<section class = "content" style = "border:none; height:100%;">'+
            '<p style = "text-align: center; font-size:16px; color:#000; margin-top:30px;">您确定要删除当前市场调研吗?</p>'+
            '</section>',
            btn:['确定','取消'],
            yes: function(index){
                $.ajax({
                    type: "post",
                    url: "${ctx}/rest/resumeManager/update?id="+id+"&isDelete=1",
                    cache:false,
                    async:false, // 此处必须同步
                    dataType: "json",
                    success: function(data){
                        if(data.state==0){
                            layer.msg("删除成功！",{icon:1});
                            setTimeout(function(){$("#searchForm").submit();},1000);
                        }else{
                            layer.msg("删除失败！！",{icon:7});
                        }
                        layer.close(index);
                    }
                });
            }
            ,success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
            }
        });

    }

    function to_edit(id){
        layer.open({
            type: 2,
            title: ['回复'],
            shade: 0.3,
            area: ['450px', '350px'],
            content: ['${ctx}/rest/resumeManager/to_update?id='+id,'no']
        });
    }

    function checkall(){
        $("input[name='pid']").each(function(){
            this.checked=true;
        });
    }



    function delcheckall(){
        $("input[name='pid']").each(function(){
            this.checked=false;
        });
    }

    function batchDowload() {
       var fileUrls = [];
       var fileNames =[];
        $("input[name='pid']").each(function(){
            if(this.checked){
                fileUrls.push($(this).next().val());
                fileNames.push($(this).next().val().substring($(this).next().val().lastIndexOf("/")+1,$(this).next().val().length))
            }
        });
        window.location.href="${ctx}/rest/resumeManager/batDownload?filePaths="+fileUrls+"&fileNames="+fileNames;
    }


</script>
</body>
</html>