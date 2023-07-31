class PlaceHold {
  public void setParamPat(String aParamPat) throws RESyntaxException {
    mParamRegexp = new RE(aParamPat);
    mParamPat = aParamPat;
  }
}
