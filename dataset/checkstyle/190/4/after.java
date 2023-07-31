class PlaceHold {
  public void setVersionFormat(String format) throws ConversionException {
    versionFormat = format;
    versionFormatPattern = Utils.createPattern(format);
  }
}
