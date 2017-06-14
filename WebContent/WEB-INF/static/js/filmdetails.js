	function onLoad() {
		$('td[data-href]').on("click", function() {
			document.location='film/' + $(this).data('href');
		})
	}
	$(document).ready(onLoad);