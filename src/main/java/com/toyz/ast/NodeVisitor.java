package com.toyz.ast;

public class NodeVisitor {

    public Integer visit(AST node) {

        Interpreter interpreter = null;
        Integer result = 0;
        try {
            interpreter = Interpreter.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(node instanceof BinOp) {
            assert interpreter != null;
            result = interpreter.visit_BinOp((BinOp) node);
        }
        if(node instanceof Num) {
            assert interpreter != null;
            result = interpreter.visit_Num((Num) node);
        }
        return result;
    }
}
