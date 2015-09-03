/*
 *
 * This file is a confidential property of Amil Test. No part of this
 * file may be reproduced or copied.
 *
 */
package com.amil.predojo.bo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.amil.predojo.bo.StatisticExtractorBO;
import com.amil.predojo.domain.Match;

/**
 * @author Alisson
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StatisticExtratorBOTest {

	@Mock
	Match matchMock;

	@InjectMocks
	private StatisticExtractorBO statisticExtratorBO;

	@Test
	public void extractorMockTest() {
		statisticExtratorBO.extrator();
		Mockito.verify(matchMock, Mockito.timeout(1)).getPlayers();
	}
}
