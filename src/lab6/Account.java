package lab6;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;



public class Account {
	public int id;
	public int balance;

	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}

	// TODO: Task1 
	// replace the null with a lambda expression
	public static Consumer<Account> add100 = x -> x.balance +=100;

	

	// TODO: Task2 
	// define checkBound using lowerBound and upperBound
	// We want checkBound to check BOTH lowerBound AND upperBound.
	public static Predicate<Account> lowerBound = a -> a.balance >=0;
	public static Predicate<Account> upperBound = a -> a.balance <=10000;
	public static Predicate<Account> checkBound = a -> {return a.lowerBound.test(a) && a.upperBound.test(a);};

	interface AddMaker {
		Consumer<Account> make(int N);
	}

	// TODO: Task3 
	// replace the null with a lambda expression
	public static AddMaker maker = i -> {Consumer<Account> adder = x -> x.balance +=i; return adder;};


	// You can assume that all the Account in acconts have positive balances.
	public static int getMaxAccountID(List<Account> accounts) {
		// TODO: Task4 
		// replace the null with a lambda expression
		Account maxOne = accounts.stream().reduce(new Account(0, -100), (maxa, a) -> {if(maxa.balance>a.balance) return maxa; else return a;});

		return maxOne.id;
	}


}
