import java.util.*;
import java.lang.*;
import java.io.*;

class Conversion{


	//the method to set the precedence
	static int Prec(char ch){
		switch(ch){
			case '+':
				return 1;
			case '-':
				return 1;
			case '*':
				return 2;
			case '/':
				return 2;
			case '^':
				return 3;
		}
		return -1;
	}
		
		
	//method to check a operator
	static Boolean Op(char c){
		switch(c){
			case '+':
			case '-':
			case '*':
			case '/':
			case '^':
				return true;
		}
		return false;
	}


	//method to convert postfix to prefix
	static String postfixToPrefix(String s){
	
		//initializing a empty stack
		Stack<String> stack = new Stack<>();
	
		//initializing empty String
		String result = new String("");
	
		for(int i = 0; i < s.length(); i++){

			//if character is operator
			if(Op(s.charAt(i))){
				String s1= stack.peek();
				stack.pop();
				String s2= stack.peek();
				stack.pop();
				String temp= s.charAt(i)+s2+s1;
				stack.push(temp);
			}

			//if the character is letter or digit
			else 
				stack.push(Character.toString(s.charAt(i)));
		}

		for(String i : stack){
			result += i;
		}
		return result;	
	}


	

	//the method that changes infix exp to postfix exp
	static String infixToPostfix(String s){

		//initializing empty stack
		Stack<Character> stack = new Stack<>();

		//initializing empty string
		String result = new String("");
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);	

			//checking whether the character is letter or digit
			//then add it to output
			if(Character.isLetterOrDigit(c)){
				result += c;
			}

			//character is opening brace
			//push it to the stack
			else if(c =='('){
				stack.push(c);
			}

			//character is closing brace
			//pop from the stack till the opening brace
			else if( c == ')'){
				while(!stack.isEmpty() && stack.peek() != '('){
					result += stack.pop();
				}
				stack.pop();
			}
		
			//character is an operator
			//check for precedence
			else {
				while(!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){
					result += stack.pop();
				}
				stack.push(c);
			} 		
		} 
		//pop till stack is empty
		while(!stack.isEmpty()){
			if(stack.peek() == '('){
				return "invalid expression";
			}
			result += stack.pop();
		}
		return result;
	}

	//Driver method
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		System.out.println("1: Convert infix to postfix.");
		System.out.println("2: Convert postfix to prefix.");
		System.out.println("3: Convert infix to prefix.");
		System.out.print("What Do You Want To Convert?");
		char n = sc.next().charAt(0);
		Scanner scc = new Scanner(System.in);
		System.out.print("Enter an Expression to Convert: ");
		String st = new String();
		st = scc.nextLine();

		if(n == '1'){
			System.out.println();		
			System.out.println("Postfix Expression: " + infixToPostfix(st));
		}
		
		else if(n == '2'){
			System.out.println();
			System.out.println("Prefix Expression: " + postfixToPrefix(st));
		}

		else if(n == '3'){
			System.out.println();
			System.out.println("Prefix Expression: " + postfixToPrefix(infixToPostfix(st)));
		}

		else{
			System.out.println("Invalid Conversion!");
		}	
	}
}