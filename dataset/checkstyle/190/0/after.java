class PlaceHold {
  public void setAuthorFormat(String format) throws ConversionException {
    authorFormat = format;
    authorFormatPattern = Utils.createPattern(format);
  }
}
