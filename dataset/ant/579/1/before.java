class PlaceHold {
  protected final void doDispose(final Task task, final Configuration taskModel)
      throws TaskException {
    if (task instanceof Disposable) {
      try {
        ((Disposable) (task)).dispose();
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error disposing task " + taskModel.getName()) + " at ") + taskModel.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
