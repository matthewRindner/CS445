package cs445.a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * This class uses two stacks to evaluate an infix arithmetic expression from an
 * InputStream. It should not create a full postfix expression along the way; it
 * should convert and evaluate in a pipelined fashion, in a single pass.
 */
public class InfixCalculator {
    // Tokenizer to break up our input into tokens
    StreamTokenizer tokenizer;

    // Stacks for operators (for converting to postfix) and operands (for
    // evaluating)
    StackInterface<Character> operatorStack;
    StackInterface<Double> operandStack;

    /**
     * Initializes the calculator to read an infix expression from an input
     * stream.
     * @param input the input stream from which to read the expression
     */
    public InfixCalculator(InputStream input) {
        // Initialize the tokenizer to read from the given InputStream
        tokenizer = new StreamTokenizer(new BufferedReader(
                        new InputStreamReader(input)));

        // StreamTokenizer likes to consider - and / to have special meaning.
        // Tell it that these are regular characters, so that they can be parsed
        // as operators
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');

        // Allow the tokenizer to recognize end-of-line, which marks the end of
        // the expression
        tokenizer.eolIsSignificant(true);

        // Initialize the stacks
        operatorStack = new ArrayStack<Character>();
        operandStack = new ArrayStack<Double>();
    }

    /**
     * Parses and evaluates the expression read from the provided input stream,
     * then returns the resulting value
     * @return the value of the infix expression that was parsed
     */
    public Double evaluate() throws InvalidExpressionException {
        // Get the first token. If an IO exception occurs, replace it with a
        // runtime exception, causing an immediate crash.
        try {
            tokenizer.nextToken();
            // System.out.println(performMath('*', 5, 2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Continue processing tokens until we find end-of-line
        while (tokenizer.ttype != StreamTokenizer.TT_EOL) {
            // Consider possible token types
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    // If the token is a number, process it as a double-valued
                    // operand
                    handleOperand((double)tokenizer.nval);
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                case '\\':
                case '^':
                    // If the token is any of the above characters, process it
                    // is an operator
                    handleOperator((char)tokenizer.ttype);
                    break;
                case '(':
                case '[':
                    // If the token is open bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleOpenBracket((char)tokenizer.ttype);
                    break;
                case ')':
                case ']':
                    // If the token is close bracket, process it as such. Forms
                    // of bracket are interchangeable but must nest properly.
                    handleCloseBracket((char)tokenizer.ttype);
                    break;
                case StreamTokenizer.TT_WORD:
                    // If the token is a "word", throw an expression error
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                                    tokenizer.sval);
                default:
                    // If the token is any other type or value, throw an
                    // expression error
                    throw new InvalidExpressionException("Unrecognized symbol: " +
                                    String.valueOf((char)tokenizer.ttype));
            }

            // Read the next token, again converting any potential IO exception
            try {
                tokenizer.nextToken();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Almost done now, but we may have to process remaining operators in
        // the operators stack
        handleRemainingOperators();

        // Return the result of the evaluation
        // TODO: Fix this return statement
        if(!operandStack.isEmpty())
            return operandStack.pop();
        else
            return 0.0;
    }

    /**
     * This method is called when the calculator encounters an operand. It
     * manipulates operatorStack and/or operandStack to process the operand
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operand the operand token that was encountered
     */
    void handleOperand(double operand) {
        // TODO: Complete this method
        operandStack.push(operand);
    }

    /**
     * This method is called when the calculator encounters an operator. It
     * manipulates operatorStack and/or operandStack to process the operator
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param operator the operator token that was encountered
     */
    void handleOperator(char operator) {
        // TODO: Complete this method
    	double operand_1; 
        double operand_2;
        char nextOperator;
        double result;
    	//time to actually to the stack operations
    	while (!operatorStack.isEmpty() && presedenceOfOperator(operator) <= presedenceOfOperator(operatorStack.peek()))	
    	// if op is less than op on the stack, do it
    	{
    		nextOperator = operatorStack.pop();
            operand_2 = operandStack.pop();
            operand_1 = operandStack.pop();

            //operandStack.performMath(nextOperator, operand_1, operand_2);
            result = performMath(nextOperator, operand_1, operand_2);
            operandStack.push(result);

    	}
    	operatorStack.push(operator);

    }

    /**
     * This method is called when the calculator encounters an open bracket. It
     * manipulates operatorStack and/or operandStack to process the open bracket
     * according to the Infix-to-Postfix and Postfix-evaluation algorithms.
     * @param openBracket the open bracket token that was encountered
     */
    void handleOpenBracket(char openBracket) {
        // TODO: Complete this method
        operatorStack.push(openBracket);
    }

    /**
     * This method is called when the calculator encounters a close bracket. It
     * manipulates operatorStack and/or operandStack to process the close
     * bracket according to the Infix-to-Postfix and Postfix-evaluation
     * algorithms.
     * @param closeBracket the close bracket token that was encountered
     */
    void handleCloseBracket(char closeBracket) throws InvalidExpressionException {
        // TODO: Complete this method
        
    	//int typeOfParen;
      

        double operand_1;
        double operand_2;
        char nextOperator = operatorStack.pop();
        //double result;

        switch(closeBracket)
        {
        	case ')':
                while (nextOperator != '(') {
                    if (nextOperator == '[') {
                        throw new InvalidExpressionException("Unbalanced expression");
                    }
                    operand_2 = operatorStack.pop();
                    operand_1 = operandStack.pop();
                    double result;
                    //operandStack.performMath(nextOperator, operand_1, operand_2);
                    if(nextOperator == '/' && operand_1 == 0)
	     				throw new InvalidExpressionException("Cannot Divide be Zero");
                    result = performMath(nextOperator, operand_2, operand_1);
           			operandStack.push(result);

                    if (!operatorStack.isEmpty()) {
                        nextOperator = operatorStack.pop();
                    } else 
                    {
                        break;
                    }
                }
                break;

            case ']':
	            while (nextOperator != '[')
	            {
	                if (nextOperator == '(') 
	                {
	                    throw new InvalidExpressionException("Unbalanced expression");
	                }
	                operand_2 = operandStack.pop();
	                operand_1 = operandStack.pop();
	                double result;
	                //operandStack.performMath(nextOperator, operand_1, operand_2);
	                if(nextOperator == '/' && operand_1 == 0)
	     				throw new InvalidExpressionException("Cannot Divide be Zero");
	                result = performMath(nextOperator, operand_2, operand_1);
            		operandStack.push(result);

	                if (!operatorStack.isEmpty()) 
	                {
	                    nextOperator = operatorStack.pop();
	                } else 
	                {
	                    break;
	                }
	            }
	            break;
	        }
   		 
    }

    /**
     * This method is called when the calculator encounters the end of an
     * expression. It manipulates operatorStack and/or operandStack to process
     * the operators that remain on the stack, according to the Infix-to-Postfix
     * and Postfix-evaluation algorithms.
     */
    void handleRemainingOperators() throws InvalidExpressionException {
        // TODO: Complete this method
        double operand_1; 
        double operand_2;
        char nextOperator;
        double result;
        while(!operatorStack.isEmpty())
        {
        	nextOperator = operatorStack.pop();
        	//operand_1 = operandStack.pop();
        	//operand_2 = operandStack.pop();

        	if(operandStack.isEmpty())
    		    //operand_1 = operandStack.pop();
    		//else
    			throw new InvalidExpressionException("Not Enhough operands in string");
    		else
    			operand_1 = operandStack.pop();

	     	if(operandStack.isEmpty())
	     		//operand_2 = operandStack.pop();
	     	//else
	     		throw new InvalidExpressionException("Not Enhough operands in string");
	     	else
	     		operand_2 = operandStack.pop();

	     	if(nextOperator == '/' && operand_1 == 0)
	     			throw new InvalidExpressionException("Cannot Divide be Zero");

	     	operandStack.push(performMath(nextOperator, operand_1, operand_2));
        }
    }


    

     private double performMath(char nextOperator, double operand_1, double operand_2)
     {

        switch(nextOperator)
        {
            case '+':
                return operand_1 + operand_2;
                
            case '-':
                return operand_2 - operand_1;
                
            case '*':
                return operand_1 * operand_2;
            case '/':
                return operand_2 / operand_1;
                
            case '^':
                return Math.pow(operand_2, operand_1);

            }
            return 0;
     }

	private int presedenceOfOperator(Character entry) {
	        switch(entry) 
	        {
	            case '+':
	            	return 1;
	            	
	            case '-':
	                return 1;
	                
	            case '*':
	            	return 2; 
	            	
	            case '/':
	                return 2;

	            //case "\":		there is no integer operation symbol, using \ will reslt in compililation errors
	            //    return 2; so its difficult to differentialte division vs integer division

	                
	            case '^':
	                return 3;
	                
	            case '(':
	            	return 0;
	            	
	            case '[':
	                return 0;
	                
	            default:
	                return 1;
	        }
	    }

	   /**
     * Creates an InfixCalculator object to read from System.in, then evaluates
     * its input and prints the result.
     * @param args not used
     */

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

