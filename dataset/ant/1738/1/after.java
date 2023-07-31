class PlaceHold {
  public void execute() throws BuildException {
    File savedFile = myFile;
    validate();
    final Properties properties = loadProperties();
    final int buildNumber = getBuildNumber(properties);
    properties.put(DEFAULT_PROPERTY_NAME, String.valueOf(buildNumber + 1));
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(myFile);
      final String header = "Build Number for ANT. Do not edit!";
      properties.store(output, header);
    } catch (final IOException ioe) {
      final String message = "Error while writing " + myFile;
      throw new BuildException(message, ioe);
    } finally {
      if (null != output) {
        try {
          output.close();
        } catch (final IOException ioe) {
          getProject().log("error closing output stream " + ioe, MSG_ERR);
        }
      }
      myFile = savedFile;
    }
    getProject().setNewProperty(DEFAULT_PROPERTY_NAME, String.valueOf(buildNumber));
  }
}
