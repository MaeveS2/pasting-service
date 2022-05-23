$('#btn-copy').click(function() {
    let copyText = document.getElementById('paste');
    copyText.select();
    copyText.setSelectionRange(0, 99999);
    navigator.clipboard.writeText(copyText.value);
});

$('#btn-share').click(function() {
    navigator.clipboard.writeText(window.location.href);
});