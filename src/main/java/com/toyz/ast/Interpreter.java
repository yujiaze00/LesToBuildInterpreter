package com.toyz.ast;

import com.toyz.recursion.Lexer;
import com.toyz.recursion.TokenType;

import java.util.Scanner;

public class Interpreter extends NodeVisitor{

    private Parser parser;

    Interpreter(Parser parser) {
        this.parser = parser;
    }

    Interpreter() {}

    public Integer interpreter() {

        AST tree = this.parser.parser();
        return visit(tree);
    }

    public Integer visit_BinOp(BinOp node) {

        if(TokenType.PLUS.getType().equals(node.getOp().getType())) {
            return this.visit(node.getLeft()) + this.visit(node.getRight());
        }
        if(TokenType.MINUS.getType().equals(node.getOp().getType())) {
            return this.visit(node.getLeft()) - this.visit(node.getRight());
        }
        if(TokenType.MUL.getType().equals(node.getOp().getType())) {
            return this.visit(node.getLeft()) * this.visit(node.getRight());
        }
        if(TokenType.DIV.getType().equals(node.getOp().getType())) {
            return this.visit(node.getLeft()) - this.visit(node.getRight());
        }
        return null;
    }

    public Integer visit_Num(Num node) {

        return (Integer) node.getValue();
    }


    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("calc> ");
                Scanner scanner = new Scanner(System.in);
                String text = scanner.nextLine();
                Lexer lexer = new Lexer(text);
                Parser parser = new Parser(lexer);
                Interpreter interpreter = new Interpreter(parser);
                System.out.println(interpreter.interpreter());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
                break;
            }
        }
    }
}
