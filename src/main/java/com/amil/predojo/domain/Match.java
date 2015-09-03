/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alisson
 *
 */
public class Match {
	
	public Match(String name) {
		this.name = name;
		this.players = new ArrayList<Player>();
	}

	private String name;
	private List<Player> players;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
