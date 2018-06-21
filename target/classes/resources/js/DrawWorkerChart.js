/* ------------------------worker--------------------------------- */
//用户任务完成情况统计
var workerChart1 = echarts.init(document.getElementById('workerChart1'));
var workerObj = getWorkerData();

var workerOption1 = {
    title: [
        {
            text: '一周收益图',
            left: 'center',
        },
        {
            borderColor: '#999',
            borderWidth: 1,
            textStyle: {
                fontSize: 14
            },
            left: '10%',
            top: '90%'
        }
    ],
    xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [82, 93, 90, 93, 129, 30, 56],
        type: 'line'
    }]
};

workerChart1.setOption(workerOption1);

/* ------------------------------------------------------------------- */
//条形折线图 已提交任务中 针对每个任务 ['我的分数','最高分','平均分数']
var workerChart2 = echarts.init(document.getElementById('workerChart2'));
var workerOption2 = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['我的积分','最高积分','平均积分']
    },
    xAxis: [
        {
            type: 'category',
            //data: workerObj.workerMissionName,
            data: ['任务1','任务2','任务3','任务4','任务5','任务6','任务7'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '积分',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
                formatter: '{value} 分'
            }
        },
        {
            type: 'value',
            name: '积分',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
                formatter: '{value} 分'
            }
        }
    ],
    series: [
        {
            name:'我的积分',
            type:'bar',
            //data:workerObj.workerCredit
            data:[10.0, 34.9, 27.0, 23.2, 25.6, 36.7, 35.6]
        },
        {
            name:'最高积分',
            type:'bar',
            //data:workerObj.workerMaxCredit
            data:[20.6, 45.9, 39.0, 26.4, 48.7, 40.7, 50.6]
        },
        {
            name:'平均积分',
            type:'line',
            yAxisIndex: 1,
            //data:workerObj.workerAverageCredit
            data:[14.0, 40.2, 22.3, 24.5, 36.3, 30.2, 40.3]
        }
    ]
};
workerChart2.setOption(workerOption2);
/* ------------------------------------------------------------------- */
//根据[mission,credit,quality] 三维图显示用户的标注情况

var workerChart3 = echarts.init(document.getElementById('workerChart3'));


//var mission = workerObj.workerMissionName;
//var credit = workerObj.workerCredit;
//var quality = workerObj.workerMissionCreditQuality;

var workerOption3 = {
    title: {
        text: '能力雷达图'
    },
    tooltip: {},
    legend: {
        data: ['Me']
    },
    radar: {
        // shape: 'circle',
        name: {
            textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
            }
        },
        indicator: [
            { name: '人物', max: 5},
            { name: '动物', max: 5},
            { name: '风景', max: 5},
            { name: '卡通', max: 5},
            { name: '道路', max: 5},
            { name: '生活', max: 5},
            { name: '其他', max: 5}
        ]
    },
    series: [{
        name: '预算 vs 开销（Budget vs spending）',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
            {
                value : [],
                name : '能力值'
            }
        ]
    }]
};
workerChart3.setOption(workerOption3);
/* ------------------------------------------------------------------- */


var dataRader =  [];
var para1 = new para(phoneNumber);
    $.ajax({
        async: false,
        type: "POST",
        url: "/singleChart/getChart9",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(para1),
        success: function (jsonResult) {
            var jsonString = JSON.stringify(jsonResult);
            dataRader = jsonString.split(",");
        },
        error: function(msg){
            //alert("fail")
        }
    });
    workerChart3.setOption({
        series: [{
            name: '预算 vs 开销（Budget vs spending）',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : dataRader,
                    name : '能力值'
                }
            ]
        }]
    });
