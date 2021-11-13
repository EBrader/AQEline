package com.nedap.healthcare.eline.symbols;

import java.util.Objects;

public class Symbol {

    private final String identifier;
    private final int value;

    public Symbol(String identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return identifier.equals(symbol.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
