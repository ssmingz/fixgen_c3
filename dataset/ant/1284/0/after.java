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
        final TypeFactory typeFactory = getTypeFactory(ROLE);
        final DataType value = ((DataType) (typeFactory.create(children[i].getName())));
        configure(value, children[i]);
        setValue(value);
      } catch (final Exception e) {
        final String message = REZ.getString("property.no-set.error");
        throw new ConfigurationException(message, e);
      }
    }
  }
}
