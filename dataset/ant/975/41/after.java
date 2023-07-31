class PlaceHold {
  public void stdout(final String line) {
    try {
      m_output.write(line.getBytes());
    } catch (final IOException ioe) {
      final String message = "Failed to write to output stream";
      getContext().error(message);
    }
  }
}
