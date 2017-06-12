$(document).ready(function() {
	var userid;
	var $user;
	var $role;
	var $username;
	$('.change-role').each(function(e) {
		$(this).change(function() {
			userid = $(this).find('option:selected').data('userid');
			$username = $(this).find('option:selected').data('username');
			$user = $(this).find('option:selected').data('users');
			$role = $(this).find('option:selected').text().trim();
			console.log("USER = " + $user + " role : " + $role);
			console.log(ctx);
			var data = {};
			$.ajax({
				type : 'GET',
				url : ctx+'/changeRole',
				data : {
					username:$username,
					role: $role
				},
				success : function(result) {
					alert("Your bookmark has been saved");
				},
				error : function(responseData) {
		            console.log("  in ajax, error: " + responseData.responseText); 
		        }
			});
		});
	});
});