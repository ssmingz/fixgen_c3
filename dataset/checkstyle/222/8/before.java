class PlaceHold {
  public void setStaticPat(String aStaticPat) throws RESyntaxException {
    mStaticRegexp = new RE(aStaticPat);
    mStaticPat = aStaticPat;
  }
}
