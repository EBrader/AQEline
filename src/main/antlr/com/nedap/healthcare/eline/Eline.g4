grammar Eline;

program
    :   statement*
        EOF
    ;

statement
    :  type=(NUM|STR) ID ASSIGN expression ';'                 #assign
    | '{' statement* '}'                            #block
    ;

expression
    : left=expression POW          right=expression #power
    | left=expression op=(MUL|DIV) right=expression #mulDiv
    | left=expression op=(ADD|SUB) right=expression #addSub
    | INT                                           #primitive
    | ID                                            #identifier
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
STR: 'String';

ID: [a-zA-Z]+;
INT: [1-9][0-9]*;

LINE_COMMENT
    : '//' ~[\r\n]* NEWLINE -> skip;

//// globale scope
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

