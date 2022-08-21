package com.toyz.ast;

import com.toyz.recursion.Lexer;
import com.toyz.recursion.Token;
import com.toyz.recursion.TokenType;

import java.util.Objects;

public class Parser {

    private Token current_token;
    Lexer lexer;

    Parser(Lexer lexer) {
        this.lexer = lexer;
        this.current_token = this.lexer.getNextToken();
    }

    public AST parser() {
        return this.expr();
    }

    private void eat(TokenType tokenType) {

        if(current_token.getType().equals(tokenType.getType())) {
            current_token = lexer.getNextToken();
        }
    }

    private AST factor() {
        // """factor : INTEGER"""
        Token token = this.current_token;
        if(TokenType.INTEGER.getType().equals(token.getType())) {
            eat(TokenType.INTEGER);
            return new Num(token);
        } else {
            eat(TokenType.LPAREN);
            AST node = expr();
            eat(TokenType.RPAREN);
            return node;
        }
    }

    private AST term() {
        //term : factor ((MUL | DIV) factor)*
        AST node = this.factor();
        while (Objects.equals(current_token.getType(), TokenType.MUL.getType())
                || Objects.equals(current_token.getType(), TokenType.DIV.getType())) {
            Token token = current_token;
            String current_type = token.getType();
            if(current_type.equals(TokenType.MUL.getType())) {
                eat(TokenType.MUL);
            }
            if(current_type.equals(TokenType.MINUS.getType())) {
                eat(TokenType.MINUS);
            }
            BinOp binOpNode = new BinOp(node, token, factor());
            return binOpNode;
        }
        return node;
    }

    private AST expr() {

        AST node = term();
        while (Objects.equals(current_token.getType(), TokenType.PLUS.getType())
                || Objects.equals(current_token.getType(), TokenType.MINUS.getType())) {
            Token token = current_token;
            String current_type = token.getType();
            if(current_type.equals(TokenType.PLUS.getType())) {
                eat(TokenType.PLUS);
            }
            if(current_type.equals(TokenType.MINUS.getType())) {
                eat(TokenType.MINUS);
            }
            return new BinOp(node, token, term());
        }
        return node;
    }
}
