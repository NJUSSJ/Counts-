/**
 *@author Fortune
 **/


var missionNames=new Array();
var index=0;
var userPhone1;
var userCategory1;
function user(userPhone,category){
    this.userPhone=userPhone;
    this.category=category;
}

function loadMain(userPhone,userCategory) {
    var tmpUser = new user(userPhone, userCategory);
    userPhone1=userPhone;
    userCategory1=userCategory;

    if(userCategory==1){
        var li=document.getElementById("extra");
        li.style.display="inline";
        document.getElementById("menuTitle").innerHTML="发布一个新任务";
        document.getElementById("menuTitle").href="/upload.html?userPhone="+userPhone;
    } else {
        var li=document.getElementById("extra");
        li.style.display="none";
    }

    $.ajax({
        async: false,
        method: "POST",
        url: "getCollectionInfo",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(tmpUser),
        success: function takePersonalInfo(returnData) {
            for (var i = 0; i < returnData.length; i++) {
                if (returnData[i] == null) {
                    break;
                }
                missionNames[i] = returnData[i];
                index++;
            }
            setCollection();
        },
        error: function () {
            //alert("fail")
        }
    });

}


function setCollection(){
    var missionNum=index;
    var rowNum=Math.floor(missionNum/3);
    var left=missionNum-rowNum*3;

    document.getElementById("collections").innerHTML="";
    for(var i=0;i<rowNum;i++){
        var rowDiv=document.createElement("div");
        rowDiv.className="row";

        var div1=document.createElement("div");
        div1.className="4u 12u(narrower)";
        var section1=document.createElement("section");
        var header1=document.createElement("header");
        var missionName1=document.createElement("h3");
        missionName1.innerHTML=missionNames[i*3].substring(0,missionNames[i*3].indexOf("^"));
        header1.appendChild(missionName1);
        var a1_1=document.createElement("a");
        a1_1.className="image featured";
        a1_1.href="/details.html?imageURL="+missionNames[i*3].substring(0,missionNames[i*3].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        var img1=document.createElement("img");
        img1.src="missionImages/"+missionNames[i*3].substring(0,missionNames[i*3].indexOf("^"))+"_1.jpg";
        img1.className="collection";
        img1.alt="";
        a1_1.appendChild(img1);

        var footer1=document.createElement("footer");
        var ul1=document.createElement("ul");
        ul1.className="buttons";
        var li1=document.createElement("li");
        var a1_2=document.createElement("a");
        a1_2.className="button small";
        a1_2.href="/details.html?imageURL="+missionNames[i*3].substring(0,missionNames[i*3].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        a1_2.innerHTML="查看任务概况";
        li1.appendChild(a1_2);
        ul1.appendChild(li1);
        footer1.appendChild(ul1);
        section1.appendChild(header1);
        section1.appendChild(a1_1);
        section1.appendChild(footer1);
        div1.appendChild(section1);
        rowDiv.appendChild(div1);

        var div2=document.createElement("div");
        div2.className="4u 12u(narrower)";
        var section2=document.createElement("section");
        var header2=document.createElement("header");
        var missionName2=document.createElement("h3");
        missionName2.innerHTML=missionNames[i*3+1].substring(0,missionNames[i*3+1].indexOf("^"));
        header2.appendChild(missionName2);
        var a2_1=document.createElement("a");
        a2_1.className="image featured";
        a2_1.href="/details.html?imageURL="+missionNames[i*3+1].substring(0,missionNames[i*3+1].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        var img2=document.createElement("img");
        img2.src="missionImages/"+missionNames[i*3+1].substring(0,missionNames[i*3+1].indexOf("^"))+"_1.jpg";
        img2.className="collection";
        img2.alt="";
        a2_1.appendChild(img2);

        var footer2=document.createElement("footer");
        var ul2=document.createElement("ul");
        ul2.className="buttons";
        var li2=document.createElement("li");
        var a2_2=document.createElement("a");
        a2_2.className="button small";
        a2_2.href="/details.html?imageURL="+missionNames[i*3+1].substring(0,missionNames[i*3+1].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        a2_2.innerHTML="查看任务概况";
        li2.appendChild(a2_2);
        ul2.appendChild(li2);
        footer2.appendChild(ul2);
        section2.appendChild(header2);
        section2.appendChild(a2_1);

        section2.appendChild(footer2);
        div2.appendChild(section2);
        rowDiv.appendChild(div2);





        var div3=document.createElement("div");
        div3.className="4u 12u(narrower)";
        var section3=document.createElement("section");
        var header3=document.createElement("header");
        var missionName3=document.createElement("h3");
        missionName3.innerHTML=missionNames[i*3+2].substring(0,missionNames[i*3+2].indexOf("^"));
        header3.appendChild(missionName3);
        var a3_1=document.createElement("a");
        a3_1.className="image featured";
        a3_1.href="/details.html?imageURL="+missionNames[i*3+2].substring(0,missionNames[i*3+2].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        var img3=document.createElement("img");
        img3.src="missionImages/"+missionNames[i*3+2].substring(0,missionNames[i*3+2].indexOf("^"))+"_1.jpg";
        img3.className="collection";
        img3.alt="";
        a3_1.appendChild(img3);

        var footer3=document.createElement("footer");
        var ul3=document.createElement("ul");
        ul3.className="buttons";
        var li3=document.createElement("li");
        var a3_2=document.createElement("a");
        a3_2.className="button small";
        a3_2.href="/details.html?imageURL="+missionNames[i*3+2].substring(0,missionNames[i*3+2].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        a3_2.innerHTML="查看任务概况";
        li3.appendChild(a3_2);
        ul3.appendChild(li3);
        footer3.appendChild(ul3);
        section3.appendChild(header3);
        section3.appendChild(a3_1);

        section3.appendChild(footer3);
        div3.appendChild(section3);
        rowDiv.appendChild(div3);

        document.getElementById("collections").appendChild(rowDiv);
    }
    var rowDiv=document.createElement("div");
    rowDiv.className="row";
    for(var i=0;i<left;i++){
        var div1=document.createElement("div");
        div1.className="4u 12u(narrower)";
        var section1=document.createElement("section");
        var header1=document.createElement("header");
        var missionName1=document.createElement("h3");
        missionName1.innerHTML=missionNames[rowNum*3+i].substring(0,missionNames[rowNum*3+i].indexOf("^"));
        header1.appendChild(missionName1);
        var a1_1=document.createElement("a");
        a1_1.className="image featured";
        var urlString="/details.html?imageURL="+missionNames[rowNum*3+i].substring(0,missionNames[rowNum*3+i].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        a1_1.href=encodeURI(urlString);
        var img1=document.createElement("img");
        img1.src="missionImages/"+missionNames[rowNum*3+i].substring(0,missionNames[rowNum*3+i].indexOf("^"))+"_1.jpg";
        img1.className="collection";
        img1.alt="";
        a1_1.appendChild(img1);


        var footer1=document.createElement("footer");
        var ul1=document.createElement("ul");
        ul1.className="buttons";
        var li1=document.createElement("li");
        var a1_2=document.createElement("a");
        a1_2.className="button small";
        a1_2.href="/details.html?imageURL="+missionNames[rowNum*3+i].substring(0,missionNames[rowNum*3+i].indexOf("^"))+"&userPhone="+userPhone1+"&userCategory="+userCategory1+"&Tagable=0";
        a1_2.innerHTML="查看任务概况"
        li1.appendChild(a1_2);
        ul1.appendChild(li1);
        footer1.appendChild(ul1);
        section1.appendChild(header1);
        section1.appendChild(a1_1);

        section1.appendChild(footer1);
        div1.appendChild(section1);
        rowDiv.appendChild(div1);
    }

    document.getElementById("collections").appendChild(rowDiv);

}