/**
 *@author fortune
 */

function load(num,url,collection) {
    alert(num);
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
            "                            <li><a href=\"#\" class=\"button small\" id=image"+(i*3+1)+">TAT IT</a></li>\n" +
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
            "                            <li><a href=\"#\" class=\"button small\" id=image"+(i*3+2)+">TAG IT</a></li>\n" +
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
            "                            <li><a href=\"#\" class=\"button small\" id=image"+(i*3+3)+">TAG IT</a></li>\n" +
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
                "                        <li><a href=\"#\" class=\"button small\" id=image"+(3*numRow+i+1)+">TAG IT</a></li>\n" +
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
        a.href="test1.html?collection="+collection+"&imageURL="+i;
    }

}

