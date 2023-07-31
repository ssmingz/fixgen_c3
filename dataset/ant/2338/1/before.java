class PlaceHold {
  protected final void doInitialize(final Task task, final Configuration taskModel)
      throws TaskException {
    if (task instanceof Initializable) {
      try {
        ((Initializable) (task)).initialize();
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error initializing task " + taskModel.getName()) + " at ")
                            + taskModel.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
