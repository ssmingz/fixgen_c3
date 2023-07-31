class PlaceHold {
  private void addElement(
      final Object object,
      final Method method,
      final Configuration configuration,
      final Context context)
      throws ConfigurationException {
    try {
      final Class clazz = method.getParameterTypes()[0];
      final Object created = clazz.newInstance();
      configure(created, configuration, context);
      method.invoke(object, new Object[] {created});
    } catch (final ConfigurationException ce) {
      throw ce;
    } catch (final Exception e) {
      final String message = REZ.getString("subelement-create.error");
      throw new ConfigurationException(message, e);
    }
  }
}
