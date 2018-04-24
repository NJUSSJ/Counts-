// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('drawCharts'));

// 指定图表的配置项和数据

var option = {
    title: {
        text: 'COUNTS用户等级分布统计图'
    },
    tooltip: {},
    legend: {
        data:['LEVEL']
    },
    xAxis: {
        data: ["LEVEL1","LEVEL2","LEVEL3","LEVEL4","LEVEL5","LEVEL6"]
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
/*
var div = myChart.getParent.createElement("div");
div.setAttribute("id","chart0");
div.setAttribute("style","width: 600px;height:400px;");
myChart.getParent.appendChild(div);
*/
/*
var chart0 = echarts.init(document.getElementById("chart0"));
var maskImage = new Image();
maskImage.src = "images/logo.jpg";
chart0.setOption({

    series: [{
    type: 'wordCloud',

    // The shape of the "cloud" to draw. Can be any polar equation represented as a
    // callback function, or a keyword present. Available presents are circle (default),
    // cardioid (apple or heart shape curve, the most known polar equation), diamond (
    // alias of square), triangle-forward, triangle, (alias of triangle-upright, pentagon, and star.

    shape: 'circle',

    // A silhouette image which the white area will be excluded from drawing texts.
    // The shape option will continue to apply as the shape of the cloud to grow.

    maskImage: maskImage,

    // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
    // Default to be put in the center and has 75% x 80% size.

    left: 'center',
    top: 'center',
    width: '70%',
    height: '80%',
    right: null,
    bottom: null,

    // Text size range which the value in data will be mapped to.
    // Default to have minimum 12px and maximum 60px size.

    sizeRange: [12, 60],

    // Text rotation range and step in degree. Text will be rotated randomly in range [-90, 90] by rotationStep 45

    rotationRange: [-90, 90],
    rotationStep: 45,

    // size of the grid in pixels for marking the availability of the canvas
    // the larger the grid size, the bigger the gap between words.

    gridSize: 8,

    // set to true to allow word being draw partly outside of the canvas.
    // Allow word bigger than the size of the canvas to be drawn
    drawOutOfBound: false,

    // Global text style
    textStyle: {
        normal: {
            fontFamily: 'sans-serif',
            fontWeight: 'bold',
            // Color can be a callback function or a color string
            color: function () {
                // Random color
                return 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')';
            }
        },
        emphasis: {
            shadowBlur: 10,
            shadowColor: '#333'
        }
    },

    // Data is an array. Each array item must have name and value property.
    data: [{
        name: 'Farrah Abraham',
        value: 366,
        // Style of single text
        textStyle: {
            normal: {},
            emphasis: {}
        }
    }]
}]
});
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

    tooltip : {
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
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:274, name:'联盟广告'},
                {value:235, name:'视频广告'},
                {value:400, name:'搜索引擎'}
            ].sort(function (a, b) { return a.value - b.value; }),
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
chart0.setOption(option1);