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
                {value:finishedNames.length+1, name:'已完成'},
                {value:missionNames.length+1, name:'未完成'},
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
        data:['平均获得积分','总积分','参与人数']
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


/* ------------------------------------------------------------------- */
var data = echarts.dataTool.prepareBoxplotData([
    [5, 4, 6, 8, 9, 10, 8, 8, 2, 10],
    [6, 6, 7, 9, 9, 10, 4, 6, 6, 6],
    [2, 4, 6, 8, 4, 5, 3, 6, 3, 1],
    [4, 6, 7, 9, 5, 10, 5, 7, 10, 1],
    [1, 9, 7, 9, 10, 7, 9, 10, 10, 8]
]);

var starterChart3 = echarts.init(document.getElementById('starterChart3'));
var starterOption3 ={
    title: [
        {
            text: '任务质量分布盒状图',
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
    tooltip: {
        trigger: 'item',
        axisPointer: {
            type: 'shadow'
        }
    },
    grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
    },
    xAxis: {
        type: 'category',
        data: data.axisData,
        boundaryGap: true,
        nameGap: 30,
        splitArea: {
            show: false
        },
        axisLabel: {
            formatter: 'mission {value}'
        },
        splitLine: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        name: 'correct answer',
        splitArea: {
            show: true
        }
    },
    series: [
        {
            name: 'boxplot',
            type: 'boxplot',
            data: data.boxData,
            tooltip: {
                formatter: function (param) {
                    return [
                        'Mission ' + param.name + ': ',
                        'upper: ' + param.data[5],
                        'Q3: ' + param.data[4],
                        'median: ' + param.data[3],
                        'Q1: ' + param.data[2],
                        'lower: ' + param.data[1]
                    ].join('<br/>')
                }
            }
        },
        {
            name: 'outlier',
            type: 'scatter',
            data: data.outliers
        }
    ]
};
starterChart3.setOption(starterOption3);