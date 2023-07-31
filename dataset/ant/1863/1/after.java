class PlaceHold {
  private void setValue(
      final Object object, final String methodName, final String value, final Context context)
      throws ConfigurationException {
    final Class clazz = object.getClass();
    final Method[] methods = getMethodsFor(clazz, methodName);
    if (0 == methods.length) {
      final String message = REZ.getString("no-attribute-method.error", methodName);
      throw new ConfigurationException(message);
    }
    setValue(object, value, context, methods);
  }
}
