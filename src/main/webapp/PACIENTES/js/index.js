var manejador = (function () {
    var lDocumentos;
    var stylesOn = {
        "-webkit-box-shadow": "0 10px 6px -6px #777",
        "-moz-box-shadow": "0 10px 6px -6px #777",
        "box-shadow": "0 10px 6px -6px #777"
    }
    var styleOff = {
        "-webkit-box-shadow": "",
        "-moz-box-shadow": "",
        "box-shadow": ""
    }   
    var _addHandlers = function () {
        $("#btnSalir").click(function () {
            localStorage.removeItem("appPacId");
            window.location.href = "login.html";
        });
    };
    var _createElements = function () {
        localStorage.setItem("appPacDirSer", "/");
        _traerSolicitudesPaciente();
    };
    var _abriModalDetalles = function()    {
        $("#pop").popup({ transition: "slide" });
        $("#pop").popup({ positionTo: "window" });
        var option = $("#pop").popup("option");
        $("#pop").popup("open", option);
    };
    var _traerSolicitudesPaciente = function () {        
        $.ajax({
            url: manejador.getVarLocal("appPacDirSer") + 'hapi-fhir-eps/svDocumentosPac?id=' + manejador.getVarLocal("appPacId"),
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lDocumentos = result;
                $("#lSolicitudes").html("");
                $.each(lDocumentos, function(index,item){   
                    // For para la creación de la tabla de Cups
                    var strTblCups = "";
                    $.each(item.lServiciosCUPS, function (index2, item2) {
                        strTblCups = strTblCups + "<tr>"+
                                                    "<td>"+
                                                        "<p>" + item2.Descripcion + "</p>" +
                                                    "</td>"+
                                                    "<td style='text-align:right'>"+
                                                        "<p>" + item2.Cantidad + "</p>"+
                                                    "</td>                          "+                  
                                                "</tr>";
                                    });     
                    // Datos basicos de la solicitud
                    $("#lSolicitudes").append("" +
                        "<div style='background-color:#f3f3f3; font-size: 0.7rem;'>"+
                                "<div style='padding:8px 8px 8px 8px'>     "+
                                    "<table style='font-size:95%; width:100%'>"+
                                        "<tr>"+
                                            "<td colspan='2'>"+
                                                "<p><strong>IPS: </strong>" + item.ips.Nombre + "</p>"+
                                            "</td>"+
                                        "</tr>"+
                                        "<tr>"+
                                            "<td colspan='2'>"+
                                                "<p><strong>EPS: </strong>" + item.eps.Nombre + "</p>"+
                                            "</td>"+
                                        "</tr>                                        "+
                                        "<tr>"+
                                            "<td>"+ 
                                                "<p><strong>Fecha: </strong>"+ item.fechaDocumento + "  " + item.horaDocumento + "</p>"+
                                            "</td>"+
                                            "<td>"+
                                                "<p><strong>Estado: </strong>" + item.estado + "</p>"+
                                            "</td>"+
                                        "</tr>"+
                                    "</table>  "+
                                    "<h4 style='text-align:center ;-webkit-box-shadow: 0 10px 6px -6px #1a1a1a;-moz-box-shadow: 0 10px 6px -6px #1a1a1a;box-shadow: 0 10px 6px -6px #1a1a1a;'>Cups</h4>       "+                     
                                    "<div style='background-color:#f3f3f3; font-size: 0.7rem; margin:5px'>"+
                                        "<div style='padding:4px'>"+
                                            "<table style='font-size:95%; width:100%'>"+
                                                "<tr>"+
                                                    "<td>"+
                                                        "<p><strong>Cups</strong></p>"+
                                                    "</td>"+
                                                    "<td style='text-align:right'>"+
                                                        "<p><strong>Cant. </strong></p>"+
                                                    "</td>"+
                                                "</tr>"+
                                                strTblCups +
                                            "</table>   "+
                                        "</div>"+
                                    "</div>  "+
                                "</div>"+
                                "<div data-role='navbar'>"+
                                    "<ul>"+
                                        "<li><a data-theme='a' class='ui-btn ui-btn-icon-right ui-icon-info' id='" + index + "' onclick='manejador.verDetallesSolicitud(id)'>Detalles</a></li>"+
                                    "</ul>"+
                                "</div>"+
                            "</div>");
                });
                
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
    


    var _parseUbiPac = function (obj) {
        var EstadoFil;
        if (obj == "CE") EstadoFil = "Consulta Externa";
        if (obj == "UR") EstadoFil = "Urgencias";
        if (obj == "HO") EstadoFil = "Hospitalizacion";
        return EstadoFil;
    };
    var _parseOrigen = function (obj) {
        var EstadoFil;
        if (obj == "ENFGRAL") EstadoFil = "Enfermedad General";
        if (obj == "ENFPROF") EstadoFil = "Enfermedad Profesional";
        if (obj == "ACCLAB") EstadoFil = "Accidente de Trabajo";
        if (obj == "ACCTRA") EstadoFil = "Accidente de Transito";
        if (obj == "EVENCAT") EstadoFil = "Evento Catastrofico";
        return EstadoFil;
    };
    var _parseServicio = function (obj) {
        var EstadoFil;
        if (obj == "POSTURG") EstadoFil = "Posterior a la Urgencia";
        if (obj == "SERVELECT") EstadoFil = "Servicios Electivos";
        return EstadoFil;
    };
    var _parsePrioridad = function (obj) {
        var EstadoFil;
        if (obj == "PRI") EstadoFil = "Prioritaria";
        if (obj == "NPRI") EstadoFil = "No Prioritaria";
        return EstadoFil;
    };
    var _armarObjetoTrazabilidad = function (index) {
        var lTrazabilidad = new Array();
        var est = lDocumentos[index].estado;
        if (est == "SOLICITUD") {
            var e = {};
            e.Documento = "SOLICITUD";
            e.Fecha = lDocumentos[index].fechaDocumento + "  " + lDocumentos[index].horaDocumento;
            e.Responsable = lDocumentos[index].ips.Nombre;
            lTrazabilidad.push(e);
        }
        if (est == "ARSOLICITUD") {
            var e = {};
            e.Documento = "SOLICITUD";
            e.Fecha = lDocumentos[index].fechaDocumento + "  " + lDocumentos[index].horaDocumento;
            e.Responsable = lDocumentos[index].ips.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            e.Documento = "ARSOLICITUD";
            e.Fecha = lDocumentos[index].acuseRecibo.fechaDocumento + "  " + lDocumentos[index].acuseRecibo.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);
        }
        if ((est == "AUTORIZACION") || (est == "NEGACION")) {
            var e = {};
            e.Documento = "SOLICITUD";
            e.Fecha = lDocumentos[index].fechaDocumento + "  " + lDocumentos[index].horaDocumento;
            e.Responsable = lDocumentos[index].ips.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            e.Documento = "ARSOLICITUD";
            e.Fecha = lDocumentos[index].acuseRecibo.fechaDocumento + "  " + lDocumentos[index].acuseRecibo.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            if (est == "AUTORIZACION") e.Documento = "AUTORIZACION"; else e.Documento = "NEGACION";
            e.Fecha = lDocumentos[index].rptaSolicitud.fechaDocumento + "  " + lDocumentos[index].rptaSolicitud.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);
        }
        if ((est == "ARAUTORIZACION") || (est == "ARNEGACION")) {
            var e = {};
            e.Documento = "SOLICITUD";
            e.Fecha = lDocumentos[index].fechaDocumento + "  " + lDocumentos[index].horaDocumento;
            e.Responsable = lDocumentos[index].ips.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            e.Documento = "ARSOLICITUD";
            e.Fecha = lDocumentos[index].acuseRecibo.fechaDocumento + "  " + lDocumentos[index].acuseRecibo.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            if (est == "ARAUTORIZACION") e.Documento = "AUTORIZACION"; else e.Documento = "NEGACION";
            e.Fecha = lDocumentos[index].rptaSolicitud.fechaDocumento + "  " + lDocumentos[index].rptaSolicitud.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);

            var e = {};
            if (est == "ARAUTORIZACION") e.Documento = "ARAUTORIZACION"; else e.Documento = "ARNEGACION";
            e.Fecha = lDocumentos[index].rptaSolicitud.acuseRecibo.fechaDocumento + "  " + lDocumentos[index].rptaSolicitud.acuseRecibo.horaDocumento;
            e.Responsable = lDocumentos[index].eps.Nombre;
            lTrazabilidad.push(e);
        }
        return lTrazabilidad;
    };
    var VerificarSesion = function () {
        var ses = localStorage.getItem("appPacId");
        if (ses == null) {
            window.location.href = "login.html";
        }
    };
    return {
        init: function () {
            VerificarSesion();
            _createElements();
            _addHandlers();                     
        },
        getVarLocal: function (value) {
            return localStorage.getItem(value);
        },
        verDetallesSolicitud: function (index) {
            $("#dvdDetallesDatosBasicosSolicitud").html('<p style="font-size:90%"><strong>Origen: </strong>' + _parseOrigen(lDocumentos[index].origenAtencion) + '</p>' +
                        '<p style="font-size:90%"><strong>Servicio: </strong>' + _parseServicio(lDocumentos[index].servicioactual) + '</p>' +
                        '<p style="font-size:90%"><strong>Prioridad: </strong>' + _parsePrioridad(lDocumentos[index].prioridadAtencion) + '</p>' +
                        '<p style="font-size:90%"><strong>Ubicacion: </strong>' + _parseUbiPac(lDocumentos[index].ubicacionPac) + '</p>');

            $("#tblTrazabilidadSolicitud").html('<tr>'+
                                                    '<td style="text-align:left">'+
                                                        '<p><strong>Documento</strong></p>'+
                                                    '</td>'+
                                                    '<td style="text-align:center">'+
                                                        '<p><strong>Fecha </strong></p>'+
                                                    '</td>'+
                                                    '<td style="text-align:right">'+
                                                        '<p><strong>Responsable </strong></p>'+
                                                    '</td>'+
                                                '</tr>');


            var lTrazabilidad = _armarObjetoTrazabilidad(index);   
            $.each(lTrazabilidad, function (index, item) {
                $("#tblTrazabilidadSolicitud").append('<tr><td style="text-align:left"><p>' + item.Documento + '</p></td><td style="text-align:center"><p>' + item.Fecha + '</p></td><td style="text-align:right"><p>' + item.Responsable + '</p></td></tr>');
            });

            _abriModalDetalles();
        }
    };
}());

$(document).ready(function () {
    manejador.init();
});