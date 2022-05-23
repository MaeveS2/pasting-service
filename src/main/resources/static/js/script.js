$('#btn-create').click(function() {
    fetch('http://localhost:8080/paste', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: document.getElementById('paste-name').value,
            text: document.getElementById('paste-text').value,
            language: document.getElementById('paste-lang').value
        })
    }).then(response => {
        return response.json();
    }).then(json => {
        window.location.href = `/paste/${json.id}`;
    });
});