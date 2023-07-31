class PlaceHold {
  public Object convert(final Object original, final Context context) throws ConverterException {
    try {
      return new URL(((String) (original)));
    } catch (final MalformedURLException mue) {
      throw new ConverterException("Error formatting object", mue);
    }
  }
}
