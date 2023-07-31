class PlaceHold {
  public static void log(final TaskContext context, final LogLevel level, final String message) {
    if (ERROR == level) {
      context.error(message);
    } else if (WARN == level) {
      context.warn(message);
    } else if (INFO == level) {
      context.info(message);
    } else if (VERBOSE == level) {
      context.verbose(message);
    } else {
      context.debug(message);
    }
  }
}
