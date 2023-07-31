class PlaceHold {
  private void handleTask(
      final Configuration task, final URL url, final DefaultComponentFactory factory)
      throws Exception {
    final String name = task.getAttribute("name");
    final String className = task.getAttribute("classname");
    factory.addNameClassMapping(name, className);
    m_typeManager.registerType(ROLE, name, factory);
    getLogger().debug((("Registered task " + name) + " as ") + className);
  }
}
