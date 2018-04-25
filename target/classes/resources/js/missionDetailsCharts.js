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
        data: ["差","中","良","优"]
    },
    yAxis: {},
    series: [{
        name: '人数',
        type: 'bar',
        data: [5, 20, 36, 10]
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
        data: ["Level1","evel2","Level3","Level4"]
    },
    yAxis: {},
    series: [{
        name: '积分总数',
        type: 'bar',
        data: [203, 201, 398, 166]
    }]
};
chart2.setOption(option2);
