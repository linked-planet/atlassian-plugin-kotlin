// /download/resources/${groupId}.${artifactId}:frontend/js/frontend.min.js
function initFrontendApp(starterFunc) {
    var devMode = window.location.port === '2990' || window.location.port === '1990';
    console.log('loading frontend app [devMode: ' + devMode + ' ...]');
    var scriptUrl = (devMode)
        ? 'http://localhost:8080/frontend/frontend.js'
        : window.location.origin + '/download/resources/${groupId}.${artifactId}/js/frontend-min.js';
    var script = document.getElementById('load-frontend-script');
    if (script == null) {
        script = document.createElement('script');
        script.id = 'load-frontend-script';
        script.src = scriptUrl;
        document.head.appendChild(script);
        script.addEventListener('load', function () {
            console.log('loading frontend app [devMode: ' + devMode + '] DONE');
            starterFunc();
        });
    } else {
        console.log('loading frontend app [devMode: ' + devMode + '] DONE');
        starterFunc();
    }
}
