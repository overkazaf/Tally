	function navButtonClick(){
		$("#navigator li").removeClass("active");
		$("#navigator li a").removeAttr("style");
		$(this).addClass("active").css({"background-color":"#D3D3D3","color":"black"});
		var url = $(this).attr("_href");
		$.ajax({
			type:"post",
			url:url+"?m-random=" + Math.random(),
			dataType:"html",
			success:function(result){
				$("#content").empty().html(result);
			}
		});
		clearInterval(it2);
		clearInterval(it1);
		clearInterval(it3);
	}
	
    function roomadmininfo(e){
    	var info = String($(this).attr("info")).split("#");
    	var name = info[1];
    	var phone = info[2];
    	var posX = e.pageX;
    	var posY = e.pageY;
    	if(typeof name =='undefined'||typeof phone =='undefined'){
    		name = "未定义";
    		phone = "未定义";
    	}
    	$(".infobox").find("#name").html("姓名："+name);
    	$(".infobox").find("#phone").html("电话："+phone);
    	$(".infobox").css({"top":posY,"left":posX});
    	$(".infobox").show();
    }
    
    function onleavpicture(){
    	$(".infobox").hide();
    }
    
    
    function log(msg){
    	console.log(msg);
    }
    