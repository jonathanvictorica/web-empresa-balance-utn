/**
 * Define a grammar called Indicador
 */
grammar Indicador;

@header {
	package gramaticas;
}

expresion:termino((SUM|RES) termino)*;
termino : variable ((MUL|DIV) variable)*;
variable : varDefinida | '('expresion')';
varDefinida : ABREV_INDICADOR(LETRA|DIGIT)+ | ABREV_CUENTA(LETRA|DIGIT)+ | DIGIT+;

ABREV_INDICADOR:'i_';

ABREV_CUENTA:'c_';


LETRA:[a-z];

DIGIT: ('0'..'9');

SUM:'+';

RES:'-';

MUL:'*';

DIV:'/';

WS : [ \t\r\n]+ -> skip;

