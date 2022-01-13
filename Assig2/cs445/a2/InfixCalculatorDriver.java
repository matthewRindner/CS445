package cs445.a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class InfixCalculatorDriver
{
    public static void main(String[] args) {
            System.out.println("Infix expression:");
            InfixCalculator calculator = new InfixCalculator(System.in);
            Double value = null;
            try {
                value = calculator.evaluate();
            } catch (InvalidExpressionException e) {
                System.out.println("Invalid expression: " + e.getMessage());
            }
            if (value != null) {
                System.out.println(value);
            }
        }
}