$(document).ready(function(){
	$('.btn-group-state button').click(function () {
		$('.btn-group-state button').each(function () {
                $(this).removeClass('btn-success');
        });
		$(this).addClass('btn-success');
		return false;
    });
	
	$('.btn-group-type button').click(function () {
		$('.btn-group-type button').each(function () {
                $(this).removeClass('btn-success');
        });
		$(this).addClass('btn-success');
		return false;		
    });
	
	
	$('.btn-group-state button').first().trigger("click");
	$('.btn-group-type button').first().trigger("click");
});