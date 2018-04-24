<<<<<<< HEAD:target/classes/resources/js/DrawCharts.js
alert("start loading Charts");
var sumCharts = 1;

var parent = document.getElementById("chartsArea");
for(var i = 0;i<sumCharts;i++){
    var div = parent.createElement("div");
    div.setAttribute("id","chart" + i + "");
    alert("666");
    div.setAttribute("style","width: 600px;height:400px;");
    parent.appendChild(div);

    var script = parent.createElement("script");
    script.setAttribute("type","text/javascript");
    script.setAttribute("src","js/DrawCharts.js");
    parent.appendChild(script);
}
=======
>>>>>>> b5a200404d73c7f163a27d769c5305dc38a5bb98:target/classes/resources/js/DrawCharts1.js
// 基于准备好的dom，初始化echarts实例
//    var myChart = echarts.init(document.getElementById("drawCharts"));

// 指定图表的配置项和数据
/*
    var option = {
        title: {
            text: 'COUNTS用户等级分布统计图'
        },
        tooltip: {},
        legend: {
            data: ['LEVEL']
        },
        xAxis: {
            data: ["LEVEL1", "LEVEL2", "LEVEL3", "LEVEL4", "LEVEL5", "LEVEL6"]
        },
        yAxis: {},
        series: [{
            name: 'LEVEL',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
*/
    var chart0 = echarts.init(document.getElementById("chart0"));
    var option1 = {
        backgroundColor: '#2c343c',

        title: {
            text: 'Customized Pie',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 274, name: '联盟广告'},
                    {value: 235, name: '视频广告'},
                    {value: 400, name: '搜索引擎'}
                ].sort(function (a, b) {
                    return a.value - b.value;
                }),
                roseType: 'radius',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
<<<<<<< HEAD:target/classes/resources/js/DrawCharts.js
        ]
    };
    chart0.setOption(option1);
=======
        }
    ]
};
chart0.setOption(option1);
>>>>>>> b5a200404d73c7f163a27d769c5305dc38a5bb98:target/classes/resources/js/DrawCharts1.js
