class PlaceHold {
  public void setStaticFinalPat(String aStaticFinalPat) throws RESyntaxException {
    mStaticFinalRegexp = new RE(aStaticFinalPat);
    mStaticFinalPat = aStaticFinalPat;
  }
}
