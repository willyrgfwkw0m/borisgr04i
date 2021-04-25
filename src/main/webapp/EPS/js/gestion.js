var manejador = (function () {

    var lDocumentosActuales = new Array();
    var lDocumentos = new Array();
    var lstEstado;
    var EstadoFiltro;
    var lCUPS = new Array();
    var lCIE10 = new Array();
    var DocumentoActual;

    var _addHandlers = function () {
        $("#btnSalir").click(function () {
            _salir();
        });
        $("#btnIrAcuseReciboSolicitud").click(function () {
            if(DocumentoActual != null) window.location.href = "AcuseReciboSolicitud.html?NumDoc=" + DocumentoActual.idDocumento;
        });
        $("#btnIrAutorizarSolicitud").click(function () {
            if(DocumentoActual != null) window.location.href = "Autorizar.html?NumDoc=" + DocumentoActual.idDocumento;
        });  
        $("#btnIrNegarSolicitud").click(function(){
            if(DocumentoActual != null) window.location.href = "Negacion.html?NumDoc=" + DocumentoActual.idDocumento;
        });
    };
    var _createElements = function () {
        _traerDocumentos();
        _traerCIE10();
        _traerCUPS();
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
   
    var _traerDocumentos = function () {
        $.ajax({
            url: '/hapi-fhir-eps/svDocumentos',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lDocumentos = result;
                _traerEstadosDocumentos();
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
    var _traerEstadosDocumentos = function () {
        $.ajax({
            url: '/hapi-fhir-eps/svGetSolicitudesGE',
            dataType: 'json',
            data: null,
            type: 'GET',
            cache: false,
            contentType: 'application/json',
            success: function (result) {
                lDocumentosActuales = result;

                var config = {
                    Id: '#dvdEstado',
                    ClassItem: 'lstestsol',
                    Source: lDocumentosActuales,
                    fn_callback: _verDocumentos,
                    Display: 'Nombre',
                    Value: 'Codigo',
                    Count: 'Cantidad'
                };
                lstEstado = new byaLista();
                lstEstado.init(config);
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

    var tblDocumentos = new Array();
    var _verDocumentos = function(){
        var est = lstEstado.getSeleccionado();
        EstadoFiltro = _parseEst(est);
        var config = {
            Id: '#tblDocumentos',
            Source: lDocumentos,
            fn_Editar: null,
            fn_Seleccionar: verDetallesDocumento,
            lEliminar: false,
            lEditar: false,
            lSeleccionar: true,
            Enabled: false,
            fnFormatItem: function (item, index) {
                if((item.estado != null) && (item.estado == EstadoFiltro) && (item.tipoDocumento == "SOLAUT"))
                {
                    var Consultar = '<span class="glyphicon glyphicon-search clsstblDocumentosSelect" id="' + index + '" aria-hidden="true" style="text-align:center"></span>';
                    var colomnBound = '<td>' + Consultar + '</td><td>' + item.eps.Nombre + "</td><td>" + item.paciente.nombrecompleto + "</td><td>" + _parseUbiPac(item.ubicacionPac) + "</td>";
                    return colomnBound;
                }
            }
        };
        tblDocumentos = new byaTablaG();
        tblDocumentos.init(config);
        tblDocumentos.setEnabled(false);
    };
    var verDetallesDocumento = function (item) {
        if (item.estado == "SOLICITUD") verDetallesDocumentoSolicitud(item);
        if (item.estado == "ARSOLICITUD") verDetallesDocumentoARSolicitud(item);
        if (item.estado == "AUTORIZACION") verDetallesDocumentoAutorizacion(item);
        if (item.estado == "ARAUTORIZACION") verDetallesDocumentoArAutorizacion(item);
        if (item.estado == "NEGACION") verDetallesDocumentoNegacion(item);
        if (item.estado == "ARNEGACION") verDetallesDocumentoArNegacion(item);
    };
    var verDetallesDocumentoSolicitud = function (item) {
        DocumentoActual = item;

        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadSOL').DetailsJSON(objTrazabilidad, DataFields, Titulo);

        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;
        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },                    
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetallesSolicitud').DetailsJSON(item, DataFields, Titulo);

        $("#tblCups").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCups").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });

        $("#tblCIE10").html("");
        $.each(item.lDiagnosticoCIE10, function (index, item) {
            var i = index + 1;
            $("#tblCIE10").append("<tr><th scope='row'>" + i + "</th><td>" + item.Codigo + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalDetalleSolicitud").modal("show");
    };
    var verDetallesDocumentoARSolicitud = function (item) {
        DocumentoActual = item;
        item.FechaRecibidoARSOL = item.acuseRecibo.fechaDocumento;
        
        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        objTrazabilidad.ArSolicitud = item.acuseRecibo.fechaDocumento + "  " + item.acuseRecibo.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' },
                    { Titulo: 'AR Solicitud', Campo: 'ArSolicitud', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadARSOL').DetailsJSON(objTrazabilidad, DataFields, Titulo);

        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;
        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetSoliAR').DetailsJSON(item, DataFields, Titulo);

        $("#tblCupsAR").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCupsAR").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });

        $("#tblCIE10AR").html("");
        $.each(item.lDiagnosticoCIE10, function (index, item) {
            var i = index + 1;
            $("#tblCIE10AR").append("<tr><th scope='row'>" + i + "</th><td>" + item.Codigo + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalSolicitudAr").modal("show");
    };
    var verDetallesDocumentoAutorizacion = function (item) {
        DocumentoActual = item;
        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;

        item.RespuestaSolicitud = item.estado;
        item.FechaRespuestaSolicitud = item.rptaSolicitud.fechaDocumento;

        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        objTrazabilidad.ArSolicitud = item.acuseRecibo.fechaDocumento + "  " + item.acuseRecibo.horaDocumento;
        objTrazabilidad.Autorizacion = item.rptaSolicitud.fechaDocumento + "  " + item.rptaSolicitud.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' },
                    { Titulo: 'AR Solicitud', Campo: 'ArSolicitud', Tipo: 'S' },
                    { Titulo: 'Autorizacion', Campo: 'Autorizacion', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadAUT').DetailsJSON(objTrazabilidad, DataFields, Titulo);


        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetSoliAut').DetailsJSON(item, DataFields, Titulo);

        $("#tblCupsAut").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCupsAut").append("<tr><th scope='row'>" + i + "</th><td>" + _parseNombreCups(item.CodigoCUPS) + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalDetallesAutorizacion").modal("show");
    };
    var verDetallesDocumentoArAutorizacion = function (item) {
        DocumentoActual = item;
        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;

        item.RespuestaSolicitud = item.estado;
        item.FechaRespuestaSolicitud = item.rptaSolicitud.fechaDocumento;
        
        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        objTrazabilidad.ArSolicitud = item.acuseRecibo.fechaDocumento + "  " + item.acuseRecibo.horaDocumento;
        objTrazabilidad.Autorizacion = item.rptaSolicitud.fechaDocumento + "  " + item.rptaSolicitud.horaDocumento;
        objTrazabilidad.ArAutorizacion = item.rptaSolicitud.acuseRecibo.fechaDocumento + "  " + item.rptaSolicitud.acuseRecibo.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' },
                    { Titulo: 'AR Solicitud', Campo: 'ArSolicitud', Tipo: 'S' },
                    { Titulo: 'Autorizacion', Campo: 'Autorizacion', Tipo: 'S' },
                    { Titulo: 'AR Autorizacion', Campo: 'ArAutorizacion', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadARAUT').DetailsJSON(objTrazabilidad, DataFields, Titulo);

        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetSoliArAut').DetailsJSON(item, DataFields, Titulo);

        $("#tblCupsArAut").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCupsArAut").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalDetallesArAutorizacion").modal("show");
    };
    var verDetallesDocumentoNegacion = function (item) {
        DocumentoActual = item;
        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;

        item.RespuestaSolicitud = item.estado;
        item.FechaRespuestaSolicitud = item.rptaSolicitud.fechaDocumento;
        
        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        objTrazabilidad.ArSolicitud = item.acuseRecibo.fechaDocumento + "  " + item.acuseRecibo.horaDocumento;
        objTrazabilidad.Negacion = item.rptaSolicitud.fechaDocumento + "  " + item.rptaSolicitud.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' },
                    { Titulo: 'AR Solicitud', Campo: 'ArSolicitud', Tipo: 'S' },
                    { Titulo: 'Negacion', Campo: 'Negacion', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadNEG').DetailsJSON(objTrazabilidad, DataFields, Titulo);

        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetSoliNeg').DetailsJSON(item, DataFields, Titulo);

        $("#tblCupsNeg").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCupsNeg").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalDetallesNegacion").modal("show");
    };
    var verDetallesDocumentoArNegacion = function (item) {
        DocumentoActual = item;
        item.NombreUbicacion = _parseUbiPac(item.ubicacionPac);
        item.NombreEps = item.eps.Nombre;
        item.NombrePaciente = item.paciente.nombrecompleto;

        item.RespuestaSolicitud = item.estado;
        item.FechaRespuestaSolicitud = item.rptaSolicitud.fechaDocumento;
        
        var objTrazabilidad = {};
        objTrazabilidad.Solicitud = item.fechaDocumento + "  " + item.horaDocumento;
        objTrazabilidad.ArSolicitud = item.acuseRecibo.fechaDocumento + "  " + item.acuseRecibo.horaDocumento;
        objTrazabilidad.Negacion = item.rptaSolicitud.fechaDocumento + "  " + item.rptaSolicitud.horaDocumento;
        objTrazabilidad.ArNegacion = item.rptaSolicitud.acuseRecibo.fechaDocumento + "  " + item.rptaSolicitud.acuseRecibo.horaDocumento;
        var DataFields = [
                    { Titulo: 'Solicitud', Campo: 'Solicitud', Tipo: 'S' },
                    { Titulo: 'AR Solicitud', Campo: 'ArSolicitud', Tipo: 'S' },
                    { Titulo: 'Negacion', Campo: 'Negacion', Tipo: 'S' },
                    { Titulo: 'AR Negacion', Campo: 'ArNegacion', Tipo: 'S' }
        ];
        var Titulo = "Trazabilidad";
        $('#dvdTrazabilidadARNEG').DetailsJSON(objTrazabilidad, DataFields, Titulo);

        var DataFields = [
                    { Titulo: 'Documento', Campo: 'idDocumento', Tipo: 'S' },
                    { Titulo: 'EPS', Campo: 'NombreEps', Tipo: 'S' },
                    { Titulo: 'Estado', Campo: 'estado', Tipo: 'S' },
                    { Titulo: 'Paciente', Campo: 'NombrePaciente', Tipo: 'S' },
                    { Titulo: 'Ubicacion', Campo: 'NombreUbicacion', Tipo: 'S' },
                    { Titulo: 'Fecha', Campo: 'fechaDocumento', Tipo: 'S' }
        ];

        var Titulo = "Detalles Solicitud";
        $('#dvdDetSoliArNeg').DetailsJSON(item, DataFields, Titulo);

        $("#tblCupsArNeg").html("");
        $.each(item.lServiciosCUPS, function (index, item) {
            var i = index + 1;
            $("#tblCupsArNeg").append("<tr><th scope='row'>" + i + "</th><td>" + item.CodigoCUPS + "</td><td>" + item.Cantidad + "</td><td>" + item.Descripcion + "</td></tr>");
        });
        $("#modalDetallesArNegacion").modal("show");
    };

    var _parseEst = function (est) {
        var EstadoFil;
        if (est == "SOL") EstadoFil = "SOLICITUD";
        if (est == "ARS") EstadoFil = "ARSOLICITUD";
        if (est == "AUT") EstadoFil = "AUTORIZACION";
        if (est == "ARA") EstadoFil = "ARAUTORIZACION";
        if (est == "NEG") EstadoFil = "NEGACION";
        if (est == "ARN") EstadoFil = "ARNEGACION";
        return EstadoFil;
    };
    var _parseUbiPac = function (ubi) {
        var EstadoFil;
        if (ubi == "CE") EstadoFil = "Consulta Externa";
        if (ubi == "UR") EstadoFil = "Urgencias";
        if (ubi == "HO") EstadoFil = "Hospitalizacion";
        return EstadoFil;
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
                _createElements();
                _addHandlers();
            }
        }
    };
}());

$(document).ready(function () {
    manejador.init();
});


