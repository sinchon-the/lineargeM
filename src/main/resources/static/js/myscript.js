/**
 * NAME    : 이미지 슬라이드 JQuery
 * AUTHOR  : 조재청
 * CONTACT : nick2615@naver.com
 */
$(function(){
	img_sort();
	$("#btn-next").click(next);
	$("#btn-prev").click(prev);
	var size=$("#th-list li").length;
	$("#thumbnail .ea .tot-ea").text(size);
	
	
});

function changeImg(n){
	var size=$("#th-list li").length;
	var i=$("#thumbnail .th-target").index();
	
	var target_index = i + n;
	if(target_index<0){ target_index=size-1;}
	else if(target_index==size){ target_index=0;}
	//숫자 바꾸기
	$("#thumbnail .ea .idx").text(target_index+1);
	//이미지 바꾸기
	$("#th-list li").eq(i).removeClass("th-target");
	$("#th-list li").eq(target_index).addClass("th-target");
	
	if(n == 1){
		//현재 보여지는 이미지 지정
		$("#img-list>ul li").eq(i).fadeOut(300);
		$("#img-list>ul li").eq(target_index).fadeIn(300);
		//마지막 이미지가 없어지면
		if(i==size-1){
			//첫번째 이미지가 나오게하고
			$("#img-list>ul li").eq(0).fadeIn(300);
		}
	}else if(n == -1){
		//현재보다 앞에있는 이미지(target_index) 나오게하기
		//첫번째 이미지 빼고 나머지는 숨긴상태로 만들고
		if(i==0){
			for(var j=1 ; j < size ; j++){
				$("#img-list>ul li").eq(j).hide();
			}
			//첫번째 이미지는 fadeOut
			$("#img-list>ul li").eq(i).fadeOut(300,function(){
				$(this).hide();
			});
			
		}
		//이전이미지를 fadeIn
		$("#img-list>ul li").eq(target_index).fadeIn(300,function(){
			$("#img-list>ul li").eq(i).hide();
		});
	}
}

function prev(){
	changeImg(-1);
	/*
	var size=$("#th-list li").length;
	var i=$("#thumbnail .th-target").index();
	var target_index=i-1;
	if(target_index<0){ target_index=size-1;}
	$("#th-list li").eq(i).removeClass("th-target");
	$("#th-list li").eq(target_index).addClass("th-target");
	
	//현재보다 앞에있는 이미지(target_index) 나오게하기
	//첫번째 이미지 빼고 나머지는 숨긴상태로 만들고
	if(i==0){
		for(var j=1 ; j < size ; j++){
			$("#img-list>ul li").eq(j).hide();
		}
		//첫번째 이미지는 fadeOut
		$("#img-list>ul li").eq(i).fadeOut(300,function(){
			$(this).hide();
		});
		
	}
	//이전이미지를 fadeIn
	$("#img-list>ul li").eq(target_index).fadeIn(300,function(){
		$("#img-list>ul li").eq(i).hide();
	});
	//*/
}

function next(){
	changeImg(1);
	/*
	var size=$("#th-list li").length;
	var i=$("#thumbnail .th-target").index();
	var target_index=i+1;
	if(target_index==size){ target_index=0;}
	$("#th-list li").eq(i).removeClass("th-target");
	$("#th-list li").eq(target_index).addClass("th-target");
	
	//visual bg 변경
	//현재 보여지는 이미지 지정
	$("#img-list>ul li").eq(i).fadeOut(300);
	$("#img-list>ul li").eq(target_index).fadeIn(300);
	//마지막 이미지가 없어지면
	if(i==size-1){
		//첫번째 이미지가 나오게하고
		$("#img-list>ul li").eq(0).fadeIn(300);
	}
	//*/
}

function img_sort(){
	var list=$("#img-list ul li");
	//var idx=1;
	for(var i=0; i<list.length; i++){
		$(list[i]).css("z-index", -(i+1));
		if(i!=0){
			$(list[i]).hide();
		}
	}
	
}
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
