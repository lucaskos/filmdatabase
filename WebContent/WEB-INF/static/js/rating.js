
$(document).ready(function(){

	for (i = 1; i < 11; i++) {
		var btn = $("<a/>");
		btn.text(i);
		$('.ratingwrapper').append(btn);
	}
	$(document).ready(onLoad);
});