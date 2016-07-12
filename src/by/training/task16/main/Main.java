package by.training.task16.main;

import by.training.task16.controller.Controller;

public class Main {
	public static int PHILOSOPHERS_NUMBER = 5;

	public static void main(String[] args) {
		new Controller(PHILOSOPHERS_NUMBER).newRun();

	}

}
