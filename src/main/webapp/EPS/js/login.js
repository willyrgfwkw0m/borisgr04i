var manejador = (function () {

    var lIPS = new Array();

    var _addHandlers = function () {
        $("#btnLogin").click(function(){
            _login();
        });
    };
    var _createElements = function () {
        _traerIps();
    };
    var _traerIps = function () {
//        lIPS = [
//            { "Codigo": "EPS01", "Nombre": "EPS 01" },
//            { "Codigo": "EPS02", "Nombre": "EPS 02"},
//            { "Codigo": "EPS03", "Nombre": "EPS 03"}
//        ];
//        $("#cboEPS").byaCombo({ DataSource: lIPS, Value: "Codigo", Display: "Nombre" });
    };
    var _login = function (){
        localStorage.setItem("eps_idd", $("#cboEPS").val());
        window.location.href = "Gestion.html";
    };
    var _noUser = function () {
        var user = localStorage.getItem("eps_idd");
        if (user != null) window.location.href = "Gestion.html";
        else return true;
    };
    return {
        init: function () {
            if (_noUser()) {
                _createElements();
                _addHandlers();
            }
        }
    };
}());

$(document).ready(function () {
    manejador.init();
}); 


