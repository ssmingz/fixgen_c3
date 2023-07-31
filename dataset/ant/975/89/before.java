class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final String message = "Building: " + m_zipFile.getAbsolutePath();
    getLogger().info(message);
    pack();
  }
}
