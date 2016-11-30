function favoriteRenew() {
		$
				.ajax({
					url : "/initspring/favorite/favoriteCall.htm",
					method : "post",
					dataType : "json",
					success : function(data) {
						$('#favoriteList').empty();
						$(data.favoLinks)
								.each(
										function(index, obj) {
											var link = '<li><a href="/initspring/'+obj.link_addr+'">'
													+ obj.link_name
													+ '</a></li>'
											$('#favoriteList').append(link);
										});
					}
				});
	}
	favoriteRenew();