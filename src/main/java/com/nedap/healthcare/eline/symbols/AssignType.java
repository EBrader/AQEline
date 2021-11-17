package com.nedap.healthcare.eline.symbols;

import com.nedap.healthcare.eline.types.Type;

public class AssignType {

    private final Type parentType;

    public AssignType(Type parentType) {
        this.parentType = parentType;
    }

    public boolean isInvalid(final Type childType) {
        return childType != parentType;
    }

    public boolean isInvalid(final String function) {
        if (function.equals("+")) {
            return false;
        }
        return parentType != Type.INTEGER;
    }

    public Type getParentType() { return parentType; }
}
