class PlaceHold {
  public void run() throws AntException {
    if (null == m_lib) {
      throw new AntException("Must specify lib parameter");
    }
    URL url = null;
    final File lib = new File(getContext().resolveFilename(m_lib));
    try {
      url = lib.toURL();
    } catch (final MalformedURLException mue) {
      throw new AntException("Malformed task-lib parameter " + m_lib, mue);
    }
    try {
      m_engine.getTskDeployer().deploy(url.toString(), url);
    } catch (final DeploymentException de) {
      throw new AntException("Error registering resource", de);
    }
  }
}
