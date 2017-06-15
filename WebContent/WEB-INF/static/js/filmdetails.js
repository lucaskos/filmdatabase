$(document).ready(function() {
	$('td[data-href]').on("click", function() {
		document.location = 'film/' + $(this).data('href');
	})

});
