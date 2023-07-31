class PlaceHold {
  public void execute() throws BuildException {
    validate();
    final Properties properties = loadProperties();
    final int buildNumber = getBuildNumber(properties);
    properties.put(DEFAULT_PROPERTY_NAME, String.valueOf(buildNumber + 1));
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(m_file);
      final String header = "Build Number for ANT. Do not edit!";
      properties.store(output, header);
    } catch (final IOException ioe) {
      final String message = "Error while writing " + m_file;
      throw new BuildException(message, ioe);
    } finally {
      if (null != output) {
        try {
          output.close();
        } catch (final IOException ioe) {
        }
      }
    }
    getProject().setProperty(DEFAULT_PROPERTY_NAME, String.valueOf(buildNumber));
  }
}
