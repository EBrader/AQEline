package com.nedap.healthcare.eline.symbols;


import com.nedap.healthcare.eline.ansi.Ansi;
import com.nedap.healthcare.eline.types.Type;

public class SymbolTable {
    private Scope currentScope = null;
    private AssignType currentAssignType = null;

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

    public void openAssignType(final Type parent) {
        currentAssignType = new AssignType(parent);
    }

    public void closeAssignType() {
        currentAssignType = null;
    }

    public void declare(final Symbol symbol) {
        printLog("Registering symbol: " + Ansi.Blue.colorize(symbol.getIdentifier()), Ansi.Magenta);
        currentScope.declare(symbol);
    }

    public boolean checkSymbol(String identifier) {
        return currentScope.exists(identifier);
    }

    public Symbol resolve(String identifier) {
        return currentScope.resolve(identifier);
    }

    public void checkSymbolType(final Symbol symbol) {
        Type childType = symbol.getType();
        String identifier = symbol.getIdentifier();

        if (currentAssignType.isInvalid(childType)) {
            printLog("For symbol [" + identifier + "]:\nInvalid type [" + childType.name() + "] while expected [" + currentAssignType.getParentType().name() + "]", Ansi.BgRed);
        }
    }

    public boolean isStringType() {
        return currentAssignType.getParentType() == Type.STRING;
    }

    public void checkNumType(int number) {
        if(currentAssignType.isInvalid(Type.INTEGER)) {
            printLog("Invalid numeric type [" + String.valueOf(number) + "] should be parsed to [" + currentAssignType.getParentType().name() + "]", Ansi.BgRed);
        }
    }

    public void checkFunctionType(final String function) {
        if(currentAssignType.isInvalid(function)) {
            printLog("Invalid function type [" + function + "] cannot be applied to [" + currentAssignType.getParentType().name() + "]", Ansi.BgRed);
        }
    }

    public static void printLog(final String message, final Ansi color) {
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
