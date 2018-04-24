/**
 * @author fortune julia
 */

/*
parameters for tools bar
 */
var tool1 = document.getElementById("RectTool");
var img1 = document.getElementById("RectTool");
var tool2 = document.getElementById("CurlTool");
var img2 = document.getElementById("CurlTool");
var tool3 = document.getElementById("OverallTag");
var img3 = document.getElementById("OverallTag");
var clicked = "none";

/*
parameters for canvas rect
 */
var canvas = document.getElementById("canvas");
var cxt = canvas.getContext("2d");
var Mousedown = false;//判断鼠标是否落下
var src;

var rect = {
    x:0,y:0,x2:0,y2:0,width:0,height:0
};//实时的矩形对象


//已确定的矩形框
var fixedX = [];
var fixedY = [];
var fixedWidth = [];
var fixedHeight = [];
var index1 = 0;
var index2 = 0;
var colorArray = ["#000000","#66a3ff","#ff4d4d","#33cc33","#ffa31a","#cc33ff","#ff0000","#006600","#0000b3"];

/*
parameters for canvas Curl
 */

var flag  =  false;
var x_curl  =  0; // 鼠标开始移动的位置X
var y_curl  =  0; // 鼠标开始移动的位置Y
var pointList  =  [];
var curlArray=new Array();
var i  =  2;

//data list
var sentences = new Array();
var sentids = new Array();
var sentidsCount = 0;
var _sentences = new Array();
var phoneNumber = "";


//data func
function eachSentence(raw,imgid,sentid,status) {
    this.raw = raw;
    this.splitPart = raw.split(" ");
    this.imgid = imgid;
    this.sentid = sentid;
    this.status = status;
}

//get present collection name and pic name
function getNameAndCollection() {
    var tmp = window.location.search.split("&");
    var collectionName = tmp[0].substring(tmp[0].indexOf("=")+1,tmp[0].length);
    var picName = tmp[1].substring(tmp[1].indexOf("=")+1,tmp[1].length);
    var nameAndCollection = [collectionName,picName];
    return nameAndCollection;
}

/*
parameters for Pic
 */
var Pic_width;
var Pic_height;
var Pic_x;
var Pic_y;
var image = new Image();
var tmp123;

/*
从getImgInfo取json数据得到url 若不为空则加载此url
 */
