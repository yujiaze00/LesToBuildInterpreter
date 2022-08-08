package com.toyz;

import java.util.Objects;
import java.util.Scanner;

/**
 * @Author yujiaze
 * @Date 2021/7/30 5:32 下午
 */
public class Interpreter {

    private Token current_token;
    Lexer lexer;

    Interpreter(Lexer lexer) {
        this.lexer = lexer;
        this.current_token = this.lexer.getNextToken();
    }

    private void eat(TokenType tokenType) {

        if(current_token.getType().equals(tokenType.getType())) {
            current_token = lexer.getNextToken();
        }
    }

    private Integer factor() {
        // """factor : INTEGER"""
        Token token = this.current_token;
        if(TokenType.INTEGER.getType().equals(token.getType())) {
            eat(TokenType.INTEGER);
            return (Integer) token.getValue();
        } else {
            eat(TokenType.LPAREN);
            Integer result = (Integer) expr();
            eat(TokenType.RPAREN);
            return result;
        }
    }

    private Integer term() {
        //term : factor ((MUL | DIV) factor)*
        Integer result = this.factor();
        while (Objects.equals(current_token.getType(), TokenType.MUL.getType())
                || Objects.equals(current_token.getType(), TokenType.DIV.getType())) {
            String current_type = current_token.getType();
            if(current_type.equals(TokenType.MUL.getType())) {
                eat(TokenType.MUL);
                result *= factor();
            }
            if(current_type.equals(TokenType.MINUS.getType())) {
                eat(TokenType.MINUS);
                result /= factor();
            }
        }
        return result;
    }

    private Object expr() {

        int result = term();
        while (Objects.equals(current_token.getType(), TokenType.PLUS.getType())
                || Objects.equals(current_token.getType(), TokenType.MINUS.getType())) {
            String current_type = current_token.getType();
            if(current_type.equals(TokenType.PLUS.getType())) {
                eat(TokenType.PLUS);
                result += term();
            }
            if(current_type.equals(TokenType.MINUS.getType())) {
                eat(TokenType.MINUS);
                result -= term();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("calc> ");
                Scanner scanner = new Scanner(System.in);
                String text = scanner.nextLine();
                Lexer lexer = new Lexer(text);
                Interpreter interpreter = new Interpreter(lexer);
                System.out.println(interpreter.expr());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
                break;
            }
        }
    }
}
