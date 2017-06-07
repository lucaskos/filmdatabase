	function onLoad() {
		$('tr[data-href]').on("click", function() {
			document.location=$(this).data('href');
		})
	}
	$(document).ready(onLoad);