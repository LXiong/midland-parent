<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/tablib.jsp"%>
<%@include file="../layout/source.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	
	
	<!--用户列表界面-->
	<div class="box"> 
		<section class = "content">
			<p class = "detail-title">
				<span>用户列表</span>
				<a class = "setup"  target="contentF" onclick="toAddPage()">创建用户</a>
			</p>
		<form action="${ctx }/rest/user/userList" method="POST" id="searchForm"
				onsubmit="submitSearchRequest('searchForm','listDiv');return false;">
			<ul class = "userinfo row">
				<li><span>昵称：</span><input type="text" name="username" id="username" placeholder="请输入用户名" /></li>
				<li><span>手机号码：</span><input type="text" name="phone" id="phone" placeholder="请输入手机号码" /></li>
				<li><span>实名状态：</span><input type="text" name="auditStatus" id="auditStatus" placeholder="请输入实名状态" /></li>
				<li><span>注册来源：</span>
					<select name="source" id="source" class="dropdown">
						<option value="" >请选择</option>
						<option value="0">网站</option>
						<option value="1">微商</option>
					</select>
				</li>
				<li><span>审核人：</span><input type="text" name="auditName" id="auditName" placeholder="请输入审核人" /></li>
				<li><span>注册时间</span><input class="Wdate half" id="time1"
										onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'time2\')}'})"
										name="startTime" /> <em class = "gang">-</em><input
						class="Wdate half"
						onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'time1\')}'})"
						id="time2" name="endTime" /></li>

				<%--<li>--%>
					<%--<span style = "float:left;">用户类型：</span>--%>
					<%--<select name="userType" id="userType" class="dropdown">--%>
						<%--<option value="">全部</option>--%>
					    <%--<option value="0">智者汇</option>--%>
					    <%--<option value="1">渠道服务商</option>--%>
					    <%--<option value="2">终端服务商</option>--%>
					    <%--<option value="3">安装专员</option>--%>
					<%--</select>--%>
				<%--</li>--%>
				<li><input class = "public_btn bg1" type="submit" name="inquery" id="inquery" value = "查询"/></li>
			</ul>
			</form>
			<a href="#" onclick="exportUsers()">导出</a>

			<div id="listDiv"></div>
		</section>
	</div>
	
	
	<script type="text/javascript">
		/* $(function(){
			$('#searchForm').submit();
		}); */
		 window.onload = function(){
             $('#searchForm').submit();
		}


        function exportUsers(){
            var data = $("#searchForm").serialize();

            window.location.href="${ctx}/rest/user/export?"+data;
        }


		function toAddPage(){
			layer.open({
				type: 2,
				skin: 'layer-style',
				area: ['500px','500px'],
				shadeClose: false, //点击遮罩关闭
				title:['创建用户'],
				resize: false,
				scrollbar:false,
				content:['${ctx}/rest/user/toAddPage', 'no']
			});
		}
	</script>
	<!-- 本页私有js -->
	
	
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/jquery.easydropdown.js" ></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/assets/scripts/base.js" ></script>
	<script src="${ctx}/assets/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>