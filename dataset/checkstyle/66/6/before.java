class PlaceHold {
  public void setOffCommentFormat(String aFormat) throws ConversionException {
    try {
      mOffRegexp = Utils.getRE(aFormat);
    } catch (RESyntaxException e) {
      throw new ConversionException("unable to parse " + aFormat, e);
    }
  }
}
