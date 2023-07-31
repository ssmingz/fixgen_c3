class PlaceHold {
  private void doCompose(final Task task, final Configuration taskData) throws TaskException {
    if (task instanceof Composable) {
      try {
        ((Composable) (task)).compose(m_componentManager);
      } catch (final Throwable throwable) {
        throw new TaskException(
            ((((("Error composing task " + taskData.getName()) + " at ") + taskData.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
