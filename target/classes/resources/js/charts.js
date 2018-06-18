function loadChart1(mid) {
    var chart1Data = "";

    $.ajax({
        async: false,
        type: "POST",
        url: "/singleChart/getChart1",
        contentType: "application/json",
        dataType: "json",
        data: mid,
        success: function (returnData){
            chart1Data = returnData;
        },
        error: function(){
            alert("fail13");
        }
    });

    var uid = chart1Data.uid;
    var money = chart1Data.money;

    var chart1 = document.getElementById("_chart1");
    var option1 = {
        xAxis: {
            type: 'category',
            data: uid,//['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']//uid
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: money,//[120, 200, 150, 80, 70, 110, 130],//money
            type: 'bar'
        }]
    };
    chart1.setOption(option1);
}

/* ------------------------------------------------------------------- */
function loadChart3(mid) {
    var chart3Data = "";

    $.ajax({
        async: false,
        type: "POST",
        url: "/singleChart/getChart3",
        contentType: "application/json",
        dataType: "json",
        data: mid,
        success: function (returnData){
            chart3Data = returnData;
        },
        error: function(){
            alert("fail13");
        }
    });

    var chart3 = echarts.init(document.getElementById('_chart3'));
    var option3 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['总支出', '总收入']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
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
                data: [
                    {value: 335, name: '总支出'},
                    {value: 310, name: '总收入'}
                ]
            }
        ]
    };
    chart3.setOption(option3);
}