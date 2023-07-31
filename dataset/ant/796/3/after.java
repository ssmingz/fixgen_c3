class PlaceHold {
  public Object convert(final Object original, final Context context) throws ConverterException {
    try {
      return new Long(((String) (original)));
    } catch (final NumberFormatException nfe) {
      throw new ConverterException("Error formatting object", nfe);
    }
  }
}
