class PlaceHold {
  public void setTagFormat(String format) throws ConversionException {
    tagFormat = format;
    tagFormatRE = Utils.createPattern(format);
  }
}
