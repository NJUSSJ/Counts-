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

var dataRader =  [];


//var mission = workerObj.workerMissionName;
//var credit = workerObj.workerCredit;
//var quality = workerObj.workerMissionCreditQuality;
var mission = ['m1', 'm2', 'm3', 'm4', 'm5', 'm6', 'm7',
    'm8', 'm9', 'm10','m11','m12',
    'm13', 'm14', 'm15', 'm16', 'm17', 'm18',
    'm19', 'm20', 'm21', 'm22', 'm23', 'm24'];
var credit = ['10', '20', '30',
    '40', '50', '60', '70'];
var quality = [[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];

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
            { name: '人物', max: 6500},
            { name: '动物', max: 16000},
            { name: '风景', max: 30000},
            { name: '卡通', max: 38000},
            { name: '道路', max: 52000},
            { name: '生活', max: 52000},
            { name: '其他', max: 25000}
        ]
    },
    series: [{
        name: '预算 vs 开销（Budget vs spending）',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : [
            {
                value : [0, 15234, 0, 0, 0 , 0],
                name : '能力值'
            }
        ]
    }]
};
workerChart3.setOption(workerOption3);
/* ------------------------------------------------------------------- */