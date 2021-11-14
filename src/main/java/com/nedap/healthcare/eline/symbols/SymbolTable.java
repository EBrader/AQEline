package com.nedap.healthcare.eline.symbols;


import com.nedap.healthcare.eline.ansi.Ansi;
import com.nedap.healthcare.eline.exceptions.DuplicateLocalSymbolException;
import com.nedap.healthcare.eline.exceptions.UnregisteredSymbolException;

public class SymbolTable {
    private Scope topScope = null;
    private int currentLevel = 0;

    private static final boolean PRINT_LOG = true;

    public void openScope() {
        topScope = new Scope(topScope, currentLevel);
        printLog("Creating scope at level: " + Ansi.Yellow.colorize(String.valueOf(currentLevel++)));
    }

    public void closeScope() {
        printLog(String.format("%-40s || Registered symbols: " + topScope.listSymbols(), "Closing scope at level: " + Ansi.Red.colorize(String.valueOf(--currentLevel))));
        topScope = topScope.getNext();
    }

    public void addSymbol(Symbol symbol) {
        printLog(String.format("%-40s || At level: " + Ansi.Green.colorize(String.valueOf(topScope.getLevel())), "Registering symbol: " + Ansi.Green.colorize(symbol.getIdentifier())));
        topScope.register(symbol);
    }

    public void checkSymbol(String identifier) throws DuplicateLocalSymbolException {
        if(topScope.isRegistered(identifier)) {
            throw new DuplicateLocalSymbolException(identifier);
        }
    }

    public Symbol findSymbol(String identifier) throws UnregisteredSymbolException {
        Scope s = topScope;
        while(s != null) {
            if(s.isRegistered(identifier)) {
                return s.getSymbol(identifier);
            }
            s = s.getNext();
        }
        throw new UnregisteredSymbolException(identifier);
    }

    public static void printLog(String message) {
        String log = PRINT_LOG ? message + "\n" : "";
        System.out.print(log);
    }
}

/*
SymbolTable st = new SymbolTable();
...
st.register(new Symbol("E"));

 */

/*
  var x = 3;
  var y = 2 * x + y;
  { // openScope
     var z = x + y;
     {
       {
       }
     }
  } // closeScope

 */
