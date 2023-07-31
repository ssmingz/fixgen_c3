class PlaceHold {
  public void configure(final Configuration configuration) throws ConfigurationException {
    final String[] attributes = configuration.getAttributeNames();
    for (int i = 0; i < attributes.length; i++) {
      final String name = attributes[i];
      final String value = configuration.getAttribute(name);
      configureAttribute(this, name, value);
    }
    final Configuration[] children = configuration.getChildren();
    if (1 == children.length) {
      final String typeName = children[0].getName();
      try {
        m_aspectHandler = ((AspectHandler) (newInstance(AspectHandler.class, typeName)));
      } catch (final Exception e) {
        final String message = REZ.getString("facility.no-create.error", typeName);
        throw new ConfigurationException(message, e);
      }
      configureElement(m_aspectHandler, children[0]);
    } else {
      final String message = REZ.getString("facility.multi-element.error");
      throw new ConfigurationException(message);
    }
  }
}
