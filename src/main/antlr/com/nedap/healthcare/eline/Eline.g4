grammar Eline;

program
    :   statement*
        EOF
    ;

statement
    :  NUM SYM ASSIGN expression ';'                 #assignInt
    |  FLT SYM ASSIGN expression ';'                 #assignFloat
    |  STR SYM ASSIGN string_expression ';'          #assignStr
    | '{' statement* '}'                             #block
    ;

string_expression: left=string_expression ADD right=string_expression #addStr
                 | STRING_LITERAL                                     #literalStr
                 | SYM                                                 #symbolStr
                 ;

expression
    : left=expression POW          right=expression #power
    | left=expression op=(MUL|DIV) right=expression #mulDiv
    | left=expression op=(ADD|SUB) right=expression #addSub
    | prim=(INT|FLOAT)                              #primitive
    | SYM                                           #symbol
    | HLEFT expression HRIGHT                       #parenthesized
    ;

// Lexical grammar
// Terminal symbols
MUL: '*';
ADD: '+';
SUB: '-';
DIV: '/';
POW: '^';

HLEFT: '(';
HRIGHT: ')';

ASSIGN: '=';
WHITESPACE: [ \t]+ -> skip;
NEWLINE: '\r'? '\n' -> skip;

NUM: 'int';
FLT: 'float';
STR: 'String';

STRING_LITERAL: '"' SYM '"';

SYM: [a-zA-Z]+;
INT: [1-9][0-9]*;
FLOAT: INT '.' [0-9]*;

LINE_COMMENT
    : '//' ~[\r\n]* NEWLINE -> skip;

//// globale scope
// BLABLABLA CHOCOLADEVLA
//var x = 2;  // definitie van een variabele
//var y = 3;
//var z = x + y; // optellen, kan met variabelen
//{  // nieuwe scope
//  var i = 1;
//  var j = x + 2; // optellen kan ook met een variabele en een getal
//  {  // nog diepere nieuwe scope
//    var k = 1 + z;
//  }
//}
//var m = 2 + 3; // optellen kan ook met getallen
//{ // en nog een nieuwe scope
//  var n = 6 + 8 + 1996;
//}

