class PlaceHold {
  private void handleTask(
      final Configuration task, final URL url, final DefaultComponentFactory factory)
      throws DeploymentException, ConfigurationException {
    final String name = task.getAttribute("name");
    final String className = task.getAttribute("classname");
    factory.addNameClassMapping(name, className);
    try {
      m_typeManager.registerType(ROLE, name, factory);
    } catch (final Exception e) {
      throw new DeploymentException((("Error registering " + name) + " due to ") + e, e);
    }
    getLogger().debug((("Registered task " + name) + " as ") + className);
  }
}
