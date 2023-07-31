class PlaceHold {
  protected final void doLoggable(
      final Task task, final Configuration taskModel, final Logger logger) throws TaskException {
    if (task instanceof Loggable) {
      try {
        ((Loggable) (task)).setLogger(logger);
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error setting logger for task " + taskModel.getName()) + " at ")
                            + taskModel.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
