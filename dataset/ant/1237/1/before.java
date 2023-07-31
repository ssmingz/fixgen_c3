class PlaceHold {
  protected void setValue(
      final Object object, final String value, final Context context, final Method[] methods)
      throws ConfigurationException {
    try {
      final Object objectValue = PropertyUtil.resolveProperty(value, context, false);
      setValue(object, objectValue, methods);
    } catch (final PropertyException pe) {
      throw new ConfigurationException("Error resolving property " + value, pe);
    }
  }
}
