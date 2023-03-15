//체크박스 전체선택
function checkAll(checkAll){
	const checkboxes = document.getElementsByName('checkbox');
	checkboxes.forEach(
		(checkbox) => {checkbox.checked = checkAll.checked;}
	)
}

//체크박스 선택삭제
function removeCheckbox(){
	
	var checkbox = $("input[name='checkbox']");
	var imgArray = new Array();
	var array = new Array();
	
	for(var i = 0; i<checkbox.length; i++){
		if(checkbox[i].checked){
			$("input[name='checkbox']:checked").each(function(index, item){
				imgArray.push($(item).attr("imgValue"));
			});
		}
	}
	for(var i = 0; i<checkbox.length; i++){
		if(checkbox[i].checked){
			array.push(checkbox[i].value);
		}
	}
	
	if(array.length == 0){
		alert("체크박스를 선택해주세요.");
	}
	else{
		var confirmation = confirm(array.length+"개의 게시글을 삭제합니다.");
		if(confirmation == true){
			$.ajax({
				url : "removeCheckbox",
				type : "POST",
				traditional : true, //넘기는 데이터값이 배열일 경우 추가하는 옵션
				data : {
						array : array,
						imgArray : imgArray
				},
				success : function(result){
					if(result = 1){
						alert("글이 삭제되었습니다.");
						location.replace("board");
					}
					else{
						alert("삭제 실패");
					}
				}
			});
		}
	}
}