function loadPic(url) {
    getImgInfo();


    var imgInfo = eval("(" + tmp123 + ")");


    if(imgInfo.fixedx!=null||imgInfo.list!=null) {

        fixedX = imgInfo.fixedx;
        fixedY = imgInfo.fixedy;
        fixedWidth = imgInfo.fixedwidth;
        fixedHeight = imgInfo.fixedheight;
        curlArray = imgInfo.list;

        index1=fixedX.length;
        index2=curlArray.length;

    }

    if(imgInfo!=null){
        sentences=imgInfo.sentences;
        sentids=imgInfo.sentids;
    }

    image.src = url;

    var width = image.width;
    var height = image.height;

    var MulX = canvas.width/width;
    var MulY = canvas.height/height;



    if(MulX<= MulY){
        Pic_width = width*MulX;
        Pic_height = height*MulX;
        Pic_x = 0;
        Pic_y = 0;
    }
    else{
        Pic_width = width*MulY;
        Pic_height = height*MulY;
        Pic_y = 0;
        Pic_x = 0;
    }
    canvas.width=Pic_width;
    canvas.height=Pic_height;

    drawImage();

    var indexOfRectSentence=0;
    var indexOfCurlSentence=0;


    var parent=document.getElementById("textareaPlace");

    for(var i=0;i<fixedX.length;i++){
        if(fixedWidth[i]==0){
            continue;
        }
        var textarea  =  document.createElement("textarea");
        while(imgInfo.sentences[indexOfRectSentence].status!=0){
            indexOfRectSentence++;
            if(indexOfRectSentence==imgInfo.sentences.length){
                break;
            }
        }
        textarea.innerHTML=imgInfo.sentences[indexOfRectSentence].raw;
        indexOfRectSentence++;
        textarea.style.borderColor = colorArray[i];
        textarea.style.margin = "5px";
        textarea.style.width = "250px";
        textarea.id = "rectArea"+i;

        var deleteButton = document.createElement("a");
        deleteButton.innerHTML = "DELETE";
        deleteButton.style.textAlign = "right";
        deleteButton.style.fontSize = "20px";
        deleteButton.style.cursor = "pointer";
        deleteButton.id = ""+i;

        /*
        删除
         */
        deleteButton.addEventListener("click",function () {
            var indexDele = parseInt(this.id);
            var toBeDeleted = document.getElementById("rectArea"+indexDele);//indexDele是什么 是删除的txtareaa的id嘛 我用来删除图片标注了
            var parent = document.getElementById("textareaPlace");

            var tmp1=0;
            for(var i=0;i<=indexDele;i++){
                if(fixedWidth[i]!=0){
                    tmp1++;
                }
            }
            //data
            var tmp2=0;
            var j=0;
            for(j;j<fixedHeight.length;j++){
                if(tmp2==tmp1){
                    break;
                }
                if(imgInfo.sentences[j].status==0){
                    tmp2++;
                }
            }
            sentences.splice(j-1,1);
            sentids.splice(sentids.length - 1);
            sentidsCount--;

            parent.removeChild(toBeDeleted);
            this.style.display="none";
            this.innerHTML="";


            /*
            处理rect数组
             */
            fixedWidth[indexDele]=0;
            fixedHeight[indexDele]=0;

            drawImage();


        })

        parent.appendChild(deleteButton);
        parent.appendChild(textarea);
    }

    for(var i=0;i<curlArray.length;i++){
        if(curlArray[i].length==1){
            continue;
        }

        var textarea=document.createElement("textarea");

        while(imgInfo.sentences[indexOfCurlSentence].status!=1){
            indexOfCurlSentence++;
            if(indexOfCurlSentence==imgInfo.sentences.length){
                break;
            }
        }



        textarea.innerHTML=imgInfo.sentences[indexOfCurlSentence].raw;

        indexOfCurlSentence++;

        textarea.style.borderColor = colorArray[i];
        textarea.style.margin = "5px";
        textarea.style.width = "250px";
        textarea.id="curlArea"+i;
        var deleteButton = document.createElement("a");
        deleteButton.innerHTML = "DELETE";
        deleteButton.style.textAlign = "right";
        deleteButton.style.fontSize = "20px";
        deleteButton.style.cursor = "pointer";
        deleteButton.id="c "+i;

        parent.appendChild(deleteButton);
        textarea.style.borderStyle = "dotted";
        parent.appendChild(textarea);

        /*
        删除曲线
         */

        deleteButton.addEventListener("click", function () {
            var id=this.id.split(" ")[1];
            var indexDele = parseInt(id);
            var toBeDeleted = document.getElementById("curlArea"+indexDele);

            var parent=document.getElementById("textareaPlace");
            parent.removeChild(toBeDeleted);
            this.style.display="none";
            this.innerHTML="";


            /*
            处理curl数组
             */
            curlArray[indexDele]=[0];

            //data 待实现删除方法

            //data

            var tmp1=0;
            for(var i=0;i<=indexDele;i++){
                if(curlArray[i]!=[0]){
                    tmp1++;
                }
            }


            var tmp2=0;
            var j=0;
            for(j;j<imgInfo.sentences.length;j++){
                if(tmp2==tmp1){
                    break;
                }
                if(imgInfo.sentences[j].status==1){
                    tmp2++;
                }
            }


            sentences.splice(j-1,1);
            sentids.splice(sentids.length - 1);
            sentidsCount--;

            drawImage();


        })

    }

    var OverallIndex=0;
    while(imgInfo.sentences[OverallIndex].status!=2){
        OverallIndex++;
        if(OverallIndex==imgInfo.sentences.length){
            break;
        }
    }
    document.getElementById("info").innerHTML=imgInfo.sentences[OverallIndex].raw;
}





/*
绘制已有Rect
 */
function drawRects() {
    for(var i = 0;i<index1;i++){
        cxt.strokeStyle = (colorArray[i]);
        cxt.strokeRect(fixedX[i],fixedY[i],fixedWidth[i],fixedHeight[i]);
    }
    cxt.strokeStyle = (colorArray[index1]);
}

function drawCurl() {
    for(var i=0;i<curlArray.length;i++){
        cxt.moveTo(curlArray[i][0],curlArray[i][1]);
        for(var j=2;j<curlArray[i].length-1;j=j+2){
            cxt.lineTo(curlArray[i][j],curlArray[i][j+1]);
        }
        cxt.strokeStyle=colorArray[i];
        cxt.stroke();
        cxt.beginPath();
    }
}

/*
functions for canvas rect
 */
function drawImage() {
    cxt.drawImage(image,Pic_x,Pic_y,Pic_width,Pic_height);
    drawRects();
    drawCurl();
}

function mouseDownRect(e){
    rect.x = e.offsetX;//起点
    rect.y = e.offsetY;//起点
    Mousedown = true;
}

