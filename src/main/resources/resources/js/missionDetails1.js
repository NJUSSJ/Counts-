function loadDetails(picNum) {
    var missionName=document.getElementById("Name").innerText;
    var picSection=document.getElementById("picSection");
    for(var i=1;i<=picNum;i++){
        var div=document.createElement("div");
        div.className="4u 12u$(mobile)";
        var a=document.createElement("a");
        a.href="#";
        a.className="image fit";
        var img=document.createElement("img");
        img.src="../../images/"+missionName+"_"+i+".jpg";
        a.appendChild(img);
        div.appendChild(a);
        picSection.appendChild(div);
    }
}

var number0;
var number1;
var number2;
var number3;
var numberSum;
var credit;
var level;

function SingleMissionData(number0, number1, number2, number3, numberSum, credit, level) {
    this.number0=number0;
    this.number1=number1;
    this.number2=number2;
    this.number3=number3;
    this.numberSum=numberSum;
    this.credit=credit;
    this.level=level;
}
function loadChartsData() {

}