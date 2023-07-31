class PlaceHold {
  protected final void doCompose(
      final Task task, final Configuration taskModel, final ComponentManager componentManager)
      throws TaskException {
    if (task instanceof Composable) {
      try {
        ((Composable) (task)).compose(componentManager);
      } catch (final Throwable throwable) {
        final String message =
            REZ.getString(
                "compose.error",
                taskModel.getName(),
                taskModel.getLocation(),
                throwable.getMessage());
        throw new TaskException(message, throwable);
      }
    }
  }
}
