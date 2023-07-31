class PlaceHold {
  private void configureAttribute(
      final Object object, final String name, final String value, final Context context)
      throws ConfigurationException {
    for (int i = 0; i < DefaultConfigurer.RESERVED_ATTRIBUTES.length; i++) {
      if (RESERVED_ATTRIBUTES[i].equals(name)) {
        final String message = REZ.getString("reserved-attribute.error", name);
        throw new ConfigurationException(message);
      }
    }
    final String methodName = getMethodNameFor(name);
    setValue(object, methodName, value, context);
  }
}
