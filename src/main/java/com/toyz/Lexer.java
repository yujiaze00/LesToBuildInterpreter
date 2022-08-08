package com.toyz;

/**
 * @author yujiaze
 * @date 2022/8/7 15:51
 */
public class Lexer {

    private final String text;
    private Integer pos;
    private Character current_char;

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.current_char = this.text.charAt(pos);
    }

    private void advance() {
        pos += 1;
        if(pos > text.length() - 1) {
            current_char = null;
            return;
        }
        current_char = text.charAt(pos);
    }

    private void skip_whitespace() {
        while (" ".equals(String.valueOf(current_char))){
            advance();
        }
    }

    private Integer integer() {

        StringBuilder result = new StringBuilder();
        while(current_char != null && Character.isDigit(current_char)) {
            result.append(current_char);
            advance();
        }
        return Integer.valueOf(result.toString());
    }

    public Token getNextToken() {

        while (current_char != null) {
            if (" ".equals(String.valueOf(current_char))){
                skip_whitespace();
            }
            if(Character.isDigit(current_char)) {
                return new Token(TokenType.INTEGER.getType(), integer());
            }
            if(current_char == '+') {
                advance();
                return new Token(TokenType.PLUS.getType(), '+');
            }
            if(current_char == '-') {
                advance();
                return new Token(TokenType.MINUS.getType(), '-');
            }
            if(current_char == '*') {
                advance();
                return new Token(TokenType.MUL.getType(), '*');
            }
            if(current_char == '/') {
                advance();
                return new Token(TokenType.DIV.getType(), '/');
            }
            if(current_char == '(') {
                advance();
                return new Token(TokenType.LPAREN.getType(), '(');
            }
            if(current_char == ')') {
                advance();
                return new Token(TokenType.RPAREN.getType(), ')');
            }
        }

        return new Token(TokenType.EOF.getType(), null);
    }
}
