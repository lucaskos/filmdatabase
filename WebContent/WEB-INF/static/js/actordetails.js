	function onLoad() {
		$('td[data-href]').on("click", function() {
			document.location='actor/' + $(this).data('href');
		})
	}
	$(document).ready(onLoad);