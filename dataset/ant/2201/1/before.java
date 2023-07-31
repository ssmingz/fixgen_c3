class PlaceHold {
  private BuildLogger createLogger() {
    BuildLogger logger = null;
    if (loggerClassname != null) {
      try {
        logger = ((BuildLogger) (Class.forName(loggerClassname).newInstance()));
      } catch (ClassCastException e) {
        System.err.println(
            ("The specified logger class " + loggerClassname)
                + " does not implement the BuildLogger interface");
        throw new RuntimeException();
      } catch (Exception e) {
        System.err.println(
            (("Unable to instantiate specified logger class " + loggerClassname) + " : ")
                + e.getClass().getName());
        throw new RuntimeException();
      }
    } else {
      logger = new DefaultLogger();
    }
    logger.setMessageOutputLevel(msgOutputLevel);
    logger.setOutputPrintStream(out);
    logger.setEmacsMode(emacsMode);
    return logger;
  }
}
