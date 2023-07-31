class PlaceHold {
  protected void doSendString(
      final OutputStream output, final String string, final boolean echoString)
      throws TaskException {
    try {
      output.write((string + "\n").getBytes());
      if (echoString) {
        getLogger().info(string);
      }
      output.flush();
    } catch (final Exception e) {
      throw new TaskException(e.getMessage(), e);
    }
  }
}
