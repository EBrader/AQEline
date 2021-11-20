package com.nedap.healthcare.eline.print.log;

import com.nedap.healthcare.eline.print.ansi.Ansi;

/**
 * This class contains strings for logging and methods for printing.
 */
public class Logging {

    private static final boolean PRINT_LOG = true;

    public static final String CREATE_SCOPE = "Creating scope";
    public static final String CLOSE_SCOPE = "Closing scope";

    public static final String REGISTER = "Registered symbol: %s";
    public static final String REGISTERED = "Registered symbols: %s";

    public static final String SUCCESS_ST = "SymbolTable finished with success";

    // ERROR MESSAGES
    public static final String ERROR_ST = "SymbolTable finished with errors:";
    public static final String ERROR_ST_SYMBOL_TYPE = "Symbol [%s] has type [%s] while [%s] expected";
    public static final String ERROR_ST_FLOAT_TYPE = "Could not convert FLOAT [%s] to INTEGER without data loss";

    public static final String ERROR_DUPLICATE = "Symbol [%s] already in scope.";
    public static final String ERROR_UNREGISTERED = "Symbol [%s] not declared.";

    public static void printLog(final String message, final Ansi color) {
        String msg = PRINT_LOG ? color.colorize(message) + "\n" : "";
        System.out.print(msg);
    }
}
