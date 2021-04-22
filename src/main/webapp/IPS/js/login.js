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
        $.ajax({
            url: '/hapi-fhir-ips/svIPSs',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lIPS = result;
                $("#cboIPS").byaCombo({ DataSource: lIPS, Value: "Codigo", Display: "Nombre" });
            },
            error: function (request, textStatus, errorThrown) {
                alert("error:" + textStatus + errorThrown);
            },
            complete: function (request, textStatus) {
                //alert("complete" + request.responseText);
                //alert("complete" + textStatus);
            }
        });        
    };
    var _login = function (){
        localStorage.setItem("ips_id", $("#cboIPS").val());
        localStorage.setItem("eps_id", "EPS01");
        window.location.href = "Gestion.html";
    };
    var _noUser = function () {
        var user = localStorage.getItem("ips_id");
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


