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
            chart1Data =returnData;
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