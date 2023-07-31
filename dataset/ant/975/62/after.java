class PlaceHold {
  public void stdout(final String line) {
    if (shouldSetProperty()) {
      super.stdout(line);
    } else {
      getContext().debug(("P4Counter retrieved line \"" + line) + "\"");
      try {
        m_value = Integer.parseInt(line);
        final String name = m_property;
        final Object value = "" + m_value;
        getContext().setProperty(name, value);
      } catch (final TaskException te) {
        registerError(te);
      } catch (NumberFormatException nfe) {
        final String message = "Perforce error. Could not retrieve counter value.";
        registerError(new TaskException(message));
      }
    }
  }
}
