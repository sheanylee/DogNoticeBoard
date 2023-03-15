		function papago(){
			var inputKorean = $("#inputKorean").val();
           	$("#papago1").attr("style", "display:'';");
           	$("#papago2").attr("style", "display:'';");
           	$("#papago3").attr("style", "display:'';");
           	$("#papago4").attr("style", "display:'';");		
			$.ajax({
				url:"chinese",
				type:"POST",
				dataType:"json",
				data:{korean:inputKorean},
				success:function(result){
		               	let chinese = result.message.result.translatedText;
		              	console.log(chinese);
		               	$("#chinese").text(chinese);
				},error:function(e){
						console.log(e);
				}
			});
		
			$.ajax({
				url:"english",
				type:"POST",
				dataType:"json",
				data:{korean:inputKorean},
				success:function(result){
		                let english = result.message.result.translatedText;
		                console.log(english);
		                $("#english").text(english);
				},error:function(e){
						console.log(e);
				}
			});
		}