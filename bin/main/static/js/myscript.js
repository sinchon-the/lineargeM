/**
 * NAME    : 이미지 슬라이드 JQuery
 * AUTHOR  : 조재청
 * CONTACT : nick2615@naver.com
 */
var e_slide;
var e_sizeW;
var e_ea;
$(function(){
	img_sort();
	e_sizeW=$("#event .img-wrap").width();//이벤트 이미지 표현할 공간의 가로크기 762
	e_ea=$("#event .img-wrap>ul>li").length;//이벤트 슬라이드 이미지 개수 5
	$("#event .img-wrap>ul").css("width", e_sizeW*e_ea);//
	
	$("#btn-next").click(next);
	$("#btn-prev").click(prev);
	var size=$("#th-list li").length;
	$("#thumbnail .ea .tot-ea").text(size);
	
	$(window).scroll(stickyEvent);
	if(e_ea>1){
		e_slide=setTimeout(e_start,5000);
	}
	
	$("#event").hover(e_stop, function(){
		if(e_ea>1)
			e_slide=setTimeout(e_start,5000);
	});
	e_bullet();
	$(".e-next").click(e_next);
	$(".e-prev").click(e_prev);
	$("#event .tit-wrap ul li").click(e_move);
});
//////////////////////////////////////////////////
//블릿클릭시 이동
function e_move(){
	//var sizeW=$("#event .img-wrap").width();
	var i=$(this).index();
	var lis=$("#event .img-wrap>ul>li");//li여러개(5개)
	lis.each(function(){
		if($(this).val() == i){//대상이미지를 찾는다
			var idx=$(this).index();//li중 현재 위치한 index
			var pos=e_sizeW*idx;//한개이미지사이즈*index = left가이동할 위치 계산
			$("#event .img-wrap>ul").stop().animate({left : -pos}, 500,function(){
				for(var j=0; j<idx; j++){
					var first=$("#event .img-wrap>ul li:first-child");
				 	var last=$("#event .img-wrap>ul li:last-child");
				 	last.after(first);
				}
				$("#event .img-wrap>ul").css("left",0);
				e_bullet();
			});
 
		}
	});
}
//////////////////////////////////////////////////
function e_bullet(){
	//현재 슬라이드 이미지 위치값을 value로 세팅한거 읽어오기
	var i=$("#event .img-wrap>ul li:first-child").val();
	var target=$("#event .tit-wrap ul li:eq("+i+") .e-bullet");
	var bullets=$("#event .tit-wrap ul li .e-bullet");
	//alert(target);
	bullets.css("background-color","rgba(0,0,0,0.3)");
	target.css("background-color","rgba(0,0,0,0.6)");
}
//////////////////////////////////////////////////
function e_stop(){
	clearTimeout(e_slide);
}
function e_start(){
	e_next();
	e_slide=setTimeout(e_start,5000);
}
function e_prev(){
	var sizeW=$("#event .img-wrap").width();
	var first=$("#event .img-wrap>ul li:first-child");
	var last=$("#event .img-wrap>ul li:last-child");
	//first앞에 last 이동 하고 ul의 left -sizeW
	first.before(last);
	$("#event .img-wrap>ul").css("left", -sizeW);
	
	$("#event .img-wrap>ul").stop().animate({left : 0}, 500,function(){
		e_bullet();
	});
	
}
function e_next(){
	
	var sizeW=$("#event .img-wrap").width();
	$("#event .img-wrap>ul").stop().animate({left : -sizeW}, 500,function(){
		var first=$("#event .img-wrap>ul li:first-child");
		var last=$("#event .img-wrap>ul li:last-child");
		//last뒤에 first 이동 하고 ul의 left 0
		last.after(first);
		$("#event .img-wrap>ul").css("left",0);
		e_bullet();
	});
	
}
//////////////////////////////////////////////////
function stickyEvent(){
		//sticky가 IE에서는 적용 않됨..
		if($(window).scrollTop() == 0){
			$("header").css("position", "sticky");
			$("#header-top").show();
			$("#header-wrap").css("background-color","rgba(0,0,0,0.5)");
			$("#lnb a").css("color","#fff");
		}else{
			$("header").css("position", "fixed");
			//$("header").css("top", 0);
			$("#header-top").hide();
			$("#header-wrap").css("background-color","rgba(255,255,255,0.8)");
			$("#lnb a").css("color","#000");
		}
}

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


