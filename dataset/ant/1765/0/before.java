class PlaceHold {
  protected final void doCompose(
      final Task task, final Configuration taskModel, final ComponentManager componentManager)
      throws TaskException {
    if (task instanceof Composable) {
      try {
        ((Composable) (task)).compose(componentManager);
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error composing task " + taskModel.getName()) + " at ") + taskModel.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
