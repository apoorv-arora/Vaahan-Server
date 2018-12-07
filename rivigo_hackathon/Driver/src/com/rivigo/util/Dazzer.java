package com.rivigo.util;

import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Dazzer implements ServletContextListener {

	public static int TRACKING_RUN_TIME = 10;

	private static ScheduledExecutorService userPointsService;
	private static Dazzer zappObject;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if (userPointsService != null) {
			userPointsService.shutdown();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
}
