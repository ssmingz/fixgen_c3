class PlaceHold {
  protected final void doDispose(final Task task, final Configuration taskModel)
      throws TaskException {
    if (task instanceof Disposable) {
      try {
        ((Disposable) (task)).dispose();
      } catch (final Throwable throwable) {
        final String message =
            REZ.getString(
                "dispose.error",
                taskModel.getName(),
                taskModel.getLocation(),
                throwable.getMessage());
        throw new TaskException(message, throwable);
      }
    }
  }
}
