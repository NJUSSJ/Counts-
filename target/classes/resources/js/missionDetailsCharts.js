var chart1 = echarts.init(document.getElementById('chart1'));
var option1 = {
    title: {
        text: '提交质量分布'
    },
    tooltip: {},
    legend: {
        data:['人数']
    },
    xAxis: {
        data: ["10-20","20-30","30-40","40-50","50-60","60-70", "70-80", "80-90", "90-100"]
    },
    yAxis: {},
    series: [{
        name: '人数',
        type: 'bar',
        data: [5, 20, 36, 10, 42, 36, 20,23,8]
    }]
};
chart1.setOption(option1);

var chart2 = echarts.init(document.getElementById('chart2'));
var option2 = {
    title: {
        text: '积分关于工人的等级分布'
    },
    tooltip: {},
    legend: {
        data:['积分总数']
    },
    xAxis: {
        data: ["Level 1-10","Level 11-20","Level 21-30","Level 31-40", "Level 41-50", "Level 51-60"]
    },
    yAxis: {},
    series: [{
        name: '积分总数',
        type: 'bar',
        data: [11, 22, 64, 0,0 ]
    }]
};
chart2.setOption(option2);
