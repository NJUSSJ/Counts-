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
        img.src="../../images/"+missionName+"_"+i+"";
        a.appendChild(img);
        div.appendChild(a);
        picSection.appendChild(div);
    }
}