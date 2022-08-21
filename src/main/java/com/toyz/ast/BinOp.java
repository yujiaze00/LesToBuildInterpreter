package com.toyz.ast;

import com.toyz.recursion.Token;

public class BinOp extends AST{

    private AST left;
    private Token op;
    private Token token;
    private AST right;

    public BinOp (AST left, Token op, AST right) {

        this.left = left;
        this.op = op;
        this.token = op;
        this.right = right;
    }


    public AST getLeft() {
        return left;
    }

    public void setLeft(AST left) {
        this.left = left;
    }

    public Token getOp() {
        return op;
    }

    public void setOp(Token op) {
        this.op = op;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public AST getRight() {
        return right;
    }

    public void setRight(AST right) {
        this.right = right;
    }
}
