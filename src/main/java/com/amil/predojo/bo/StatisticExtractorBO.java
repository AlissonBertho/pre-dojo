/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.bo;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.amil.predojo.domain.Match;
import com.amil.predojo.domain.Player;
import com.amil.predojo.domain.Weapon;

/**
 * @author Alisson
 *
 */
public class StatisticExtractorBO {

	Match match;

	public StatisticExtractorBO(final Match match) {
		this.match = match;
	}

	public void extrator() {

		System.out.println(EMPTY);
		System.out.println("##################################################### Ranking Geral #####################################################");
		System.out.println(String.format("Partida %s", match.getName()));
		System.out.println(EMPTY);
		int i = 1;

		for (final Player player : match.getPlayers()) {
			System.out.println(String.format("Jogador................: %d", i++));
			System.out.println(String.format("Nome...................: %s", player.getName()));
			System.out.println(String.format("Assassinatos realizados: %d", player.getMurders()));
			System.out.println(String.format("Mortes sofridas........: %d", player.getDeaths()));
			System.out.println(String.format("Arma preferida.........: %s", getFavoriteWeapon(player)));
			System.out.println(String.format("Streak.................: %d", player.getStreak()));
			System.out.println(String.format("Award..................: %d", player.getDeaths() > INTEGER_ZERO ? player.getAwards() : (player.getAwards() + INTEGER_ONE)));
			System.out.println(EMPTY);
		}
		System.out.println("#########################################################################################################################");
	}

	private String getFavoriteWeapon(final Player player) {

		String weaponName = "Nenhuma arma utilizada na partida.";
		int amount = 0;
		for (final Weapon weapon : player.getWeapons()) {

			if (amount == weapon.getUsed()) {
				weaponName = weaponName + ", " + weapon.getName();
			} else if (weapon.getUsed() > amount) {
				weaponName = weapon.getName();
				amount = weapon.getUsed();
			}
		}
		return weaponName;
	}
}
