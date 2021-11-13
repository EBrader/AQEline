package com.nedap.healthcare.eline.symbols;

public class SymbolTable {
    private Scope topScope = null;
    private int currentLevel = 0;

    public void openScope() {
        topScope = new Scope(topScope, currentLevel++);
    }

    public void closeScope() {
        topScope = topScope.getNext();
        currentLevel--;
    }

    public void addSymbol(Symbol symbol) {
        topScope.register(symbol);
    }

    public Symbol findSymbol(String identifier) {
        Scope s = topScope;
        while(s != null) {
            if(s.isRegistered(identifier)) {
                return s.getSymbol(identifier);
            }
            s = s.getNext();
        }
        return null;
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
