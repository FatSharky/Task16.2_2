package by.training.task16.service;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.task16.domain.Fork;
import by.training.task16.service.util.ServiceUtil;

/**
 * Ñlass Philoshopher extends Thread
 * 
 * @author Vladislav
 *
 */
public class Philosopher extends Thread {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	/**
	 * Semaphore referred to by the philosopher
	 */
	private final Semaphore waiter;

	private final long eatTime;
	private final long thinkTime;
	private final Fork fork1;
	private final Fork fork2;
	private int id;

	public Philosopher(Semaphore waiter, long eatTime, long thinkTime, Fork fork1, Fork fork2, int id) {
		this.waiter = waiter;
		this.eatTime = eatTime;
		this.thinkTime = thinkTime;
		this.fork1 = fork1;
		this.fork2 = fork2;
		this.id = id;
	}

	/**
	 * while main flow not interrupt philosopher philosopher wait until
	 * semaphore give permission to eat
	 */
	@Override
	public void run() {
		logger.info("Philosopher " + id + " sat down");
		while (!this.isInterrupted()) {
			try {
				waiter.acquire();

				takeFork(fork1);
				takeFork(fork2);
				eat();
				putFork(fork1);
				putFork(fork2);
				/*
				 * Cath this exception occurs if while waiting for the semaphore
				 * capture the main thread wants to interrupt the philosopher,
				 * but instead an exception (throw it acquire () method)
				 */
			} catch (InterruptedException e) {

				this.interrupt();
			} finally {
				waiter.release();
				think();
			}
		}
		logger.info("Philosopher " + id + " run away");
	}

	private void takeFork(Fork fork) {
		fork.take();
		logger.info("Philosopher " + id + " take fork " + fork.getId());
	}

	private void putFork(Fork fork) {
		fork.put();
		logger.info("Philosopher " + id + " put fork " + fork.getId());
	}

	private void eat() {
		ServiceUtil.waitMillis(eatTime);
		logger.info("Philosopher " + id + " eat");
	}

	private void think() {
		ServiceUtil.waitMillis(thinkTime);
		logger.info("Philosopher " + id + " thinks about eternity");
	}
}
