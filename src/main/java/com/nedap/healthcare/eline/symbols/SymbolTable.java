package com.nedap.healthcare.eline.symbols;


import com.nedap.healthcare.eline.print.ansi.Ansi;
import com.nedap.healthcare.eline.print.log.Logging;
import com.nedap.healthcare.eline.types.Type;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private Scope currentScope = null;
    private Type currentType = null;

    private final List<String> ERROR_LOG = new ArrayList<>();

    public void openScope() {
        currentScope = new Scope(currentScope);
        Logging.printLog(Logging.CREATE_SCOPE, Ansi.Green);
    }

    public void closeScope() {
        Logging.printLog(Logging.CLOSE_SCOPE, Ansi.Green);
        Logging.printLog(String.format(Logging.REGISTERED, currentScope.listSymbols()), Ansi.Magenta);;
        currentScope = currentScope.getParent();
    }

    public void checkErrors() {
        if(ERROR_LOG.isEmpty()) {
            Logging.printLog(Logging.SUCCESS_ST, Ansi.BgGreen);
        } else {
            Logging.printLog(Logging.ERROR_ST, Ansi.BgRed);
            for(String error : ERROR_LOG) {
                Logging.printLog(error, Ansi.Red);
            }
            throw new RuntimeException();
        }
    }

    public void declare(final Symbol symbol) {
        Logging.printLog(String.format(Logging.REGISTER, symbol.getIdentifier()), Ansi.Magenta);
        currentScope.declare(symbol);
    }

    public boolean checkSymbol(String identifier) {
        boolean check = currentScope.exists(identifier);
        if(check) {
            logError(String.format(Logging.ERROR_DUPLICATE, identifier));
        }
        return check;
    }

    public Symbol resolve(String identifier) {
        Symbol symbol = currentScope.resolve(identifier);
        if(symbol == null) {
            logError(String.format(Logging.ERROR_UNREGISTERED, identifier));
        }
        return symbol;
    }

    public void checkSymbolType(final Symbol symbol) {
        if(symbol.getType() != currentType) {
            ERROR_LOG.add(String.format(Logging.ERROR_ST_TYPE, symbol.getIdentifier(), symbol.getType().name(), currentType.name()));
        }
    }
    public void setCurrentType(Type currentType) { this.currentType = currentType; }
    public void resetCurrentType() { currentType = null; }

    private void logError(final String message) {
        ERROR_LOG.add(message);
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
