class PlaceHold {
  private void handleDataType(
      final Configuration datatype, final URL url, final DefaultComponentFactory factory)
      throws Exception {
    final String name = datatype.getAttribute("name");
    final String className = datatype.getAttribute("classname");
    factory.addNameClassMapping(name, className);
    m_typeManager.registerType("org.apache.ant.tasklet.DataType", name, factory);
    getLogger().debug((("Registered datatype " + name) + " as ") + className);
  }
}
