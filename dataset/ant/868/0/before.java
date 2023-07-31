class PlaceHold {
  protected void registerResource(final String name, final String classname, final URL url)
      throws AntException, RegistryException {
    if (null == classname) {
      try {
        m_engine.getTskDeployer().deployTasklet(name, url.toString(), url);
      } catch (final DeploymentException de) {
        throw new AntException((("Failed deploying " + name) + " from ") + url, de);
      }
    } else {
      final DefaultLocator locator = new DefaultLocator(classname, url);
      m_engine.getRegistry().register(name, locator);
    }
  }
}