function mouseUpRect(e){
    //saveImgURL  =  canvas.toDataURL();//保存标注信息
    //alert(saveImgURL);

    rect.x2 = e.offsetX;
    rect.y2 = e.offsetY;
    rect.width = rect.x2-rect.x;
    rect.height = rect.y2-rect.y;
    cxt.strokeRect(rect.x,rect.y,rect.width,rect.height);
    cxt.save();
    Mousedown = false;

    var rectPrompt  =  prompt("请对上一步画框操作进行标注。");
    if(rectPrompt!=  null) {
        alert(rectPrompt);

        var rectTextarea  =  document.getElementById("textareaPlace");

        var textarea  =  document.createElement("textarea");
        textarea.innerHTML  =  rectPrompt;
        textarea.style.borderColor = colorArray[index1];
        textarea.style.margin = "5px";
        textarea.style.width = "250px";
        textarea.id = "rectArea"+index1;

        //data
        sentids.push(sentidsCount++);
        var sentence = new eachSentence(textarea.value,getNameAndCollection(),sentidsCount,0);
        sentences.push(sentence);


        var deleteButton = document.createElement("a");
        deleteButton.innerHTML = "DELETE";
        deleteButton.style.textAlign = "right";
        deleteButton.style.fontSize = "20px";
        deleteButton.style.cursor = "pointer";
        deleteButton.id = ""+index1;

        /*
        删除
         */
        deleteButton.addEventListener("click",function () {
            var indexDele = parseInt(this.id);
            var toBeDeleted = document.getElementById("rectArea"+indexDele);//indexDele是什么 是删除的txtareaa的id嘛 我用来删除图片标注了
            var parent = document.getElementById("textareaPlace");

            //data
            sentences.splice(indexDele,1);
            sentids.splice(sentids.length - 1);
            sentidsCount--;

            parent.removeChild(toBeDeleted);
            this.style.display="none";
            this.innerHTML="";


            /*
            处理rect数组
             */
            fixedWidth[indexDele]=0;
            fixedHeight[indexDele]=0;

            drawImage();


        })

        rectTextarea.appendChild(deleteButton);
        rectTextarea.appendChild(textarea);


        fixedX[index1] = rect.x;
        fixedY[index1] = rect.y;
        fixedWidth[index1] = rect.width;
        fixedHeight[index1] = rect.height;
        index1 = index1+1;
    }else{
        drawImage();
    }
}

function mouseMoveRect(e) {
    if(Mousedown == true){
        rect.x2 = e.offsetX;
        rect.y2 = e.offsetY;
        rect.width = rect.x2-rect.x;
        rect.height = rect.y2-rect.y;
        cxt.clearRect(0,0,canvas.width,canvas.height);//清空
        drawImage();
        cxt.strokeStyle = (colorArray[index1]);
        cxt.strokeRect(rect.x,rect.y,rect.width,rect.height);

    }
}


/*
functions for canvas Curl
 */

/* 画笔 */
function drawPencil(e){
    if(flag){
        cxt.lineTo(e.offsetX,e.offsetY);
        cxt.strokeStyle=colorArray[index2];
        cxt.stroke(); // 调用绘制方法
        if(Math.abs(e.offsetX-pointList[i-2])>2&&Math.abs(e.offsetY-pointList[i-1])>2){
            pointList[i]  =  e.offsetX;
            pointList[i+1]  =  e.offsetY;
            i  =  i + 2;
        }
    }else{
        cxt.beginPath();
    }
}

function mouseDownCurl(e) {
    x_curl  =  e.offsetX; // 鼠标落下时的X
    y_curl  =  e.offsetY; // 鼠标落下时的Y
    pointList[0]=x_curl;
    pointList[1]=y_curl;
    flag  =  true;
}

function mouseMoveCurl(e) {
    drawPencil(e);
}

function mouseUpCurl() {
    flag  =  false;
    //saveImgURL  =  canvas.toDataURL(); // 每次 mouseup 都保存一次画布状态
    var curlPrompt  =  prompt("请对上一步画线操作进行标注。");
    if(curlPrompt !=  null) {
        alert(curlPrompt);
        var curlTextarea  =  document.getElementById("textareaPlace");
        var textarea  =  document.createElement("textarea");
        textarea.id="curlArea"+index2;

        //Store the curl to Array
        var tmp = pointList;
        curlArray[index2]=tmp;
        pointList=[];
        i=2;

        textarea.innerHTML  =  curlPrompt;
        //data
        sentids.push(sentidsCount++);
        var sentence = new eachSentence(textarea.innerHTML,getNameAndCollection(),sentidsCount,1);
        sentences.push(sentence);


        textarea.style.borderColor = colorArray[index2];
        textarea.style.margin = "5px";
        textarea.style.width = "250px";
        var deleteButton = document.createElement("a");
        deleteButton.innerHTML = "DELETE";
        deleteButton.style.textAlign = "right";
        deleteButton.style.fontSize = "20px";
        deleteButton.style.cursor = "pointer";
        deleteButton.id="c "+index2;

        /*
        删除曲线
         */

        deleteButton.addEventListener("click", function () {
            var id=this.id.split(" ")[1];
            var indexDele = parseInt(id);
            var toBeDeleted = document.getElementById("curlArea"+indexDele);

            var parent=document.getElementById("textareaPlace");
            parent.removeChild(toBeDeleted);
            this.style.display="none";
            this.innerHTML="";


            /*
            处理curl数组
             */
            curlArray[indexDele]=[0];

            //data 待实现删除方法

            sentences.splice(indexDele,1);
            sentids.splice(sentids.length - 1);
            sentidsCount--;

            drawImage();


        })
        curlTextarea.appendChild(deleteButton);
        textarea.style.borderStyle = "dotted";
        curlTextarea.appendChild(textarea);
        index2 = index2+1;

    }else{
        drawImage();
    }

}

