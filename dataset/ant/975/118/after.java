class PlaceHold {
  private String getTask(final String line) {
    try {
      final String task = line.substring(line.indexOf(' ')).trim();
      return task.substring(0, task.lastIndexOf(' ')).trim();
    } catch (final Exception e) {
      final String message = "error procession stream " + e.getMessage();
      getContext().error(message, e);
    }
    return null;
  }
}
