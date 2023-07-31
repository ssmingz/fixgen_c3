class PlaceHold {
  public static void log(
      final TaskContext context,
      final LogLevel level,
      final String message,
      final Throwable throwable) {
    if (LogLevel.ERROR == level) {
      context.error(message, throwable);
    } else if (LogLevel.WARN == level) {
      context.warn(message, throwable);
    } else if (LogLevel.INFO == level) {
      context.info(message, throwable);
    } else {
      context.debug(message, throwable);
    }
  }
}
