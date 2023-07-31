class PlaceHold {
  public URL getURL() throws DeploymentException {
    try {
      return m_file.getCanonicalFile().toURL();
    } catch (final IOException ioe) {
      throw new DeploymentException("Unable to form url", ioe);
    }
  }
}
