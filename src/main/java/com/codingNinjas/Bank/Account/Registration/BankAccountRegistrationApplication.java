package com.codingNinjas.Bank.Account.Registration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class BankAccountRegistrationApplication {

	public static void main(String[] args) {

		/*
		You need to complete this application as mentioned in the problem 
		statement build your own logic and perform the following tasks.
		
		* 1. Fetch context from ApplicationContext.xml and initiate scanner.
		* 2. Get user details from console.
		* 3. Get account details from user and add them to the account list.
		* 4. Display the list of accounts with their reference ids.
		*/
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		User user= (User) context.getBean("myUser");

		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to Account Registration Application!");
		System.out.println("Please enter your name");
		String name=scanner.nextLine();
		user.setUserDetails(name);

		while(true){
			//scanner.nextLine();
			String accountType;
			System.out.println("Do you want to add account\n1. Yes\n2. No");
			int choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Please select the account type\n1. Current\n2.Savings");
				int bankChoice=scanner.nextInt();
				switch (bankChoice){
					case 1->accountType="currentAccount";
					case 2->accountType="savingsAccount";
					default -> {
						return;
					}

				}

				Account account= (Account) context.getBean(accountType);
				System.out.println("Enter the opening balance");
				double amount=scanner.nextDouble();
				account.addBalance(amount);
				user.addAccount(account);
			}else {
				System.out.println("Hi "+user.getName()+", here is the list of your accounts:");
				for(Account account:user.getAllAccounts()){
					int userReferenceLength = account.toString().length(); //46
					System.out.println(account.getAccountType()+" : opening balance - "+account.getBalance()+" Reference Id "+account.toString().substring(userReferenceLength - 9, userReferenceLength));

				}
				break;
			}

		}
		context.close();
		scanner.close();
    }

}
