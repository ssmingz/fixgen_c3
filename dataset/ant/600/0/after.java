class PlaceHold {
  protected final void doLoggable(
      final Task task, final Configuration taskModel, final Logger logger) throws TaskException {
    if (task instanceof Loggable) {
      try {
        ((Loggable) (task)).setLogger(logger);
      } catch (final Throwable throwable) {
        final String message =
            REZ.getString(
                "logger.error",
                taskModel.getName(),
                taskModel.getLocation(),
                throwable.getMessage());
        throw new TaskException(message, throwable);
      }
    }
  }
}
