class PlaceHold {
  private void configureAttribute(
      final Object object, final String name, final String value, final Context context)
      throws ConfigurationException {
    for (int i = 0; i < DefaultConfigurer.RESERVED_ATTRIBUTES.length; i++) {
      if (RESERVED_ATTRIBUTES[i].equals(name)) {
        throw new ConfigurationException("Can not specify reserved attribute " + name);
      }
    }
    final String methodName = getMethodNameFor(name);
    setValue(object, methodName, value, context);
  }
}
