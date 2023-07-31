class PlaceHold {
  private void setValue(
      final Object object, final String methodName, final String value, final Context context)
      throws ConfigurationException {
    final Class clazz = object.getClass();
    final Method methods[] = getMethodsFor(clazz, methodName);
    if (0 == methods.length) {
      throw new ConfigurationException(
          (("Unable to set attribute via " + methodName) + " due to not finding any appropriate ")
              + "accessor method");
    }
    setValue(object, value, context, methods);
  }
}
