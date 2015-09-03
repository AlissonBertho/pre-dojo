/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.amil.predojo.bo.LogExtractorBO;
import com.amil.predojo.bo.StatisticExtractorBO;
import com.amil.predojo.domain.Match;

/**
 * @author Alisson
 *
 */
public class App {

	final static String MSG_INFORM_PATH = "Digite ctrl+z para sair ou informe o pathname do arquivo de log da partida: ";
	final static String MSG_ERROR_PATH = "Pathname inv\u00e1lido!";

	@SuppressWarnings("resource")
	public static void main(final String[] args) {

		System.out.println(MSG_INFORM_PATH);
		Scanner sc = new Scanner(System.in);
		String text;

		while (sc.hasNext()) {
			text = sc.next();

			File gameLog = new File(text);

			while (!gameLog.exists()) {
				System.out.println(String.format(MSG_ERROR_PATH));
				System.out.println(MSG_INFORM_PATH);
				sc = new Scanner(System.in);

				text = sc.next();
				gameLog = new File(text);
			}

			try {
				processLog(gameLog);
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static void processLog(final File gameLog) throws FileNotFoundException {

		final LogExtractorBO logExtrator = new LogExtractorBO(gameLog);
		final Match match = logExtrator.extrator();
		final StatisticExtractorBO statistic = new StatisticExtractorBO(match);
		statistic.extrator();
	}
}
