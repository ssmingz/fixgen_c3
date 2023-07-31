class PlaceHold {
  public void setOnCommentFormat(String aFormat) throws ConversionException {
    try {
      mOnRegexp = Utils.getRE(aFormat);
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
