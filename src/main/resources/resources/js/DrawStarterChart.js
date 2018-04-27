/* -------------------------starter---------------------------- */
// 基于准备好的dom，初始化echarts实例
var starterChart1 = echarts.init(document.getElementById('starterChart1'));
var starterObj = getStarterData();
// 指定图表的配置项和数据

var starterOption1 = {
    title : {
        text: '所有任务完成情况统计',
        subtext: '',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['已完成','未完成']
    },
    series : [
        {
            name: '任务数',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                //{value:starterObj.starterFinishedMissionNum, name:'已完成'},
                //{value:starterObj.starterOngoingMissionNum, name:'未完成'},
                {value:10, name:'已完成'},
                {value:20, name:'未完成'},
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
starterChart1.setOption(starterOption1);

/* ------------------------------------------------------------------- */
var starterChart2 = echarts.init(document.getElementById('starterChart2'));
var starterOption2 ={
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
        data:['平均分数','总分数','参与人数']
    },
    xAxis: [
        {
            type: 'category',
            //data: starterObj.starterMissionName,
            data: ['任务1','任务2','任务3','任务4','任务5','任务6','任务7'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '分数',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
                formatter: '{value} 分'
            }
        },
        {
            type: 'value',
            name: '人数',
            min: 0,
            max: 100,
            interval: 20,
            axisLabel: {
                formatter: '{value} 人'
            }
        }
    ],
    series: [
        {
            name:'平均分数',
            type:'bar',
            //data:starterObj.starterCreditAvg
            data:[10.0, 34.9, 27.0, 23.2, 25.6, 36.7, 35.6]
        },
        {
            name:'总分数',
            type:'bar',
            //data:starterObj.starterCreditSum
            data:[80.6, 75.9, 89.0, 96.4, 88.7, 90.7, 80.6]
        },
        {
            name:'参与人数',
            type:'line',
            yAxisIndex: 1,
            //data:starterObj.starterParticipantSum
            data:[10, 20, 12, 4, 6, 6, 8]
        }
    ]
};
starterChart2.setOption(starterOption2);