class PlaceHold {
  public void setTodoPat(String aTodoPat) throws RESyntaxException {
    mTodoRegexp = new RE(aTodoPat);
    mTodoPat = aTodoPat;
  }
}
