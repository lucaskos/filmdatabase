<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function onLoad() {
		$('tr[data-href]').on("click", function() {
			document.location=$(this).data('href');
		})
	}
	$(document).ready(onLoad);
</script>