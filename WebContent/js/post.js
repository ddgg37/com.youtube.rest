$(document).ready(function(){
//	$("#gorka").click(function(){
//		$.ajax({
//			type: 'POST',
//			url: 'rest/v2/inventory',
//			dataType: 'json',
//			data: "{\"PC_PARTS_TITLE\":\"" + $("#PC_PARTS_TITLE").val() + "\",\"PC_PARTS_CODE\":\"" + $("#PC_PARTS_CODE").val() + "\",\"PC_PARTS_MAKER\":\"" + $("#PC_PARTS_MAKER").val() + "\",\"PC_PARTS_AVAIL\":\"" + $("#PC_PARTS_AVAIL").val() + "\",\"PC_PARTS_DESC\":\"" + $("#PC_PARTS_DESC").val() + "\"}",
//			success: function (data) { 
//					alert(data);
//			}
//		});
//	});
	
	$.fn.serializeObject = function()
	{
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};
	
	$("#submit").click(function(e){		
		//e.preventDefault();
		var jsObj=$('form.postform').serializeObject();
		alert(jsObj);
		$.ajax({
			type: 'POST',
			url: 'rest/v2/inventory',
			data: JSON.stringify(jsObj).toString(),
			contentType:'application/json',
			error: function(jqXHR, textStatus, errorThrown){
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function (data) { 
				if (data[0].HTTP_CODE==200){
					$('#div_ajaxResponse').text(data[0].MSG);
				}	
			},
			complete: function(XMLHttpRequest){
				
			},
			dataType:'json'
			
		});
		//$.ajax(ajaxObj);
	});
	
	$("#submit1").click(function(){		
		//e.preventDefault();
		var jsObj=$('form.postform').serializeObject();
		alert("3" + jsObj);
		$.ajax({
			type: 'POST',
			url: 'rest/v3/inventory',
			data: JSON.stringify(jsObj).toString(),
			contentType:'application/json',
			error: function(jqXHR, textStatus, errorThrown){
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function (data) { 
				if (data[0].HTTP_CODE==200){
					$('#div_ajaxResponse').text(data[0].MSG);
				}	
			},
			complete: function(XMLHttpRequest){
				
			},
			dataType:'json'
		});
		//$.ajax(ajaxObj);
	});
});
