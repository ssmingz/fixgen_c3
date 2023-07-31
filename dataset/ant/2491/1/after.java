class PlaceHold {
  public URL getURL() throws DeploymentException {
    try {
      return m_file.getCanonicalFile().toURL();
    } catch (final IOException ioe) {
      final String message = REZ.getString("bad-url.error", m_file);
      throw new DeploymentException(message, ioe);
    }
  }
}
