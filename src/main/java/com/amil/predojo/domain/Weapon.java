/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.domain;

/**
 * @author Alisson
 *
 */
public class Weapon {

	private String name;
	private int used = 1;

	public Weapon(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getUsed() {
		return used;
	}

	public void addUsed() {
		this.used++;
	}
}