package ru.hh.univitrina.config;


import io.sentry.logback.SentryAppender;
import ru.hh.nab.logging.HhMultiAppender;
import ru.hh.nab.starter.NabLogbackBaseConfigurator;

public class LogbackConfigurator extends NabLogbackBaseConfigurator {
  @Override
  public final void configure(LoggingContextWrapper context, HhMultiAppender service, HhMultiAppender libraries, SentryAppender sentryAppender) {
    //createLogger(context, Classname.class, Level.ERROR, false, service);
  }
}
