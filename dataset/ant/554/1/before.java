class PlaceHold {
  private void doExecute(final Execute exe) throws TaskException {
    try {
      final int err = exe.execute();
      if (0 != err) {
        final String message = REZ.getString("exec.bad-resultcode.error", new Integer(err));
        throw new TaskException(message);
      }
    } catch (final IOException ioe) {
      final String message = REZ.getString("exec.failed.error", ioe);
      throw new TaskException(message, ioe);
    }
  }
}
