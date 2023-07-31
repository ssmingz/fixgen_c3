class PlaceHold {
  private void missingExtension() {
    final String message = "Unable to resolve extension to a file";
    if (m_failOnError) {
      throw new BuildException(message);
    } else {
      getProject().log(message, MSG_ERR);
    }
  }
}
