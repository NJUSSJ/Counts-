var size = 0;
function loadTagType(tagType, missionLabel) {
    size = missionLabel.size();
    if(tagType === 1){
        document.getElementById("picTools").innerHTML = "";
        document.getElementById("textareaPlace").innerHTML = "";
        //String testBtn = "<li><a href=\"#\" class=\"button special\" id=\"123\"/> 测试标签</li>";
        var btn = "";
        for(var i =0;i<missionLabel.size();i++){
            btn += "<li><a href=\"#\" class=\"button special\" id=missionLabel_"+ i +"/> missionLabel[i]</li>";
            btn += "";
        }
        document.getElementById("missionLabel").innerHTML = btn;
    }
}
