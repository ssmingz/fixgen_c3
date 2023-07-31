class PlaceHold {
  protected final Object convert(final Class to, final Object object)
      throws ConfigurationException {
    try {
      return getConverter().convert(to, object, getContext());
    } catch (final ConverterException ce) {
      final String message = REZ.getString("container.bad-config.error");
      throw new ConfigurationException(message, ce);
    }
  }
}
