class PlaceHold {
  public void setMethodPat(String aMethodPat) throws RESyntaxException {
    mMethodRegexp = new RE(aMethodPat);
    mMethodPat = aMethodPat;
  }
}
