class PlaceHold {
  private void createElement(
      final Object object,
      final Method method,
      final Configuration configuration,
      final Context context)
      throws ConfigurationException {
    try {
      final Object created = method.invoke(object, new Object[0]);
      configure(created, configuration, context);
    } catch (final ConfigurationException ce) {
      throw ce;
    } catch (final Exception e) {
      final String message = REZ.getString("subelement-create.error");
      throw new ConfigurationException(message, e);
    }
  }
}
