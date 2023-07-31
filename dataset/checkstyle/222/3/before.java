class PlaceHold {
  public void setTypePat(String aTypePat) throws RESyntaxException {
    mTypeRegexp = new RE(aTypePat);
    mTypePat = aTypePat;
  }
}
