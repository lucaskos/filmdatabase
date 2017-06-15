
$(document).ready(function(){
	$('.ratingwrapper').prepend("<ul class='scores'>");
	for (i = 1; i < 11; i++) {
		var btn = $("<a/>");
		btn.text(i);
		$('.ratingwrapper').append("<button type='submit' class='btn-sm' data-index="+i+">"+i+"</button>");
	}
	$('.btn-sm').click(function(){
		var $filmId = $('.filmId').text().trim();
		var $rating = $(this).attr("data-index");
		console.log($rating + ' : ' + $filmId);
		var data = {};
		$.ajax({
            url: '/films/'+$filmId,
            type: 'POST',
            data: {filmId:$filmId, rating:$rating}, // An object with the key 'submit' and value 'true;
            success: function (result) {
              alert("Your bookmark has been saved");
            }, 
            error: function (result) {
            	/*
            	 * 
            	 */
            	location.reload();
            }
        });  
	});
});