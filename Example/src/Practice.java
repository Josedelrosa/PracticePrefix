import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;


public class Practice {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practice window = new Practice();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Practice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 273, 239);
		frame.getContentPane().add(scrollPane);
		JList<String> list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		
		
		textField = new JTextField();
		textField.setBounds(293, 38, 131, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// press button and it converts infix to postfix
		JButton btnCount = new JButton("Infix to Postfix");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String input = textField.getText();
				 System.out.println(input);
				 String output = "Postfix: " + infixToPostfix(input);
			     System.out.println(output);
			     listModel.addElement(output);
			    
			}
		});
		btnCount.setBounds(293, 152, 131, 32);
		frame.getContentPane().add(btnCount);
		
		JLabel lblNumber = new JLabel("Expression:");
		lblNumber.setBounds(293, 13, 131, 14);
		frame.getContentPane().add(lblNumber);
		
		JButton btnNewButton = new JButton("Infix to Prefix");
		btnNewButton.setBounds(293, 195, 131, 32);
		frame.getContentPane().add(btnNewButton);
		
		//Press button and it evaluates the expression
		JButton btnNewButton_1 = new JButton("Evaluate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String input = textField.getText();
					double output = eval(input);				
					System.out.println(output);
					listModel.addElement(Double.toString(output));
				}
				catch(RuntimeException ex){
					System.out.println("This is not a valid expression");
					listModel.addElement("This is not a valid expression");
				}
				
			}
		});
		// press button and it converts infix to prefix
		btnNewButton_1.setBounds(293, 109, 131, 32);
		frame.getContentPane().add(btnNewButton_1);
		
		// press button and it clears list
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listModel.removeAllElements();
			}
		});
		btnNewButton_2.setBounds(293, 66, 131, 32);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				System.out.println(input);
				String output = "Prefix: " + infixToPrefix(input);
				System.out.println(output);
				listModel.addElement(output);
			}
		});
		
	}
	 // function to find the priority
	   static int Prio(char ch) 
	    { 
	        switch (ch) 
	        { 
	        case '+': 
	        case '-': 
	            return 1; 
	       
	        case '*': 
	        case '/': 
	            return 2; 
	       
	        case '^': 
	            return 3; 
	        } 
	        return -1; 
	    } 
	       
	    // The main method that converts given infix expression to postfix expression.  
	    static String infixToPostfix(String exp) 
	    { 
	        // initializing empty String for result 
	        String result = new String(""); 
	          
	        // initializing empty stack 
	        Stack<Character> stack = new Stack<>(); 
	          
	        for (int i = 0; i < exp.length(); ++i) 
	        { 
	            char c = exp.charAt(i); 
	              
	             // If the scanned character is an operand, add it to output. 
	            if (Character.isLetterOrDigit(c)) 
	                result += c; 
	               
	            // If the scanned character is an '(', push it to the stack. 
	            else if (c == '(') 
	                stack.push(c); 
	              
	            //  If the scanned character is an ')', pop and output from the stack  
	            // until an '(' is encountered. 
	            else if (c == ')') 
	            { 
	                while (!stack.isEmpty() && stack.peek() != '(') 
	                    result += stack.pop(); 
	                  
	                if (!stack.isEmpty() && stack.peek() != '(') 
	                    return "Invalid Expression"; // invalid expression                 
	                else
	                    stack.pop(); 
	            } 
	            else // an operator is encountered 
	            { 
	                while (!stack.isEmpty() && Prio(c) <= Prio(stack.peek())){ 
	                    if(stack.peek() == '(') 
	                        return "Invalid Expression"; 
	                    result += stack.pop(); 
	             } 
	                stack.push(c); 
	            } 
	       
	        } 
	       
	        // pop all the operators from the stack 
	        while (!stack.isEmpty()){ 
	            if(stack.peek() == '(') 
	                return "Invalid Expression"; 
	            result += stack.pop(); 
	         } 
	        return result; 
	    }
	    
	    static boolean isOperator(char c) 
	    { 
	        return (!(c >= 'a' && c <= 'z') &&  
	                !(c >= '0' && c <= '9') &&  
	                !(c >= 'A' && c <= 'Z')); 
	    } 	      

	      
	    // method that converts infix expression to prefix expression. 
	    static String infixToPrefix(String infix) 
	    { 
	        // stack for operators. 
	        Stack<Character> operators = new Stack<Character>(); 
	      
	        // stack for operands. 
	        Stack<String> operands = new Stack<String>(); 
	      
	        for (int i = 0; i < infix.length(); i++)  
	        { 
	      
	            // If current character is an 
	            // opening bracket, then 
	            // push into the operators stack. 
	            if (infix.charAt(i) == '(')  
	            { 
	                operators.push(infix.charAt(i)); 
	            } 
	      
	            // If current character is a 
	            // closing bracket, then pop from 
	            // both stacks and push result 
	            // in operands stack until 
	            // matching opening bracket is 
	            // not found. 
	            else if (infix.charAt(i) == ')')  
	            { 
	                while (!operators.empty() &&  
	                    operators.peek() != '(')  
	                    { 
	      
	                    // operand 1 
	                    String op1 = operands.peek(); 
	                    operands.pop(); 
	      
	                    // operand 2 
	                    String op2 = operands.peek(); 
	                    operands.pop(); 
	      
	                    // operator 
	                    char op = operators.peek(); 
	                    operators.pop(); 
	      
	                    // Add operands and operator 
	                    // in form operator + 
	                    // operand1 + operand2. 
	                    String tmp = op + op2 + op1; 
	                    operands.push(tmp); 
	                } 
	      
	                // Pop opening bracket  
	                // from stack. 
	                operators.pop(); 
	            } 
	      
	            // If current character is an 
	            // operand then push it into 
	            // operands stack. 
	            else if (!isOperator(infix.charAt(i)))  
	            { 
	                operands.push(infix.charAt(i) + ""); 
	            } 
	      
	            // If current character is an 
	            // operator, then push it into 
	            // operators stack after popping 
	            // high priority operators from 
	            // operators stack and pushing 
	            // result in operands stack. 
	            else 
	            { 
	                while (!operators.empty() &&  
	                    Prio(infix.charAt(i)) <=  
	                        Prio(operators.peek()))  
	                    { 
	      
	                    String op1 = operands.peek(); 
	                    operands.pop(); 
	      
	                    String op2 = operands.peek(); 
	                    operands.pop(); 
	      
	                    char op = operators.peek(); 
	                    operators.pop(); 
	      
	                    String tmp = op + op2 + op1; 
	                    operands.push(tmp); 
	                } 
	      
	                operators.push(infix.charAt(i)); 
	            } 
	        } 
	      
	        // Pop operators from operators  
	        // stack until it is empty and  
	        // operation in add result of  
	        // each pop operands stack. 
	        while (!operators.empty())  
	        { 
	            String op1 = operands.peek(); 
	            operands.pop(); 
	      
	            String op2 = operands.peek(); 
	            operands.pop(); 
	      
	            char op = operators.peek(); 
	            operators.pop(); 
	      
	            String tmp = op + op2 + op1; 
	            operands.push(tmp); 
	        } 
	      
	        // Final prefix expression is 
	        // present in operands stack. 
	        return operands.peek(); 
	    } 
	    // method evaluates string operators ex: "5+4" = 9
	    public static double eval(final String str) {
	        return new Object() {
	            int pos = -1, ch;

	            void nextChar() {
	                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	            }

	            boolean eat(int charToEat) {
	                while (ch == ' ') nextChar();
	                if (ch == charToEat) {
	                    nextChar();
	                    return true;
	                }
	                return false;
	            }

	            double parse() {
	                nextChar();
	                double x = parseExpression();
	                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	                return x;
	            }

	            // Grammar:
	            // expression = term | expression `+` term | expression `-` term
	            // term = factor | term `*` factor | term `/` factor
	            // factor = `+` factor | `-` factor | `(` expression `)`
	            //        | number | functionName factor | factor `^` factor

	            double parseExpression() {
	                double x = parseTerm();
	                for (;;) {
	                    if      (eat('+')) x += parseTerm(); // addition
	                    else if (eat('-')) x -= parseTerm(); // subtraction
	                    else return x;
	                }
	            }

	            double parseTerm() {
	                double x = parseFactor();
	                for (;;) {
	                    if      (eat('*')) x *= parseFactor(); // multiplication
	                    else if (eat('/')) x /= parseFactor(); // division
	                    else return x;
	                }
	            }

	            double parseFactor() {
	                if (eat('+')) return parseFactor(); // unary plus
	                if (eat('-')) return -parseFactor(); // unary minus

	                double x;
	                int startPos = this.pos;
	                if (eat('(')) { // parentheses
	                    x = parseExpression();
	                    eat(')');
	                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                    x = Double.parseDouble(str.substring(startPos, this.pos));
	                } else if (ch >= 'a' && ch <= 'z') { // functions
	                    while (ch >= 'a' && ch <= 'z') nextChar();
	                    String func = str.substring(startPos, this.pos);
	                    x = parseFactor();
	                    if (func.equals("sqrt")) x = Math.sqrt(x);
	                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                    else throw new RuntimeException("Unknown function: " + func);
	                } else {
	                    throw new RuntimeException("Unexpected: " + (char)ch);
	                }

	                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	                return x;
	            }
	        }.parse();
	    }
}
