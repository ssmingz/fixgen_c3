class PlaceHold {
  protected void doDispose(final Task task, final Configuration taskData) throws AntException {
    if (task instanceof Disposable) {
      try {
        ((Disposable) (task)).dispose();
      } catch (final Throwable throwable) {
        throw new AntException(
            ((((("Error disposing task " + taskData.getName()) + " at ") + taskData.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
