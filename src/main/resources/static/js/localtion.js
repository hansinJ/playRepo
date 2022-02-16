
var localhostPath = function () {
    var curPath = window.document.location.href;
    var pathname = window.document.location.pathname;
    var pos = curPath.indexOf(pathname);
    var localhostPath = curPath.substring( 0, pos );
    return localhostPath;
}