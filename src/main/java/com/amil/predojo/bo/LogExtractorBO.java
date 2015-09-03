/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.bo;

import static com.amil.predojo.common.GamePatternConstant.KILLER_PATTERN;
import static com.amil.predojo.common.GamePatternConstant.START_PATTERN;
import static com.amil.predojo.common.GamePatternConstant.TIME_LOG_LINE_PATTERN;
import static com.amil.predojo.common.GamePatternConstant.WORLD_PATTERN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

import com.amil.predojo.domain.Match;
import com.amil.predojo.domain.Player;
import com.amil.predojo.util.DateTool;

/**
 * @author Alisson
 *
 */
public class LogExtractorBO {

	private final File gameLog;

	public LogExtractorBO(final File gameLog) {
		this.gameLog = gameLog;
	}

	public Match extrator() throws FileNotFoundException {

		final Scanner input = new Scanner(gameLog);
		Match match = null;
		final List<Player> players = new ArrayList<Player>();

		while (input.hasNextLine()) {

			final String line = input.nextLine();
			if (line.contains("has ended")) {
				break;
			}

			if (line.contains("has started")) {
				final Matcher m = START_PATTERN.matcher(line);
				if (m.find()) {
					match = new Match(m.group(1));
				}
				continue;
			}

			if (line.contains("<WORLD> killed")) {
				final Matcher m = WORLD_PATTERN.matcher(line);
				if (m.find()) {
					processDeadPlayer(m.group(1), players);
				}
				continue;
			}

			if (line.contains("using")) {
				final Matcher m = KILLER_PATTERN.matcher(line);
				if (m.find()) {
					processPlayers(m.group(2), m.group(3), m.group(1), m.group(4), players);
				}
				continue;
			}
		}
		input.close();
		match.setPlayers(players);

		return match;
	}

	private void processDeadPlayer(final String murderedName, final List<Player> players) {

		addNewPlayer(murderedName, players);

		for (final Player deadPlayer : players) {

			if (increaseMurderedPlayer(murderedName, deadPlayer))
				break;
		}
	}

	/**
	 * Insere nova morte para jogador.
	 * 
	 * @param murderedName
	 *            nome do jogador morto
	 * @param deadPlayer
	 *            jogador morto
	 * @return true se inserido nova morte do jogador ou false caso contrario
	 *
	 */
	private boolean increaseMurderedPlayer(final String murderedName, final Player deadPlayer) {
		if (murderedName.equals(deadPlayer.getName())) {
			deadPlayer.addDeath();
			deadPlayer.resetStreak();
			return true;
		}
		return false;
	}

	private void processPlayers(final String killerName, final String murderedName, final String murderTime, final String gunName, final List<Player> players) {

		addNewPlayer(killerName, players);
		addNewPlayer(murderedName, players);

		for (final Player player : players) {
			increaseKillerPlayer(killerName, murderTime, gunName, player);

			if (increaseMurderedPlayer(murderedName, player))
				break;
		}
	}

	private void increaseKillerPlayer(final String killerName, final String murderTime, final String gunName, final Player player) {
		if (killerName.equals(player.getName())) {
			player.addMurder();
			player.increaseWeaponsUsed(gunName);
			player.setLastMurderTime(DateTool.parseStringToCalendar(murderTime, TIME_LOG_LINE_PATTERN));
			player.addStreak();
		}
	}

	private void addNewPlayer(final String playerName, final List<Player> players) {
		if (!containsPlayer(players, playerName)) {
			players.add(new Player(playerName));
		}
	}

	private boolean containsPlayer(final List<Player> players, final String name) {
		for (final Player player : players) {
			if (player.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
