package ru.vmsoftware.parser.builder.examples.calculator;

import org.junit.Test;
import ru.vmsoftware.parser.builder.CaptureContext;
import ru.vmsoftware.parser.builder.CaptureListener;
import ru.vmsoftware.parser.builder.ParserBuilder;
import ru.vmsoftware.parser.builder.matchers.HolderMatcher;
import ru.vmsoftware.parser.builder.matchers.TokenMatcher;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.vmsoftware.parser.builder.matchers.MatcherFactory.*;

/**
 * @author Vyacheslav Mayorov
 * @since 2014-18-01
 */
public class CalculatorParserTest {

    static interface EvaluationContext {
        double resolve(String identifier);
    }

    static interface Expression {
        double evaluate(EvaluationContext ctx);
    }

    static class Number implements Expression {
        private double value;

        Number(double value) {
            this.value = value;
        }

        @Override
        public double evaluate(EvaluationContext ctx) {
            return value;
        }
    }

    static class Identifier implements Expression {
        private String identifier;

        Identifier(String identifier) {
            this.identifier = identifier;
        }

        @Override
        public double evaluate(EvaluationContext ctx) {
            return ctx.resolve(identifier);
        }
    }

    static enum BinaryOperation {
        ADD,
        SUB,
        MUL,
        DIV,
        POW;

        public double execute(double a, double b) {
            switch (this) {
            case ADD: return a + b;
            case SUB: return a - b;
            case MUL: return a * b;
            case DIV: return a / b;
            case POW: return Math.pow(a, b);
            }
            throw new UnsupportedOperationException("Unknown operation: " + this);
        }

    }

    static enum UnaryOperation {
        NEGATE;

        public double execute(double a) {
            switch (this) {
            case NEGATE: return -a;
            }
            throw new UnsupportedOperationException("Unknown unary operation: " + this);
        }
    }

    static class BinaryExpression implements Expression {
        private Expression left;
        private Expression right;
        private BinaryOperation operation;

        BinaryExpression(BinaryOperation operation, Expression left, Expression right) {
            this.left = left;
            this.right = right;
            this.operation = operation;
        }

        @Override
        public double evaluate(EvaluationContext ctx) {
            final double leftValue = left.evaluate(ctx);
            final double rightValue = right.evaluate(ctx);
            return operation.execute(leftValue, rightValue);
        }
    }

    static class UnaryExpression implements Expression {
        private Expression expression;
        private UnaryOperation operation;

        UnaryExpression(UnaryOperation operation, Expression expression) {
            this.operation = operation;
            this.expression = expression;
        }

        @Override
        public double evaluate(EvaluationContext ctx) {
            final double value = expression.evaluate(ctx);
            return operation.execute(value);
        }
    }

    private CaptureListener binaryOp(final BinaryOperation operation) {
        return new CaptureListener() {
            @Override
            public void onCapture(CaptureContext context) {
                final List<Expression> expressions = context.getValues();
                Expression e = expressions.get(0);
                for (int i = 1; i < expressions.size(); i++) {
                    e = new BinaryExpression(operation, e, expressions.get(i));
                }
                context.addValue(e);
            }
        };
    }

    private CaptureListener unaryOp(final UnaryOperation operation) {
        return new CaptureListener() {
            @Override
            public void onCapture(CaptureContext context) {
                context.addValue(new UnaryExpression(operation, context.<Expression>getValue()));
            }
        };
    }

    @Test
    public void testTest() throws Exception {

        final TokenMatcher digit = charRange('0', '9');
        final TokenMatcher number = sequence(oneOrMore(digit), zeroOrOne(sequence(charValue('.'), oneOrMore(digit))));

        final TokenMatcher letter = any(charRange('a', 'z'), charRange('A', 'Z'));
        final TokenMatcher identifier = any(sequence(letter, oneOrMore(any(digit, letter))));
        final TokenMatcher whitespace = oneOrMore(charInArray(' ', '\t', '\r', '\n', '\f'));

        final HolderMatcher expr = holder();
        final TokenMatcher element = any(
                capture(new CaptureListener() {
                    @Override
                    public void onCapture(CaptureContext context) {
                        context.addValue(new Number(Double.parseDouble(context.getCapture().toString())));
                    }
                }, number),
                capture(new CaptureListener() {
                    @Override
                    public void onCapture(CaptureContext context) {
                        context.addValue(new Identifier(context.getCapture().toString()));
                    }
                }, identifier),
                tokens(charValue('('), expr, charValue(')')));

        final TokenMatcher unary = any(
                capture(unaryOp(UnaryOperation.NEGATE), tokens(charValue('-'), element)),
                element
        );

        final TokenMatcher highOrder = any(
                capture(binaryOp(BinaryOperation.MUL), tokens(unary, oneOrMore(tokens(charValue('*'), unary)))),
                capture(binaryOp(BinaryOperation.DIV), tokens(unary, oneOrMore(tokens(charValue('/'), unary)))),
                capture(binaryOp(BinaryOperation.POW), tokens(unary, oneOrMore(tokens(charValue('^'), unary)))),
                unary
        );

        final TokenMatcher lowOrder = any(
                capture(binaryOp(BinaryOperation.ADD), tokens(highOrder, oneOrMore(tokens(charValue('+'), highOrder)))),
                capture(binaryOp(BinaryOperation.SUB), tokens(highOrder, oneOrMore(tokens(charValue('-'), highOrder)))),
                highOrder
        );

        expr.init(lowOrder);

        final Expression root = ParserBuilder.parse("15 * 19 + 25 * (10 + - 91)").
                by(expr).
                skip(whitespace).
                getResult();

        final double result = root.evaluate(new EvaluationContext() {
            @Override
            public double resolve(String identifier) {
                return 0;
            }
        });

        assertEquals(result, -1740, 0.5);
    }
}
