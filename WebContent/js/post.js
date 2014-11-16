$(document).ready(function(){
	$("#gorka").click(function(){
		alert($("#PC_PARTS_TITLE").val());
		$.ajax({
			type: 'POST',
			url: 'rest/v2/inventory',
			data: "{\"PC_PARTS_TITLE\":\"" + $("#PC_PARTS_TITLE").val() + "\",\"PC_PARTS_CODE\":\"" + $("#PC_PARTS_CODE").val() + "\",\"PC_PARTS_MAKER\":\"" + $("#PC_PARTS_MAKER").val() + "\",\"PC_PARTS_AVAIL\":\"" + $("#PC_PARTS_AVAIL").val() + "\",\"PC_PARTS_DESC\":\"" + $("#PC_PARTS_DESC").val() + "\"}",
			dataType: 'json',
			success: function (data) { 
					alert(data);
			}
		});
	});
});
