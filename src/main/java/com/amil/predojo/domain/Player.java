/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Alisson
 *
 */
public class Player {

	private int death;
	private String name;
	private int murder;
	private int awards;
	private final List<Weapon> weapons = new ArrayList<Weapon>();
	private List<Calendar> lastMurderTimeArray = new ArrayList<Calendar>();
	private int streak;

	public Player(final String name) {
		this.name = name;
	}

	public Player(final String name, final Calendar lastMurderTime) {
		this.name = name;
		this.lastMurderTimeArray.add(lastMurderTime);
	}

	public Player(final String name, final Calendar lastMurderTime, final String weaponName) {
		this.name = name;
		this.lastMurderTimeArray.add(lastMurderTime);
		this.increaseWeaponsUsed(weaponName);
	}

	public int getDeaths() {
		return death;
	}

	public void addDeath() {
		this.death++;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMurders() {
		return murder;
	}

	public void addMurder() {
		this.murder++;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void increaseWeaponsUsed(final String weaponName) {
		if (!containsWeapon(weaponName)) {
			weapons.add(new Weapon(weaponName));
		}

		for (final Weapon weapon : weapons) {
			if (weapon.getName().equals(weaponName)) {
				weapon.addUsed();
				break;
			}
		}
	}

	private boolean containsWeapon(final String weaponName) {
		for (final Weapon gun : weapons) {
			if (gun.getName().equals(weaponName)) {
				return true;
			}
		}
		return false;
	}

	public int getAwards() {
		return awards;
	}

	public void addAwards() {
		this.awards++;
	}

	public int getStreak() {
		return streak;
	}

	public void addStreak() {
		this.streak++;
	}

	public void resetStreak() {
		this.streak = 0;
	}

	public void setLastMurderTime(final Calendar lastMurderTime) {
		this.lastMurderTimeArray.add(lastMurderTime);

		if (lastMurderTimeArray.size() >= 5) {
			final Calendar oneMinuteAgo = lastMurderTimeArray.get(lastMurderTimeArray.size() - 1);

			oneMinuteAgo.add(Calendar.MINUTE, -1);

			final Calendar fifthLastTime = lastMurderTimeArray.get(lastMurderTimeArray.size() - 5);

			if (oneMinuteAgo.before(fifthLastTime)) {
				this.addAwards();
				this.lastMurderTimeArray = new ArrayList<Calendar>();
			}
		}
	}
}
