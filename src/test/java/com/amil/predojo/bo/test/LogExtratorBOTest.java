/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.bo.test;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.amil.predojo.bo.LogExtractorBO;
import com.amil.predojo.domain.Match;
import com.amil.predojo.domain.Player;

/**
 * @author Alisson
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LogExtratorBOTest {

	@Test
	public void extractorTest() throws FileNotFoundException {
		final LogExtractorBO logExtrator = new LogExtractorBO(new File(System.getProperty("user.dir") + "/log/11348965.log"));
		final Match match = logExtrator.extrator();

		Assert.assertTrue(match != null);
		Assert.assertEquals("11348965", match.getName());
		Assert.assertTrue(!match.getPlayers().isEmpty());

		final Player player1 = match.getPlayers().get(INTEGER_ZERO);
		Assert.assertEquals("Roman", player1.getName());
		Assert.assertEquals(0, player1.getDeaths());
		Assert.assertEquals(10, player1.getMurders());
		Assert.assertTrue(!player1.getWeapons().isEmpty());

		final Player player2 = match.getPlayers().get(INTEGER_ONE);
		Assert.assertEquals("Nick", player2.getName());
		Assert.assertEquals(11, player2.getDeaths());
		Assert.assertEquals(0, player2.getMurders());
		Assert.assertTrue(player2.getWeapons().isEmpty());
	}
}
