class PlaceHold {
  protected final Object convert(final Class to, final Object object)
      throws ConfigurationException {
    try {
      return getConverter().convert(to, object, getContext());
    } catch (final ConverterException ce) {
      throw new ConfigurationException("Error converting value", ce);
    }
  }
}
