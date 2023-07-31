class PlaceHold {
  protected final void doInitialize(final Task task, final Configuration taskModel)
      throws TaskException {
    if (task instanceof Initializable) {
      try {
        ((Initializable) (task)).initialize();
      } catch (final Throwable throwable) {
        final String message =
            REZ.getString(
                "init.error", taskModel.getName(), taskModel.getLocation(), throwable.getMessage());
        throw new TaskException(message, throwable);
      }
    }
  }
}
