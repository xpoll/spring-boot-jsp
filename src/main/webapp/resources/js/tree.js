console.info("````````````````````````")
$.get("/spec/auth", function(data) {
	console.info(data)
	if (data.success)
		console.info("win")
	else
		console.info("faild")
	//<a href='{href}' data-type='{key}'>{name}</a>

})