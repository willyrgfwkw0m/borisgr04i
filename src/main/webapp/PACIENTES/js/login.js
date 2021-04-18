var Elecc = (function () {  

    var _addHandlers = function () {
        $("#btnLogin").click(function () {
            _login();
        });
    };

    var _createElements = function () {
        
    };

    var _login = function () {
        localStorage.setItem("appPacId", $("#txtUsuario").val());
        window.location.href = "index.html";
    };

    var VerificarSesion = function () {
        var ses = localStorage.getItem("appPacId");
        if (ses != null) {
            window.location.href = "index.html";
        }
    };

    return {
        init: function () {
            VerificarSesion();
            _createElements();
            _addHandlers();
            document.addEventListener("backbutton", EventoAtras, true);
        }
    };
}());

$(document).ready(function () {
    Elecc.init();
});