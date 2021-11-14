package com.nedap.healthcare.eline.symbols;

import com.nedap.healthcare.eline.ansi.Ansi;

import java.util.HashSet;
import java.util.Set;

public class Scope {

    private final Scope next;
    private final int level;

    private final Set<Symbol> symbols = new HashSet<>();

    public Scope(Scope next, int level) {
        this.next = next;
        this.level = level;
    }

    public void register(final Symbol symbol) {
        symbols.add(symbol);
    }

    public boolean isRegistered(final String identifier) {
        return symbols.stream().anyMatch(symbol -> symbol.getIdentifier().equals(identifier));
    }

    public Symbol getSymbol(final String identifier) {;
        // Method is called prior to "isRegistered"
        return symbols.stream().filter(symbol -> symbol.getIdentifier().equals(identifier)).findFirst().get();
    }

    public Scope getNext() {
        return next;
    }

    public int getLevel() {
        return level;
    }

    public String listSymbols() {
        StringBuilder list = new StringBuilder();
        symbols.forEach(symbol -> list.append(Ansi.Blue.colorize(symbol.getIdentifier() + " - ")));
        return list.toString();
    }
}
