<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <script type="text/javascript">
                var empresaBalanceProcesar = '<s:property  value="resultado"/>';
            </script>
            <script src="<s:url value='/js/indicador/aplicarIndicador.js' />"></script>
            <script src="<s:url value='/js/indicador/indicador.js' />"></script>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="<s:url value='/css/indicadores/indicadorAplicar.css' />"></link>
        </head>

        <body onload="inicializarPaginaIndicadorAplicar();">
            <div class="marcoIndicador">
                <div class="form-group">
                    <div class="row">
                        <label class="col-md-4 control-label" for="cmbIndicadores">Indicadores</label>
                        <div class="col-md-5">
                            <select id="cmbIndicadores" name="cmbIndicadores" class="form-control">
                            </select>
                        </div>
                    </div>
                </div>
                <!-- Button -->
                <div class="form-group">
                    <div class="row">
                        <label class="col-md-4 control-label" for="cmdAplicarIndicador"></label>
                        <div class="col-md-5">
                            <button id="cmdAplicarIndicador" onclick="aplicarIndicador();" name="cmdAplicarIndicador" class="btn btn-warning">Aplicar Indicador</button>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="marcoTablaIndicador">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-4">
                            <label>Resultado del indicador</label>
                        </div>
                        <div class="col-md-5">
                            <input type="text" readonly="readonly" id="txtResultadoIndicador" class="form-control input-md" />
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <label id="tituloCuentasUtilizadas">Cuentas utilizadas en el cálculo</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-12">
                                <table id="tablaCuentas" class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Nombre de Cuenta</th>
                                            <th>Valor</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <button class="btn btn-info" onclick="volverAlMenu()">Volver al menu</button>
        </body>

        </html>