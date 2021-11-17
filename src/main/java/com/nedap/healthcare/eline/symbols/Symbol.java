package com.nedap.healthcare.eline.symbols;

import com.nedap.healthcare.eline.types.Type;

import java.util.Objects;

public class Symbol {

    private final Type type;
    private final String identifier;

    public Symbol(Type type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
    public Type getType() { return type; }

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
