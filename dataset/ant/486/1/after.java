class PlaceHold {
  private void setValue(
      final Object object, final String value, final Context context, final Method[] methods)
      throws ConfigurationException {
    try {
      final Object objectValue = PropertyUtil.resolveProperty(value, context, false);
      setValue(object, objectValue, methods, context);
    } catch (final PropertyException pe) {
      final String message = REZ.getString("bad-property-resolve.error", value);
      throw new ConfigurationException(message, pe);
    }
  }
}
