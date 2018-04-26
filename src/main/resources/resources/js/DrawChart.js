/* ------------------------worker--------------------------------- */
//用户任务完成情况统计
var workerChart1 = echarts.init(document.getElementById('workerChart1'));
var workerObj = getWorkerData();

var workerOption1 = {
        title : {
            text: '用户任务完成情况统计',
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
                    {value:workerObj.workerFinishedMissionNum, name:'已完成'},
                    {value:workerObj.workerUnfinishedMissionNum, name:'未完成'},
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
        data:['我的分数','最高分数','平均分数']
    },
    xAxis: [
        {
            type: 'category',
            data: workerObj.workerMissionName,
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
            name: '分数',
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
            name:'我的分数',
            type:'bar',
            data:workerObj.workerCredit
        },
        {
            name:'最高分数',
            type:'bar',
            data:workerObj.workerMaxCredit
        },
        {
            name:'平均分数',
            type:'line',
            yAxisIndex: 1,
            data:workerObj.workerAverageCredit
        }
    ]
};
workerChart2.setOption(workerOption2);
/* ------------------------------------------------------------------- */
//根据[mission,credit,quality] 三维图显示用户的标注情况
var workerChart3 = echarts.init(document.getElementById('workerChart3'));

var mission = workerObj.workerMissionName;
var credit = workerObj.workerCredit;
var quality = workerObj.workerMissionCreditQuality;

var workerOption3 = {
    tooltip: {},
    visualMap: {
        max: 20,
        inRange: {
            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
        }
    },
    xAxis3D: {
        type: 'category',
        data: mission
    },
    yAxis3D: {
        type: 'category',
        data: credit
    },
    zAxis3D: {
        type: 'value'
    },
    grid3D: {
        boxWidth: 200,
        boxDepth: 80,
        viewControl: {
            // projection: 'orthographic'
        },
        light: {
            main: {
                intensity: 1.2,
                shadow: true
            },
            ambient: {
                intensity: 0.3
            }
        }
    },
    series: [{
        type: 'bar3D',
        data: quality.map(function (item) {
            return {
                value: [item[1], item[0], item[2]],
            }
        }),
        shading: 'lambert',

        label: {
            textStyle: {
                fontSize: 16,
                borderWidth: 1
            }
        },

        emphasis: {
            label: {
                textStyle: {
                    fontSize: 20,
                    color: '#900'
                }
            },
            itemStyle: {
                color: '#900'
            }
        }
    }]
}
workerChart3.setOption(workerOption3);
/* ------------------------------------------------------------------- */

/* ------------------------admin--------------------------------- */

// 基于准备好的dom，初始化echarts实例
    var adminChart1 = echarts.init(document.getElementById('adminChart1'));
    var adminObj = getAdminData();
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
            data: adminObj.adminLevelName
        },
        yAxis: {},
        series: [{
            name: 'LEVEL',
            type: 'bar',
            data: adminObj.adminLevelWorkerNum
        }]
    };

// 使用刚指定的配置项和数据显示图表。
    adminChart1.setOption(adminOption1);

/* ------------------------------------------------------------------- */

//根据[userName,mission,quality] 三维图显示用户的标注情况
var adminChart2 = echarts.init(document.getElementById('adminChart2'));

var _userName = adminObj.adminUserName;
var _mission = adminObj.adminMissionName;
var _quality = adminObj.adminUserMissionQuality;

var adminOption2 = {
    tooltip: {},
    visualMap: {
        max: 20,
        inRange: {
            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
        }
    },
    xAxis3D: {
        type: 'category',
        data: _userName
    },
    yAxis3D: {
        type: 'category',
        data: _mission
    },
    zAxis3D: {
        type: 'value'
    },
    grid3D: {
        boxWidth: 200,
        boxDepth: 80,
        viewControl: {
            // projection: 'orthographic'
        },
        light: {
            main: {
                intensity: 1.2,
                shadow: true
            },
            ambient: {
                intensity: 0.3
            }
        }
    },
    series: [{
        type: 'bar3D',
        data: _quality.map(function (item) {
            return {
                value: [item[1], item[0], item[2]],
            }
        }),
        shading: 'lambert',

        label: {
            textStyle: {
                fontSize: 16,
                borderWidth: 1
            }
        },

        emphasis: {
            label: {
                textStyle: {
                    fontSize: 20,
                    color: '#900'
                }
            },
            itemStyle: {
                color: '#900'
            }
        }
    }]
}
adminChart2.setOption(adminOption2);
/* ------------------------------------------------------------------- */

//'众包工人','系统管理员','众包发起者' 三种角色人数环形图
var adminChart3 = echarts.init(document.getElementById('adminChart3'));
var adminOption3 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['众包工人','系统管理员','众包发起者']
    },
    series: [
        {
            name:'用户数',
            type:'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:adminObj.adminWorkerNum, name:'众包工人'},
                {value:adminObj.adminNum, name:'系统管理员'},
                {value:adminObj.adminStarterNum, name:'众包发起者'},
            ]
        }
    ]
};
adminChart3.setOption(adminOption3);
/* ------------------------------------------------------------------- */
//所有任务已完成未完成数
var adminChart4 = echarts.init(document.getElementById('adminChart4'));
var adminOption4 = {
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
        data: ['已完成','进行中']
    },
    series : [
        {
            name: '任务数',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:adminObj.adminFinishedMissionNum, name:'已完成'},
                {value:adminObj.adminOngoingMissionNum, name:'进行中'},
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
adminChart4.setOption(adminOption4);
/* ------------------------------------------------------------------- */

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
                {value:starterObj.starterFinishedMissionNum, name:'已完成'},
                {value:starterObj.starterOngoingMissionNum, name:'未完成'},
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
            data: starterObj.starterMissionName,
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
            data:starterObj.starterCreditAvg
        },
        {
            name:'总分数',
            type:'bar',
            data:starterObj.starterCreditSum
        },
        {
            name:'参与人数',
            type:'line',
            yAxisIndex: 1,
            data:starterObj.starterParticipantSum
        }
    ]
};
starterChart2.setOption(starterOption2);