$(document).ready(function() {
    // wait 1 second before runnings
    setTimeout(function() {
        // cycle background color to green
        $("div[id='${name}']").animate(
            { backgroundColor: '#71d54c' },
            1000,
            'swing',
            function () {
                ${handlers.whenDone}
            });
    }, 1000);
});