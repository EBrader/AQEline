package com.nedap.healthcare.eline.exceptions;

import com.nedap.healthcare.eline.ansi.Ansi;

public class DuplicateLocalSymbolException extends Exception {

    public DuplicateLocalSymbolException(String identifier) {
        super(Ansi.BgRed.colorize("Duplicate symbol: " + identifier));
    }
}
