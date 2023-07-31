class PlaceHold {
  public void setCheckFormat(String format) throws ConversionException {
    checkRegexp = Utils.createPattern(format);
    checkFormat = format;
  }
}
