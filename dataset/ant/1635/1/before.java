class PlaceHold {
  public static void log(final TaskContext context, final LogLevel level, final String message) {
    if (LogLevel.ERROR == level) {
      context.error(message);
    } else if (LogLevel.WARN == level) {
      context.warn(message);
    } else if (LogLevel.INFO == level) {
      context.info(message);
    } else {
      context.debug(message);
    }
  }
}
