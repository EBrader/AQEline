package com.nedap.healthcare.eline.symbols;

import com.nedap.healthcare.eline.types.Type;

import java.util.Objects;

public class Symbol {

    private final Type type;
    private final String identifier;
    private final String value;

    public Symbol(final Type type, final String identifier, final String value) {
        this.type = type;
        this.identifier = identifier;
        this.value = value;
    }

    public Symbol(final Type type, final String identifier) {
        this(type, identifier, null);
    }

    public String getIdentifier() {
        return identifier;
    }
    public Type getType() { return type; }
    public String getValue() { return value; }



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
