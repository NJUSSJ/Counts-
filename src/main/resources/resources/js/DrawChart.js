var workerChart1 = echarts.init(document.getElementById('workerChart1'));
var workerOption1 = {
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
    ]
};
workerChart1.setOption(workerOption1);

/* ------------------------------------------------------------------- */

// 基于准备好的dom，初始化echarts实例
    var adminChart1 = echarts.init(document.getElementById('adminChart1'));

// 指定图表的配置项和数据

    var adminOption1 = {
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
    adminChart1.setOption(adminOption1);

/* ------------------------------------------------------------------- */


// 基于准备好的dom，初始化echarts实例
var starterChart1 = echarts.init(document.getElementById('starterChart1'));

// 指定图表的配置项和数据

var starterOption1 = {
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
starterChart1.setOption(starterOption1);

/* ------------------------------------------------------------------- */
