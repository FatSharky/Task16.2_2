package by.training.task16.domain;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Fork
 * 
 * @author Gapeenko Vlad
 */
public class Fork {
	/**
	 * Lock, in one particular time fork can only belong to one philosopher
	 */
	private final Lock lock = new ReentrantLock();

	private int id;

	public Fork() {
	}

	public Fork(int id) {
		this.id = id;
	}

	/**
	 * Method take: where fork is considered taken, if blocked flow
	 */
	public void take() {
		lock.lock();
	}

	/**
	 * Method put: where fork is considered put, if unblocked flow
	 */
	public void put() {
		lock.unlock();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
