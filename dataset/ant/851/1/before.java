class PlaceHold {
  private Logger createLogger(final String logLevel) throws Exception {
    final String logLevelCapitalized = logLevel.toUpperCase();
    final Priority priority = Priority.getPriorityForName(logLevelCapitalized);
    if (!priority.getName().equals(logLevelCapitalized)) {
      throw new Exception("Unknown log level - " + logLevel);
    }
    final Logger logger = Hierarchy.getDefaultHierarchy().getLoggerFor("myrmidon");
    final DefaultOutputLogTarget target = new DefaultOutputLogTarget();
    target.setFormat("[%8.8{category}] %{message}\\n%{throwable}");
    logger.setLogTargets(new LogTarget[] {target});
    logger.setPriority(priority);
    return logger;
  }
}
