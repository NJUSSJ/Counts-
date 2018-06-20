var already = RandomNum(60,100);
var rest= RandomNum(10,30);
var chart1 = echarts.init(document.getElementById('chart1'));
var option1 = {
    title : {
        text: '任务当前分派状况',
        subtext: '实时数据',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    series : [
        {
            name: '',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:already, name:'已接人数'},
                {value:rest, name:'剩余名额'}


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
chart1.setOption(option1);

function RandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.floor(Rand * Range); //舍去
    return num;
}