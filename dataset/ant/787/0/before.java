class PlaceHold {
  protected void doCompose(final Task task, final Configuration taskData) throws AntException {
    if (task instanceof Composable) {
      try {
        ((Composable) (task)).compose(m_componentManager);
      } catch (final Throwable throwable) {
        throw new AntException(
            ((((("Error composing task " + taskData.getName()) + " at ") + taskData.getLocation())
                        + "(Reason: ")
                    + throwable.getMessage())
                + ")",
            throwable);
      }
    }
  }
}
