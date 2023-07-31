class PlaceHold {
  private void configureElement(
      final Object object, final Configuration configuration, final Context context)
      throws ConfigurationException {
    final String name = configuration.getName();
    for (int i = 0; i < DefaultConfigurer.RESERVED_ELEMENTS.length; i++) {
      if (RESERVED_ATTRIBUTES[i].equals(name)) {
        return;
      }
    }
    final String javaName = getJavaNameFor(name);
    final Class clazz = object.getClass();
    Method methods[] = getMethodsFor(clazz, "add" + javaName);
    if (0 != methods.length) {
      addElement(object, methods[0], configuration, context);
    } else {
      methods = getCreateMethodsFor(clazz, "create" + javaName);
      if (0 == methods.length) {
        final String message = REZ.getString("no-element-method.error", javaName);
        throw new ConfigurationException(message);
      }
      createElement(object, methods[0], configuration, context);
    }
  }
}
