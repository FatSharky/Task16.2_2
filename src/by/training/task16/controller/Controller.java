package by.training.task16.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import by.training.task16.domain.Fork;
import by.training.task16.service.Philosopher;
import by.training.task16.service.util.ServiceUtil;

/**
 * Controller
 * 
 * @author Vladislav
 *
 */
public class Controller {
	private final static int WORK_TIME = 100;
	private final static int WAIT_TIME = 50;
	private final List<Philosopher> philosophers = new ArrayList<>();
	private final int n;
	private final long THINK_TIME = 1L;
	private final long EAT_TIME = 1L;

	public Controller(int n) {
		this.n = n;
	}

	/**
	 * Method to run application 
	 * It creates a semaphore, list of forks = philosopher
	 * create philosopher with "left" & "right" fork 
	 * "right" fork = id philosopher 
	 * "left" fork = id+1 except last(id 0)
	 * 
	 */
	public void newRun() {
		Semaphore waiter = new Semaphore(n - 1);

		List<Fork> forks = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			forks.add(new Fork(i));
		}
		for (int i = 0; i < n; i++) { 
			Fork left = forks.get(i); 
			Fork right = forks.get((i + 1) % n);

			philosophers.add(new Philosopher(waiter, THINK_TIME, EAT_TIME, left, right, i)); // создаЄтс¤

		}
		philosophers.forEach(Thread::start);
		ServiceUtil.waitMillis(WORK_TIME);
		philosophers.forEach(Thread::interrupt);
		ServiceUtil.waitMillis(WAIT_TIME);
	}
}
