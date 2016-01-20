    function freshpage(){
    	$("#navigator a[_href=setting]").click();
    }
	function jfinfomodify(row) {
		$("#jfcfgok").removeAttr("onclick").on("click",modal1submit);
		var value = [];
		var posx = document.body.clientWidth;
		var posy = document.body.clientHeight;
		var width = $("#themodal").width();
		$("#themodal").css({
			"top" : posy / 2 - width / 2,
			"left" : posx / 2 - 175
		});
		var row = $("#jfinfotabel").find("tr:eq(" + row + ")");
		var tds = row.find("td");
		for (i = 0; i < tds.length; i++) {
			value[i] = $(tds[i]).html();
		}
		var adminid = $(tds[2]).attr('adminid');
		$("#ul1 li").siblings().removeClass("active");
		var t = $('a[adminid="' + adminid + '"]').parent("li:first").addClass(
				"active");
		$("#themodal li:eq(0) input").attr("disabled",true); 
		$("#themodal li:eq(0) input").val(value[0]);
		$("#themodal li:eq(1) input").val(value[1]);
		$("#themodal li:contains(机房地址：) input").val(value[4]);
		$("#adminname").html(value[2]);
		$("#mtips").addClass("fade");
		$("#themodal").modal("show");
	}
	function thresholdmodify(row) {
		var posx = document.body.clientWidth;
		var posy = document.body.clientHeight;
		var width = $("#themoda2").width();

		$("#themoda2").css({
			"top" : posy / 2 - width / 2 - 20,
			"left" : posx / 2 - 175
		});
		$("#mtipsthreshold").addClass("fade");
		$("#themoda2").modal("show");
		var row = $("#thresholdtab").find("tr:eq(" + row + ")");
		var tds = row.find("td");
		for (i = 0; i < tds.length; i++) {
			$("#themoda2 li:eq(" + i + ") input").val($(tds[i]).html());
		}
		$("#themoda2 li:eq(0) input").attr("disabled",true);
		$("#themoda2 li:eq(1) input").attr("disabled",true);
	}
	function adminmodify(row) {
		var posx = document.body.clientWidth;
		var posy = document.body.clientHeight;
		var width = $("#themodaadmin").width();
		$("#themodaadmin").css({
			"top" : posy / 2 - width / 2,
			"left" : posx / 2 - 175
		});
		var row = $("#admintab").find("tr:eq(" + row + ")");
		var tds = row.find("td");
		var fors = ["adminid","name","phone","position"];
		var index = [0,1,3,4]
		for(i=0;i<fors.length;i++){
			var t = fors[i];
			$("#themodaadmin").find("input[name="+t+"]").val($(tds[index[i]]).html());
		}
		$("#themodaadmin li:eq(0) input").attr("disabled",true);
		$("#mtipsadmin").addClass("fade");
		$('#ulsex li').removeClass("active"); 
		var sexval = $(tds[2]).html();
		$('#ulsex a:contains('+sexval.trim()+')').parent("li:first").addClass("active");
		$("#adminsex").html($(tds[2]).html());
		$("#themodaadmin").modal("show");
	}
	function initmodal() {
		var url = BASEURL+"setting/getadmins";
		$
				.ajax({
					type : "post",
					url : url,
					dataType : 'json',
					success : function(result) {
						var ul1 = $("#ul1");
						$("#ul1").empty();
						for (i = 0; i < result.length; i++) {
							var li = $('<li role="presentation"><a role="menuitem" tabindex="-1" href="#" adminid='+result[i].adminid+'>'
									+ result[i].name + '</a></li>');
							ul1.append(li);
						}
					},
					error : function() {

					}
				});
		var urllistjf = BASEURL+"setting/listjfs";
		$.ajax({
			type:'post',
			url:urllistjf,
			dataType:'json',
			success:function(result){
				var ul1 = $("#uljfaddress");
				$("#uljfaddress").empty();
				for (i = 0; i < result.length; i++) {
					var li = $('<li role="presentation"><a role="menuitem" tabindex="-1" href="#" jfid='+result[i].jfid+'>'
							+ result[i].address + '</a></li>');
					ul1.append(li);
				}
			},
			error:function(){
				
			}
		});
	}
	function modal1submit() {
		var jfid = $("#themodal li:eq(0) input").val();
		var port = $("#themodal li:eq(1) input").val();
		var adminid = $("#ul1 .active > a").attr("adminid");
		var address = $("#themodal li:contains(机房地址：) input").val();
		var data = {};
		data.jfid = jfid;
		data.port = port;
		data.adminid = adminid;
		data.address = address;
		var url = BASEURL+"setting/updatejfconfig";
		$.ajax({
			type : 'post',
			data : JSON.stringify(data),
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			url : url,
			success : function(result) {
				if (result == '1') {
					$("#mtips").removeClass("fade");
					$("#mtips div").removeClass("alter-danger").addClass(
							"alter-success").html("更新成功");
				} else {
					$("#mtips").removeClass("fade");
					$("#mtips div").removeClass("alter-success").addClass(
							"alert-danger").html("更新失败");
				}
			},
			error : function() {
				console.log("error");
			}
		});

	}
	function thresholdsubmit() {
		var data = {};
		var property = [ "jfid", "address", "temphold", "humlityhold",
				"lighthold", "shakehold", "humanhold" ];
		for (i = 0; i < property.length; i++) {
			data[property[i]] = $("#themoda2 li:eq(" + i + ") input").val();
		}
		var url = BASEURL+"setting/updatethreshold";
		$.ajax({
			type : 'post',
			data : JSON.stringify(data),
			url : url,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				if (result == '1') {
					$("#mtipsthreshold").removeClass("fade");
					$("#mtipsthreshold div").removeClass("alter-danger")
							.addClass("alter-success").html("更新成功");
				} else {
					$("#mtipsthreshold").removeClass("fade");
					$("#mtipsthreshold div").removeClass("alter-success")
							.addClass("alert-danger").html("更新失败");
				}
			},
			error : {

			}
		});
	}
	function adminsubmit(){
		var property = [ "adminid", "name", "phone","position"];
		var data = {};
		for(i=0;i<property.length;i++){
			var t = property[i];
			data[t] = $("#themodaadmin").find("input[name="+t+"]").val();
		}
		data["sex"] = $("#adminsex").html().trim();
		url = BASEURL+"setting/updateadmin";
		$.ajax({
			type : 'post',
			data : JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType : 'json',
			url:url,
			success:function(result){
				if(result == '1'){
					$("#mtipsadmin").removeClass("fade");
					$("#mtipsadmin div").removeClass("alter-danger")
					.addClass("alter-success").html("更新成功");
					
				}
				else{
					$("#mtipsadmin").removeClass("fade");
					$("#mtipsadmin div").removeClass("alter-success")
					.addClass("alter-danger").html("更新成功");
				}
			},
			error:function(){
			   console.log("error");	
			}
		});
		
	}
	function liclick(liid) {
		var livalue = $(this).find("a").html();
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		$(liid.data).html(livalue);
	}
	var deldata = {};
	function dodelete(){
		var baseurl = BASEURL+"setting/";
		urls = ["deljf","delthreshold","deladmin"];
		var senddata = $(deldata.tabel).find("tr:eq("+deldata["row"]+") td:first").html();
		$.ajax({
		data: senddata,
		type:'post',
		dataType:'json',
		contentType:"application/json; charset=utf-8",
		url:baseurl+urls[deldata["url"]],
		success:function(result){
			if(result == '1'){
				$("#btnok").addClass("fade");
				$("#mtipsdelete").removeClass("fade");
				$("#mtipsdelete div").removeClass("alert-danger")
				.addClass("alert-success").html("删除成功");
			}else{
				$("#mtipsdelete").removeClass("fade");
				$("#mtipsdelete div").removeClass("alert-success")
				.addClass("alert-danger").html("删除失败！存在必须的级联关系");
			}
			
		},
		error:function(){
			console.log("error");
		}
	  }); 
	}
	function deletedata(data){
		deldata = data;
		var posx = document.body.clientWidth;
		var posy = document.body.clientHeight;
		var width = $("#showstate").width();
		$("#showstate").css({
			"top" : posy / 2 - width / 2,
			"left" : posx / 2 - 175
		});
		$("#mtipsdelete div").removeClass("alert-success");
		$("#showstate").modal("show");
	}
	function validatorModal1() {
		$('#modFieldContainerForm').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				port : {
					container : '#modIdCtn',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The port is not empty'
						},
						digits : {
							message : "The port must be number"
						},
						between : {
							min : 1024,
							max : 65535,
							message : 'The port is between 1024-65535'
						}
					}
				},
			 jfid:{
				  container : '#modajfid',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The id is not empty'
						},
						digits : {
							message : "The id must be number"
						},
						stringLength: {
							min: 4,
	                        max: 4,
	                        message: 'The value is 4 length'
		                }
					}
				}
			}
		});
	}
	function validatorModal2() {
		$('#thresholdform').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				temp : {
					container : '#modtemp',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				humlity : {
					container : '#modhumilty',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				light : {
					container : '#modlight',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				human : {
					container : '#modhuman',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 0,
							max : 1,
							message : 'The port is between 0-1'
						}
					}
				},
				shake : {
					container : '#modshake',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 0,
							max : 1,
							message : 'The port is between 0-1'
						}
					}
				}
			}
		});
	}
	function validatorModal3(){
		$('#themodaadmin').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				adminid:{
					container : '#modadminid',
					feedbackIcons : true,
					
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits:{
							message : "The value must be number"
						},
						stringLength: {
							min: 7,
	                        max: 7,
	                        message: 'The value is 7 length'
		                }
					}
				},
				phone:{
					container : '#modphone',
					feedbackIcons : true,
					
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits:{
							message : "The value must be number"
						},
						stringLength: {
							min: 11,
	                        max: 11,
	                        message: 'The value is 11 length'
		                },
					}
				}
			}
		});
	}
	function validatorModal4(){
		$('#addthresholdform').bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				temp : {
					container : '#modtemp1',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				humlity : {
					container : '#modhumilty1',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				light : {
					container : '#modlight1',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 1,
							max : 500,
							message : 'The port is between 1-500'
						}
					}
				},
				human : {
					container : '#modhuman1',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 0,
							max : 1,
							message : 'The port is between 0-1'
						}
					}
				},
				shake : {
					container : '#modshake1',
					feedbackIcons : true,
					validators : {
						notEmpty : {
							message : 'The value is not empty'
						},
						digits : {
							message : "The value must be number"
						},
						between : {
							min : 0,
							max : 1,
							message : 'The port is between 0-1'
						}
					}
				}
			}
		});
	}
	/* Add data  */
	
	var urlbase = BASEURL+"setting/";
	var addurls = ["addjf","addthreshold","addadmin"];
	function Add(){
		function getinputdata(modalid,mode){
			var property = [["jfid","port","address"],["temp","humlity","light","shake","human"],["adminid","name","phone","position"]];
			var obj = {};
			for(var i=0;i<property[mode].length;i++){
				var t = property[mode][i];
				obj[t] = $(modalid).find('input[name="'+t+'"]').val();
			}
			return obj;
		} 
		function showtips(param){
			$(param.tipid).removeClass("fade");
			if(param.mode == 0){
				$(param.tipid+" div").removeClass("alter-danger")
				.addClass("alert-success").html(param.info);
			}else{
				$(param.tipid+" div").removeClass("alert-success")
				.addClass("alert-danger").html(param.info);
			}
		}

		this.addjf = function(){
			var obj = getinputdata("#themodal",0);
			obj.adminid = $("#ul1 .active > a").attr("adminid");
			var url = this.urlbasae;
			if(typeof obj.adminid == 'undefined'){
				var info = {};
				info.tipid = "#mtips";
				info.info = "没有选择管理员";
				info.mode = 1;
				showtips(info);
			}
			var url = urlbase + addurls[0];
			$.ajax({
				type:'post',
				url:url,
				dataType:'json',
				data: JSON.stringify(obj),
				contentType:"application/json; charset=utf-8",
				success:function(result){
					if(result == 1){
						showtips({"tipid":"#mtips","info":"添加成功","mode":0});
					}
				},
				error:function(){
					console.log("error");
				}
			});
		}
	    this.addthreshold = function(){
	    	var url = urlbase + addurls[1];
			var obj = getinputdata("#addthreshold",1);
			obj.jfid = $("#uljfaddress .active > a").attr("jfid");
			$.ajax({
				type:'post',
				contentType: "application/json; charset=utf-8",
				data:JSON.stringify(obj),
				dataType:'json',
				url :url,
				success:function(result){
					if(result == '1'){
						showtips({"tipid":"#mtipsthreshold1","info":"添加成功","mode":0});
					}
				},
				error:function(){
					console.log("error");
				}
			});
		}
		this.addadmin = function(){
			var url = urlbase + addurls[2];
			var obj = getinputdata("#themodaadmin",2);
			obj.sex = $("#adminsex").html().trim();
			$.ajax({
				type:'post',
				contentType: "application/json; charset=utf-8",
				data:JSON.stringify(obj),
				dataType:'json',
				url :url,
				success:function(result){
					if(result == 1){
						showtips({"tipid":"#mtipsthreshold","info":"添加成功","mode":0});
					}
					else{
						showtips({"tipid":"#mtipsthreshold","info":"添加失败","mode":1});
					}
				},
				error:function(){
					console.log("error");
				}
			});
		}
	}
	function adddata(data){
		var addobject = new Add();
		var posx = document.body.clientWidth;
		var posy = document.body.clientHeight;
		var width = $(data["modal"]).width();
		$(data["modal"]).css({
			"top" : posy / 2 - width / 2,
			"left" : posx / 2 - 175
		});
		$(data["modal"]).modal("show");
		switch(data["url"]){                    
		case 0:                                 
			$("#jfcfgok").removeAttr("onclick").on("click",addobject.addjf);
			break;
		case 1:
			$("#thredholdaddcfgok").removeAttr("onclick").on("click",addobject.addthreshold);
			break;
		case 2:
			$("#admincfgok").removeAttr("onclick").on("click",addobject.addadmin);
			break;
		}
	}
	
	
	$(function() {
		initmodal();
		/*$("#cancel1").on("click", function() {
			freshpage();
			$("#themodal").modal("hide");
		});
		
		$("#cancel2").on("click", function() {
			freshpage();
			$("#themoda2").modal("hide");
		});*/
		$("div [id^='cancel']").on("click",function(){
			freshpage();
			$(this).parents(".modal").modal("hide");
		});
		
		$("#ul1").on("click", "li", "#adminname",liclick);
		$("#ulsex").on("click", "li", "#adminsex",liclick);
		$("#uljfaddress").on("click", "li", "#jfaddr",liclick);
		validatorModal1();
		validatorModal2();
		validatorModal3();
		validatorModal4();
		$("#jfcfgok").click(function(){
			$('#modFieldContainerForm').bootstrapValidator('validate');
		});
        $("#thresholodcfgok").click(function(){
			$("#thresholdform").bootstrapValidator('validate');
		});
		$("#thredholdaddcfgok").click(function(){
			$('#addthresholdform').bootstrapValidator('validate');
		})
	});