class PlaceHold {
  public void setFormat(String aFormat) throws ConversionException {
    try {
      mRegexp = Utils.getRE(aFormat);
      mFormat = aFormat;
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
