package com.toyz;

/**
 * @Author yujiaze
 * @Date 2021/7/30 5:57 下午
 */
public enum TokenType {

    INTEGER("INTEGER"),
    PLUS("PLUS"),
    MINUS("MINUS"),
    MUL("MUL"),
    DIV("DIV"),
    LPAREN("("),
    RPAREN(")"),
    EOF("EOF");

    TokenType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
