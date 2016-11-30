$("span").click(function(){
	var $node = $(this).parent().find("div");
	if ($node.length > 0)
		if ($node.css("display")[0] == "n")
			$node.css("display", "block");
		else if ($node.css("display")[0] == "b")
			$node.css("display", "none");
})