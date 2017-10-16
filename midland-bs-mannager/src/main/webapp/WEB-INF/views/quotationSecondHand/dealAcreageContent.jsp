<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>美联物业 - 关于平台</title>
<link rel="stylesheet" href="${ctx }/assets/css/common.css">

</head>
<body>
<!--关于平台界面-->
	<div class="box" style = "width:auto;">
		<section class = "content" style = "width:auto;">
			<dt style = "text-align:center;">
				<div class = "canva" id="main" style="width: 90%;height:600px;text-align: center;" ></div>
			</dt>
			</dl>
		</section>
	</div>
</body>
</html>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));


    options = {
        title: {
            text: '二手房-面积'
        },

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#ffffff'
                }
            }
        },
        toolbox: {
            feature: {
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['成交面积','成交面积环比']
        },
        xAxis: [
            {
                type: 'category',
                data: ${months},
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '成交面积',
                min: ${listMin},
                max: ${listMax},
                interval:${listStep},
                axisLabel: {
                    formatter: '{value} m²'
                }
            },
            {
                type: 'value',
                name: '环比 [(当月-上月)/上月]*100',
                min: ${ratioMin},
                max: ${ratioMax},
                interval:${ratioStep},
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],

        series: [
            {
                name:'成交面积',
                type:'bar',
                data:${acreageList},
                barWidth:'20',
                itemStyle:{
                    normal:{
                        color:'#b6a2de',
                        barBorderRadius:[6,6,6,6]
                    }
                }
            },

            {
                name:'成交面积环比',
                type:'line',
                yAxisIndex: 1,
                data:${acreageRatioList},
				barWidth:'20',
                smooth: true,
				itemStyle:{
                    normal:{
                        color:'#1ddeb6',
						barBorderRadius:[1,1,1,1]
                    }
                }
            }
        ]
    };

    myChart.setOption(options);

</script>
