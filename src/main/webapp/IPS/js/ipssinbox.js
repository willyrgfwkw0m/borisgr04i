var manejador = (function () {
    var IPS;
    var EPS;
    var PACIENTE;
    
    var lCUPS = new Array();
    var lCIE10 = new Array();

    var lServiciosCUPS = new Array();
    var lDiagnosticoCIE10 = new Array();

    var _addHandlers = function () {
        $("#btnEnviar").click(function(){
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
        $("#cboCUPS").change(function () {
            var value = $(this).val();
            $.each(lCUPS, function (index, item) {
                if (item.Codigo == value) $("#txtDescripcion").val(item.Nombre);
            });
        });
        $("#cboCIE10").change(function () {
            var value = $(this).val();
            $.each(lCIE10, function (index, item) {
                if (item.Codigo == value) $("#txtDiagnostico").val(item.Nombre);
            });
        });
        $("#btnSalir").click(function () {
            _salir();
        });
    };
    var _createElements = function () {
        var FechaActual = new Date();
        $("#fechaDocumento").val(byaPage.FechaShortX(FechaActual));
        manejador.mostrarhora();

        DibujarCups();
        DibujarCIE10();
        _traerIPS();
        _traerEPS();
        _traerCIE10();
        _traerCUPS();
    };
    

    var formToJSON = function () {
                        return JSON.stringify({
                            "id": $("#idDocumento").val(),
                            "ips_ide": IPS.Codigo,
                            "eps_ide": EPS.Codigo,
                            "pac_ide": PACIENTE.id,
                            "origenAtencion": $("#origen_atencion").val(),
                            "servicioactual": $("#servicio_solicitado").val(),
                            "prioridadAtencion": $("#prioridad_atencion").val(),
                            "ubicacionPac": $("#ubicacion_pas").val(),
                            "servicio": $("#servicio").val(),
                            "cama":$("#cama").val(),
                            "lServiciosCUPS": lServiciosCUPS,
                            "lDiagnosticoCIE10": lDiagnosticoCIE10
                        });
                };
    var _EjemploPOST = function () {
        $.ajax({
            url: '/hapi-fhir-ips/IPS_NuevaSolicitud',
            dataType: 'json',
            data:formToJSON(),
            type: 'POST',
            cache: false,
            contentType: 'application/json',
            success: function (response)
            {
                alert("Operación Realizada Satisfactoriamente");
                //alert(JSON.stringify(response));
                $("#stockReport").html(response);
            },
            error: function (request, textStatus, errorThrown)
            {
                alert("error:" + textStatus + errorThrown);
            },
            complete: function (request, textStatus)
            {
                //alert("complete" + request.responseText);
                //alert("complete" + textStatus);
            }
        });
    };
    var _traerIPS = function () {
        $.ajax({
            url: '/hapi-fhir-ips/svIPS?codigo=' + localStorage.getItem("ips_id"),
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                IPS = result;
                $("#codigo_ips").html(IPS.Codigo);
                $("#nom_ips").html(IPS.Nombre);
                $("#direccion_ips").html(IPS.Direccion);
                $("#telefono_ips").html(IPS.Telefono);
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
    var _traerEPS = function () {
        $.ajax({
            url: '/hapi-fhir-ips/svEPS?codigo=' + localStorage.getItem("eps_id"),
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                EPS = result;
                $("#codigo_eps").html(EPS.Codigo);
                $("#nombre_eps").html(EPS.Nombre);
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
            url: '/hapi-fhir-ips/svCUPS',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lCUPS = result;
                $("#cboCUPS").byaCombo({ DataSource: lCUPS, Value: "Codigo", Display: "Codigo" });
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
            url: '/hapi-fhir-ips/svCIE10',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lCIE10 = result;
                $("#cboCIE10").byaCombo({ DataSource: lCIE10, Value: "Codigo", Display: "Codigo" });
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
    var _traerPaciente = function () {
        var id_pa = $("#documento_pac").val();
        $.ajax({
            url: '/hapi-fhir-ips/svPacientes?id=' + id_pa,
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                PACIENTE = result;
                $("#nombre_pac").html(PACIENTE.nombrecompleto);
                $("#nacimiento_pac").html(PACIENTE.fechanacimiento);
                $("#telefono_pac").html(PACIENTE.telefono_pas);
                $("#direccion_pac").html(PACIENTE.direccion_pas);
                $("#correo_pac").html(PACIENTE.correo_pac);
                $("#cobertura_pac").html(PACIENTE.nombrecobertura);
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

    var AgregarCups = function () {
        var e = {};
        e.CodigoCUPS = $("#cboCUPS").val();
        $.each(lCUPS, function (index, item) {
            if (item.Codigo == e.CodigoCUPS) e.Nom_CodigoCUPS = item.Nombre;
        });
        e.Cantidad = $("#txtCantidad").val();
        e.Descripcion = $("#txtDescripcion").val();
        lServiciosCUPS.push(e);
        DibujarCups();

        $("#cboCUPS").val("");
        $("#txtCantidad").val("");
        $("#txtDescripcion").val("");
    };
    var DibujarCups = function () {
        $("#tblCups").html("");
        $.each(lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCups").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
    };

    var AgregarCIE10 = function () {
        var e = {};
        e.Codigo = $("#cboCIE10").val();
        e.Descripcion = $("#txtDiagnostico").val();
        $.each(lCIE10, function (index, item) {
            if (item.Codigo == e.Codigo) e.Nom_Codigo = item.Nombre;
        });
        lDiagnosticoCIE10.push(e);
        DibujarCIE10();

        $("#cboCIE10").val("");
        $("#txtDiagnostico").val("");
    };
    var DibujarCIE10 = function () {
        $("#tblCIE10").html("");
        $.each(lDiagnosticoCIE10, function (index, item) {
            var i = index + 1;
            $("#tblCIE10").append("<tr><th scope='row'>" + i + "</th><td>" + item.Codigo + "</td><td>" + item.Descripcion + "</td></tr>");
        });
    };

    var _siUser = function () {
        var user = localStorage.getItem("ips_id");
        if (user == null) window.location.href = "login.html";
        else return true;
    }; 
    var _salir = function () {
        localStorage.removeItem("ips_id");
        localStorage.removeItem("eps_id");
        window.location.href = "login.html";
    };
    return {
        init: function () {
            if (_siUser()) {
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