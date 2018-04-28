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
            //data: adminObj.adminLevelName
            data: ['LEVEL1','LEVEL2','LEVEL3','LEVEL4','LEVEL5','LEVEL6']
        },
        yAxis: {},
        series: [{
            name: 'LEVEL',
            type: 'bar',
            data: adminObj.adminLevelWorkerNum
            //data: [10,20,30,25,44,33]
        }]
    };

// 使用刚指定的配置项和数据显示图表。
    adminChart1.setOption(adminOption1);

/* ------------------------------------------------------------------- */

//根据[userName,mission,quality] 三维图显示用户的标注情况
var adminChart2 = echarts.init(document.getElementById('adminChart2'));

//var _userName = adminObj.adminUserName;
//var _mission = adminObj.adminMissionName;
//var _quality = adminObj.adminUserMissionQuality;
var _userName = ['m1', 'm2', 'm3', 'm4', 'm5', 'm6', 'm7',
    'm8', 'm9', 'm10','m11','m12',
    'm13', 'm14', 'm15', 'm16', 'm17', 'm18',
    'm19', 'm20', 'm21', 'm22', 'm23', 'm24'];
var _mission = ['10', '20', '30',
    '40', '50', '60', '70'];
var _quality =[[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];

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
                //{value:adminObj.adminWorkerNum, name:'众包工人'},
                //{value:adminObj.adminNum, name:'系统管理员'},
                //{value:adminObj.adminStarterNum, name:'众包发起者'},
                {value:40, name:'众包工人'},
                {value:5, name:'系统管理员'},
                {value:20, name:'众包发起者'},
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
                //{value:adminObj.adminFinishedMissionNum, name:'已完成'},
                //{value:adminObj.adminOngoingMissionNum, name:'进行中'},
                {value:30, name:'已完成'},
                {value:40, name:'进行中'},
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