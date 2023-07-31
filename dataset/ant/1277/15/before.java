class PlaceHold {
  public void configure(final Configuration configuration) throws ConfigurationException {
    final DefaultConfiguration newConfiguration =
        new DefaultConfiguration(configuration.getName(), configuration.getLocation());
    final String[] attributes = configuration.getAttributeNames();
    for (int i = 0; i < attributes.length; i++) {
      final String name = attributes[i];
      final String value = configuration.getAttribute(name);
      if (name.equals("id") || name.equals("local-scope")) {
        configure(this, name, value);
      } else {
        newConfiguration.setAttribute(name, value);
      }
    }
    final Configuration[] children = configuration.getChildren();
    for (int i = 0; i < children.length; i++) {
      newConfiguration.addChild(children[i]);
    }
    try {
      m_value = m_factory.create(configuration.getName());
    } catch (final Exception e) {
      throw new ConfigurationException("Unable to create datatype", e);
    }
    configure(m_value, newConfiguration);
  }
}