/*
tool bars ActionListener
 */
tool1.addEventListener("click",function () {
    cxt.clearRect(0,0,canvas.width,canvas.height);//清空
    drawImage();
    cxt.strokeStyle = colorArray[index1];
    if(clicked == "Curl"||clicked == "none"||clicked == "Over"){
        img1.style.boxShadow = "3px 3px 8px #cccccc";
        img2.style.boxShadow = "";
        img3.style.boxShadow = "";
        canvas.style.cursor = "crosshair";
        //清除监听
        canvas.removeEventListener("mousedown",mouseDownCurl);
        canvas.removeEventListener("mousemove",mouseMoveCurl);
        canvas.removeEventListener("mouseup",mouseUpCurl);
        //为画布增加监听事件
        canvas.addEventListener("mousedown",mouseDownRect);
        canvas.addEventListener("mousemove",mouseMoveRect);
        canvas.addEventListener("mouseup",mouseUpRect);
        clicked = "Rect";
    }else if(clicked == "Rect"){
        canvas.removeEventListener("mousedown",mouseDownRect);
        canvas.removeEventListener("mousemove",mouseMoveRect);
        canvas.removeEventListener("mouseup",mouseUpRect);
        img1.style.boxShadow = "";
        canvas.style.cursor = "";
        clicked = "none";
    }



})


tool2.addEventListener("click",function () {
    cxt.clearRect(0,0,canvas.width,canvas.height);//清空
    drawImage();
    cxt.strokeStyle = colorArray[index2];
    if(clicked == "Rect"||clicked == "none"||clicked == "Over"){
        img2.style.boxShadow = "3px 3px 8px #cccccc";
        img1.style.boxShadow = "";
        img3.style.boxShadow = "";
        canvas.style.cursor = "crosshair";
        //清除监听
        canvas.removeEventListener("mousedown",mouseDownRect);
        canvas.removeEventListener("mousemove",mouseMoveRect);
        canvas.removeEventListener("mouseup",mouseUpRect);
        //
        canvas.addEventListener("mousedown",mouseDownCurl);
        canvas.addEventListener("mousemove",mouseMoveCurl);
        canvas.addEventListener("mouseup",mouseUpCurl);
        clicked = "Curl";
    }else if(clicked == "Curl"){
        canvas.removeEventListener("mousedown",mouseDownCurl);
        canvas.removeEventListener("mousemove",mouseMoveCurl);
        canvas.removeEventListener("mouseup",mouseUpCurl);
        img2.style.boxShadow = "";
        canvas.style.cursor = "";
        clicked = "none";
    }

})

tool3.addEventListener("click",function () {
    cxt.clearRect(0,0,canvas.width,canvas.height);//清空
    drawImage();



    if(clicked == "none"||clicked == "Rect"||clicked == "Curl"){
        // 清除监听
        canvas.removeEventListener("mousedown",mouseDownRect);
        canvas.removeEventListener("mousemove",mouseMoveRect);
        canvas.removeEventListener("mouseup",mouseUpRect);

        canvas.removeEventListener("mousedown",mouseDownCurl);
        canvas.removeEventListener("mousemove",mouseMoveCurl);
        canvas.removeEventListener("mouseup",mouseUpCurl);

        img3.style.boxShadow = "3px 3px 8px #cccccc";
        img2.style.boxShadow = "";
        img1.style.boxShadow = "";
        canvas.style.cursor = "";


        var overallPrompt = prompt("请对整体图像进行标注。");
        if(overallPrompt !=  null) {
            alert(overallPrompt);
            document.getElementById("info").innerHTML=overallPrompt;
            clicked = "Over";
        }

    }else if(clicked == "Over"){

    }
    img3.style.boxShadow = "";
    img2.style.boxShadow = "";
    img1.style.boxShadow = "";
    canvas.style.cursor = "";

    //data
    sentids.push(sentidsCount++);
    var txt = document.getElementById("info").innerHTML;
    var sentence = new eachSentence(txt,getNameAndCollection(),sentidsCount,2);
    sentences.push(sentence);

    //重新发送taginfo
    for(var i = 0;i<sentences.length;i++){
        _sentences.push(sentences[i].raw);
    }

})