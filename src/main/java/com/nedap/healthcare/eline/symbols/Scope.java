package com.nedap.healthcare.eline.symbols;

import com.nedap.healthcare.eline.ansi.Ansi;

import java.util.HashSet;
import java.util.Set;

public class Scope {
    private final Scope parent;

    private final Set<Symbol> symbols = new HashSet<>();

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return parent;
    }

    public void declare(final Symbol symbol) {
        symbols.add(symbol);
    }

    public boolean exists(final String identifier) {
        return symbols.stream().anyMatch(s -> s.getIdentifier().equals(identifier));
    }

    public Symbol resolve(final String identifier) {
        // Method is called prior to "exists"
        final Symbol symbol = symbols.stream().filter(s -> s.getIdentifier().equals(identifier)).findFirst().orElse(null);
        if (symbol != null) {
            return symbol;
        }
        if (parent != null) {
            return parent.resolve(identifier);
        }
        return null;
    }

    public String listSymbols() {
        StringBuilder list = new StringBuilder();
        symbols.forEach(symbol -> list.append(Ansi.Blue.colorize(symbol.getIdentifier() + " - ")));
        return list.toString();
    }
}
