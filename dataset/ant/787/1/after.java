class PlaceHold {
  private void doDispose(final Task task, final Configuration taskData) throws TaskException {
    if (task instanceof Disposable) {
      try {
        ((Disposable) (task)).dispose();
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error disposing task " + taskData.getName()) + " at ") + taskData.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
