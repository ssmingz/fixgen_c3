class PlaceHold {
  public void configure(final Configuration configuration) throws ConfigurationException {
    final String[] attributes = configuration.getAttributeNames();
    for (int i = 0; i < attributes.length; i++) {
      final String name = attributes[i];
      final String value = configuration.getAttribute(name);
      configure(this, name, value);
    }
    final Configuration[] children = configuration.getChildren();
    for (int i = 0; i < children.length; i++) {
      try {
        final DataType value = ((DataType) (m_factory.create(children[i].getName())));
        configure(value, children[i]);
        setValue(value);
      } catch (final Exception e) {
        throw new ConfigurationException("Unable to set datatype", e);
      }
    }
  }
}
