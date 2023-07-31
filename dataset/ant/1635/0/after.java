class PlaceHold {
  public static void log(
      final TaskContext context,
      final LogLevel level,
      final String message,
      final Throwable throwable) {
    if (ERROR == level) {
      context.error(message, throwable);
    } else if (WARN == level) {
      context.warn(message, throwable);
    } else if (INFO == level) {
      context.info(message, throwable);
    } else if (VERBOSE == level) {
      context.verbose(message, throwable);
    } else {
      context.debug(message, throwable);
    }
  }
}
