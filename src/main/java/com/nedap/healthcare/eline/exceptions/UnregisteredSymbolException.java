package com.nedap.healthcare.eline.exceptions;

import com.nedap.healthcare.eline.ansi.Ansi;

public class UnregisteredSymbolException extends Exception {

    public UnregisteredSymbolException(String identifier) {
        super(Ansi.BgRed.colorize("Unregistered symbol: " + identifier));
    }
}
