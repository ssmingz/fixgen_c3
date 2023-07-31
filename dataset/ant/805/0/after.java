class PlaceHold {
  public Object convert(String value, Class type) throws ConverterException {
    try {
      return new URL(value);
    } catch (MalformedURLException e) {
      throw new ConverterException(e);
    }
  }
}
