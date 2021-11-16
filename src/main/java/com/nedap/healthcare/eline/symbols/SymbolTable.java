package com.nedap.healthcare.eline.symbols;


import com.nedap.healthcare.eline.ansi.Ansi;

public class SymbolTable {
    private Scope currentScope = null;

    private static final boolean PRINT_LOG = true;

    public void openScope() {
        currentScope = new Scope(currentScope);
        printLog("Creating scope", Ansi.Green);
    }

    public void closeScope() {
        printLog("Closing scope", Ansi.Red);
        printLog(String.format("Registered symbols: " + currentScope.listSymbols(), ""), Ansi.Magenta);
        currentScope = currentScope.getParent();
    }

    public void declare(Symbol symbol) {
        printLog("Registering symbol: " + Ansi.Blue.colorize(symbol.getIdentifier()), Ansi.Magenta);
        currentScope.declare(symbol);
    }

    public boolean checkSymbol(String identifier) {
        return currentScope.exists(identifier);
    }

    public Symbol resolve(String identifier) {
        return currentScope.resolve(identifier);
    }

    public static void printLog(String message, Ansi color) {
        String log = PRINT_LOG ? color.colorize(message) + "\n" : "";
        System.out.print(log);
    }
}

/*
SymbolTable st = new SymbolTable();
...
st.declare(new Symbol("E"));

 */

/*
  var x = 3;
  var y = 2 * x + x;
  { // openScope
     var z = x + y;
     {
       {
       }
     }
  } // closeScope

 */
