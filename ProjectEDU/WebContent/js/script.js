jQuery(document).ready(function() {
	jQuery(".list0-item").hide();
        $("#tatthongbao").click(function(){
            var left = $('#login').offset().left;
            $("#login").addClass("abs");
            $("#login").removeClass("fade");
            $("#login").removeClass("in");
            
            $("#login").css({left:left}).animate({"left":"500px"}, "fast");
            $(".modal-backdrop .in").css("opacity:0; ");
        });
	jQuery(window).scroll(function() {
		
		scrollPos = jQuery(window).scrollTop();
		
		if (scrollPos >= jQuery("#slide").height()+1) {
			jQuery("nav").addClass("fixed");
			jQuery(".content").addClass("content-stick");
			
		} else {
			jQuery("nav").removeClass("fixed");
			jQuery(".content").removeClass("content-stick");
		}
		if (scrollPos >=1) {
			jQuery(".list0-item").show(500);
		} else {
			jQuery(".list0-item").hide(500);
		}
	});
	jQuery(window).resize(function(){
		if(jQuery("body").width()>=800){
			jQuery(".bt-chitiet").removeClass("button-chitiet");
			jQuery(".title").show(1000);
		}
		else{
			jQuery(".bt-chitiet").addClass("button-chitiet");
			jQuery(".title").hide(1000);			
		}
		
	});
	var i1=1,i2=1,i3=1,i4=1,i5=1;
	$(".list1").click(function(){
        $(".list1-item").toggle(500);
        i1++;
        if(i1%2==0){
        	$(".list1-turn").html("Mở");
        }
        
    	else{
    		$(".list1-turn").html("Đóng");
    	}
    });
    $(".list2").click(function(){
        $(".list2-item").toggle(500);
        i2++;
        if(i2%2==0){
        	$(".list2-turn").html("Mở");
        }
        
    	else{
    		$(".list2-turn").html("Đóng");
    	}
    });
    $(".list3").click(function(){
        $(".list3-item").toggle(500);
        i3++;
        if(i3%2==0){
        	$(".list3-turn").html("Mở");
        }
        
    	else{
    		$(".list3-turn").html("Đóng");
    	}
    });
    $(".list4").click(function(){
        $(".list4-item").toggle(500);
        i4++;
        if(i4%2==0){
        	$(".list4-turn").html("Mở");
        }
        
    	else{
    		$(".list4-turn").html("Đóng");
    	}
    });
    $(".list5").click(function(){
        $(".list5-item").toggle(500);
        i5++;
        if(i5%2==0){
        	$(".list5-turn").html("Mở");
        }
        
    	else{
    		$(".list5-turn").html("Đóng");
    	}
    });
});