class PlaceHold {
  private void handleDataType(
      final Configuration datatype, final URL url, final DefaultComponentFactory factory)
      throws DeploymentException, ConfigurationException {
    final String name = datatype.getAttribute("name");
    final String className = datatype.getAttribute("classname");
    factory.addNameClassMapping(name, className);
    try {
      m_typeManager.registerType("org.apache.ant.tasklet.DataType", name, factory);
    } catch (final Exception e) {
      throw new DeploymentException((("Error registering " + name) + " due to ") + e, e);
    }
    getLogger().debug((("Registered datatype " + name) + " as ") + className);
  }
}
