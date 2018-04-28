/**
 *@author fortune
 */
var userCategory1;
var Tagable1;

function load(num,url,collection,userCategory,Tagable,phoneNumber,picNum) {
    if(userCategory === 3){
        document.getElementById("addMissionBtn").innerHTML = "";
        document.getElementById("addMissionBtn").style.display = "none";
    }

    userCategory1=userCategory;
    Tagable1=Tagable;
    document.getElementById("pictureSection").innerHTML="";
    var numRow=Math.floor(num/3);
    for(var i=0;i<numRow;i++){
        var inner=document.getElementById("pictureSection").innerHTML;
        var added="        <div class=\"row\">\n" +
            "            <div class=\"4u 12u(narrower)\">\n" +
            "\n" +
            "                <section>\n" +
            "                    <img src=\""+
            url+(i*3+1)+".jpg"+
            "\" width=\"400\" height=\"260\">\n" +
            "                    <footer>\n" +
            "                        <ul class=\"buttons\">\n" +
            "                            <li><a href=\"#\" class=\"button special\" id=image"+(i*3+1)+">开始标注</a></li>\n" +
            "                        </ul>\n" +
            "                    </footer>\n" +
            "                </section>\n" +
            "\n" +
            "            </div>\n" +
            "            <div class=\"4u 12u(narrower)\">\n" +
            "\n" +
            "                <section>\n" +
            "                    <img src=\""+
            url+(i*3+2)+".jpg"+
            "\" width=\"400\" height=\"260\">\n" +
            "                    <footer>\n" +
            "                        <ul class=\"buttons\">\n" +
            "                            <li><a href=\"#\" class=\"button special\" id=image"+(i*3+2)+">开始标注</a></li>\n" +
            "                        </ul>\n" +
            "                    </footer>\n" +
            "                </section>\n" +
            "\n" +
            "            </div>\n" +
            "            <div class=\"4u 12u(narrower)\">\n" +
            "\n" +
            "                <section>\n" +
            "                    <img src=\""+
            url+(i*3+3)+".jpg"+
            "\" width=\"400\" height=\"260\">\n" +
            "                    <footer>\n" +
            "                        <ul class=\"buttons\">\n" +
            "                            <li><a href=\"#\" class=\"button special\" id=image"+(i*3+3)+">开始标注</a></li>\n" +
            "                        </ul>\n" +
            "                    </footer>\n" +
            "                </section>\n" +
            "\n" +
            "            </div>\n" +
            "        </div>\n";

        document.getElementById("pictureSection").innerHTML=inner+added;
    }

    var left=num-3*numRow;
    if(left>0){
        var inner1=document.getElementById("pictureSection").innerHTML;
        inner1=inner1+"<div class=\"row\">\n";
        for(var i=0;i<left;i++){

            inner1=inner1+"<div class=\"4u 12u(narrower)\">\n" +
                "\n" +
                "            <section>\n" +
                "                <img src=\""+
                url+(3*numRow+i+1)+".jpg"+
                "\" width=\"400\" height=\"260\">\n" +
                "                <footer>\n" +
                "                    <ul class=\"buttons\">\n" +
                "                        <li><a href=\"#\" class=\"button special\" id=image"+(3*numRow+i+1)+">开始标注</a></li>\n" +
                "                    </ul>\n" +
                "                </footer>\n" +
                "            </section>\n" +
                "\n" +
                "        </div>\n";
        }
        inner1=inner1+"<div>\n";
        document.getElementById("pictureSection").innerHTML=inner1;

    }
    /*
        set href
         */
    for(var i=1;i<=num;i++){
        var a = document.getElementById("image"+i);
        a.href="test1.html?collection="+encodeURI(collection)+"&imageURL="+i+"&phoneNumber="+phoneNumber+"&picNum="+picNum;
    }

    setButtons(num);

}

function setButtons(num) {
    if(Tagable1=="0"){
        var buttons=document.getElementsByClassName("button special");
        for(var i=0;i<=num;i++){
            buttons[i].style.display="none";
        }
    }else {
        var submenu=document.getElementById("submenu");
        submenu.style.display="none";
    }
    if(userCategory1=="1"){
        var submenu=document.getElementById("submenu");
        submenu.style.display="none";
    }
}

function collection(uid, mid){
    this.uid=uid;
    this.mid=mid;
}
function addMissionToUser(missionName, userPhone) {
        //alert(missionName);
        //alert(userPhone);
        var collectionData=new collection(userPhone,missionName);
        $.ajax({
           type: "POST",
           async: false,
           url: "/addMissionToUser",
           contentType: "application/json",
           dataType: "json",
           data: JSON.stringify(collectionData),
           success: function (returnData) {
               if(returnData=="0"){
                   alert("您的等级未达到任务要求！");
               }else if(returnData=="1"){
                   alert("该任务已截止！");
               }
           },
           error: function () {

           }
        });
}

