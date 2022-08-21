package com.toyz.recursion;

/**
 * @Author yujiaze
 * @Date 2021/7/30 5:29 下午
 */
public class Token {

    private final String type;
    private final Object value;

    public Token(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
