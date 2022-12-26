<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="<s:url value='/css/indicadores/indicadorCrear.css' />"></link>
            <title>Crear indicador</title>
        </head>

        <body>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-1">
                        <div class="marco" id="marcoBotones">
                            <button id="+" class="btn btn-info" onclick="agregarCaracter('+')">+</button>
                            <button id="-" class="btn btn-info" onclick="agregarCaracter('-')">-</button>
                            <button id="*" class="btn btn-info" onclick="agregarCaracter('*')">*</button>
                            <button id="/" class="btn btn-info" onclick="agregarCaracter('/')">/</button>
                            <button id="(" class="btn btn-info" onclick="agregarCaracter('(')">(</button>
                            <button id=")" class="btn btn-info" onclick="agregarCaracter(')')">)</button>
                        </div>
                    </div>

                    <div class="col-md-11">
                        <div class="marco" id="marcoFormula">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Nombre del indicador</label>
                                </div>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="txtNombreIndicador" style="max-width:70%" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label id="labelFormula">Fórmula</label>
                                </div>
                                <div class="col-md-8">
                                    <div class="filaBotones">
                                        <button class="btn btn-warning" id="borrarUltimoCaracterFormula" onclick="deshacer()">Deshacer</button>
                                        <button class="btn btn-danger" id="borrarFormula" onclick="borrarFormula()">Borrar</button>
                                        <button class="btn btn-success" id="guardarIndicador" onclick="guardarIndicador();">Guardar indicador</button>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <textarea id="txtFormula" class="form-control scrollableTextArea" disabled></textarea>
                        </div>

                        <div class="row">
                            <div class="col-md-7">
                                <div class="marco" id="marcoCuenta">
                                    <div class="row" style="margin-top:1%;">
                                        <div class="col-md-2">
                                            <label>Cuenta</label>
                                        </div>
                                        <div class="col-md-10">
                                            <select class="form-control" id="cmbCuentas" style="max-width:80%">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-10">
                                            <button class="btn btn-success" id="agregarCuenta" onclick="agregarCuenta()">Agregar cuenta</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="marco" id="marcoIndicador">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Indicador</label>
                                        </div>
                                        <div class="col-md-8">
                                            <select class="form-control" id="cmbIndicadores" style="max-width:80%">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4"></div>
                                        <div class="col-md-8">
                                            <button class="btn btn-success" id="agregarIndicador" onclick="agregarIndicador()">Agregar indicador</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="marco" id="marcoNumeros">
                                    <button id="0" class="btn btn-info" onclick="agregarCaracter('0')">0</button>
                                    <button id="1" class="btn btn-info" onclick="agregarCaracter('1')">1</button>
                                    <button id="2" class="btn btn-info" onclick="agregarCaracter('2')">2</button>
                                    <button id="3" class="btn btn-info" onclick="agregarCaracter('3')">3</button>
                                    <button id="4" class="btn btn-info" onclick="agregarCaracter('4')">4</button>
                                    <button id="5" class="btn btn-info" onclick="agregarCaracter('5')">5</button>
                                    <button id="6" class="btn btn-info" onclick="agregarCaracter('6')">6</button>
                                    <button id="7" class="btn btn-info" onclick="agregarCaracter('7')">7</button>
                                    <button id="8" class="btn btn-info" onclick="agregarCaracter('8')">8</button>
                                    <button id="9" class="btn btn-info" onclick="agregarCaracter('9')">9</button>
                                    <button id="." class="btn btn-info" onclick="agregarCaracter('.')">.</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>