$(document).ready(
		function() {
			var $filmId = $('.filmid').text().trim();
			$('.ratingwrapper').prepend("<ul class='scores'>");
			for (i = 1; i < 11; i++) {
				var btn = $("<a/>");
				btn.text(i);
				$('.ratingwrapper').append(
						"<button type='submit' class='btn-sm' data-index=" + i
								+ ">" + i + "</button>");
			}
			$('.btn-sm').click(function() {
				var $rating = $(this).attr("data-index");
				console.log($rating + ' : ' + $filmId);
				var data = {};
				$.ajax({
					url : '/films/' + $filmId,
					type : 'POST',
					data : {
						filmId : $filmId,
						rating : $rating
					}, // An object with the key 'submit' and value 'true;
					success : function(result) {
						alert("Your bookmark has been saved");
					},
					error : function(result) {
						/*
						 * 
						 */
						location.reload();
					}
				});
			});
			var $searchBox = $('#w-input-search');
			var $button = $('#button-id');
			var $roleBox = $('#role-in-film');
			var $actorId;
			$button.attr('disabled', 'disabled');
			$searchBox.autocomplete({
				minChars: 3,
				serviceUrl : ctx+'/getActors',
				paramName: "name",
				transformResult: function(response) {
					return {
						suggestions: $.map($.parseJSON(response), function(item) {
							return {value : item.name, data: item.id};
							
						})
					};
				},
				onSelect: function (suggestion) {
					$roleBox.keyup(function(){
						$button.removeAttr('disabled');
						$actorId = suggestion.data;
					});
			    }
			});
			
			$button.click(function(){
				var $role = $roleBox.val().trim();
				$.ajax({
					type: 'GET',
					url: ctx+'/actoraddedtofilm',
					data: {
						filmId:$filmId,
						actorId:$actorId,
						role:$role
					},
					success : function(result) {
						location.reload();
					},
					error : function(responseData) {
						console.log('in ajax, error : ' + responseData.responseText);
					}
				});
			});

		});