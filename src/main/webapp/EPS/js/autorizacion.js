var manejador = (function () {
    var IPS;
    var EPS;
    var PACIENTE;

    var NumeroDocumento;
    var Documento;

    var lCUPS = new Array();
    var lCIE10 = new Array();

    var lIPS;

    var lServiciosCUPS = new Array();
    var lDiagnosticoCIE10 = new Array();

    var _addHandlers = function () {
        $("#btnEnviar").click(function () {
            _EjemploPOST();
        });
        $("#btnBuscarPaciente").click(function () {
            _traerPaciente();
        });
        $("#btnAgregarCpus").click(function () {
            AgregarCups();
        });
        $("#btnAgregarCIE10").click(function () {
            AgregarCIE10();
        });
        $("#btnSalir").click(function () {
            _salir();
        });
    };
    var _createElements = function () {
        var FechaActual = new Date();
        $("#fechaDocumento").val(byaPage.FechaShortX(FechaActual));
        manejador.mostrarhora();

        _traerIps();

        _traerCIE10();
        _traerCUPS();
        _traerDocumento();
    };


    var formToJSON = function () {
        return JSON.stringify({
            "idSol": NumeroDocumento,
            "ips_ide": $("#cboIPSAut").val(),
            "lServiciosCUPS": lServiciosCUPS
        });
    };

    var _EjemploPOST = function () {
        $.ajax({
            url: '/hapi-fhir-eps/EPS_NuevaAutorizacion',
            dataType: 'json',
            data: formToJSON(),
            type: 'POST',
            cache: false,
            contentType: 'application/json',
            success: function (response) {
                alert("Operación Realizada Satisfactoriamente");
                //alert(JSON.stringify(response));
                $("#stockReport").html(response);
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
    var _traerCUPS = function () {
        $.ajax({
            url: '/hapi-fhir-eps/svCUPS',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lCUPS = result;
                $("#cboCUPS").byaCombo({ DataSource: lCUPS, Value: "Codigo", Display: "Nombre" });
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
    var _traerCIE10 = function () {
        $.ajax({
            url: '/hapi-fhir-eps/svCIE10',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lCIE10 = result;
                $("#cboCIE10").byaCombo({ DataSource: lCIE10, Value: "Codigo", Display: "Nombre" });
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
    var DibujarCups = function () {
        $("#tblCups").html("");
        $.each(lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCups").append("<tr><th scope='row'>" + i + "</th><td>" + _parseNombreCups(item.CodigoCUPS) + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
    };
    var DibujarCIE10 = function () {
        $("#tblCIE10").html("");
        $.each(lDiagnosticoCIE10, function (index, item) {
            var i = index + 1;
            $("#tblCIE10").append("<tr><th scope='row'>" + i + "</th><td>" + _parseNombreCie10(item.Codigo) + "</td><td>" + item.Descripcion + "</td></tr>");
        });
    };
    var _traerIps = function () {
        $.ajax({
            url: '/hapi-fhir-eps/svIPSs',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lIPS = result;
                $("#cboIPSAut").byaCombo({ DataSource: lIPS, Value: "Codigo", Display: "Nombre" });
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
    var _traerDocumento = function () {
            $.ajax({
                url: '/hapi-fhir-eps/svDoc?id=' + NumeroDocumento,
                dataType: 'json',
                data: null,
                type: 'GET',
                cache: false,
                contentType: 'application/json',
                success: function (result) {
                    Documento = result;
                    $("#origen_atencion").val(Documento.origenAtencion);
                    $("#servicio_solicitado").val(Documento.servicioactual);
                    $("#prioridad_atencion").val(Documento.prioridadAtencion);
                    $("#ubicacion_pas").val(Documento.ubicacionPac);
                    $("#cama").val(Documento.cama);
                    $("#servicio").val(Documento.servicio);
                    $("#idDocumento").val(Documento.idDocumento);

                    $("#nom_ips").html(Documento.ips.Nombre);
                    $("#cboIPSAut").val(Documento.ips.Codigo);
                    $("#direccion_ips").html(Documento.ips.Direccion);
                    $("#telefono_ips").html(Documento.ips.Telefono);

                    $("#documento_pac").html(Documento.paciente.id);
                    $("#nombre_pac").html(Documento.paciente.nombrecompleto);
                    $("#nacimiento_pac").html(Documento.paciente.fechanacimiento);
                    $("#direccion_pac").html(Documento.paciente.direccion_pas);
                    $("#telefono_pac").html(Documento.paciente.telefono_pas);
                    $("#cobertura_pac").html(Documento.paciente.nombrecobertura);

                    $("#nombre_eps").val(Documento.eps.Codigo);
                    $("#codigo_eps").val(Documento.eps.Nombre);

                    lServiciosCUPS = Documento.lServiciosCUPS;
                    DibujarCups();
                    lDiagnosticoCIE10 = Documento.lDiagnosticoCIE10;
                    DibujarCIE10();
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

    var _parseNombreCups = function (cod) {
        var res;
        $.each(lCUPS, function (index, item) {
            if (item.Codigo == cod) res = item.Nombre;
        });
        return res;
    };
    var _parseNombreCie10 = function (cod) {
        var res;
        $.each(lCIE10, function (index, item) {
            if (item.Codigo == cod) res = item.Nombre;
        });
        return res;
    };
    var _siUser = function () {
        var user = localStorage.getItem("eps_idd");
        if (user == null) window.location.href = "login.html";
        else return true;
    };
    var _salir = function () {
        localStorage.removeItem("eps_idd");
        window.location.href = "login.html";
    };
    return {
        init: function () {
            if (_siUser()) {
                NumeroDocumento = $.getUrlVar('NumDoc');
                _createElements();
                _addHandlers();
            }
        },
        mostrarhora: function () {
            var f = new Date();
            var hora = f.getHours().toString();
            if (hora.length == 1) hora = "0" + hora;

            var minutos = f.getMinutes().toString();
            if (minutos.length == 1) minutos = "0" + minutos;


            var cad = hora + ":" + minutos + ":00";
            $("#horaDocumento").val(cad);
            setTimeout("manejador.mostrarhora()", 60000);
        }
    };
}());

$(document).ready(function () {
    manejador.init();
});

