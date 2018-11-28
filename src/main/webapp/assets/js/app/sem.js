$("#btnFormat").click(function(e) {
	e.preventDefault();
	$.ajax({
		type : 'POST',
		data : $("#frmsem").serialize(),
		url : '/api/sem2b24',
		success : function(data){
			$("#result").html(formatJson(data));
			$("#modal_large").modal("show");
		}
	}).done(function(data) {

	}).fail(function(data) {
		alert('fail');
	});
	return false;
});

function formatJson(jsonobj){
	
    var json = JSON.stringify(jsonobj, undefined, 2);
    
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/,/g, ',<br>');
    json = json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'color: darkorange;';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'color: black;';
            } else {
                cls = 'color: green;';
            }
        } else if (/true|false/.test(match)) {
            cls = 'color: blue;';
        } else if (/null/.test(match)) {
            cls = 'color: magenta;';
        }
        return '<span style="' + cls + '">' + match + '</span>';
    });
    return json;
}