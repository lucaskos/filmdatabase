
$(document).ready(function(){
	for (i = 1; i < 11; i++) {
		var btn = $("<a/>");
		btn.text(i);
		$('.ratingwrapper').append("<button type='submit' class='rating' data-index="+i+">"+i+"</button>");
	}
	$('.rating').click(function(){
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
            }
        });  
	});
});