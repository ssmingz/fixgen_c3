class PlaceHold {
  public void add(int aLineNo, String aKey, Object[] aArgs) {
    add(new LocalizedMessage(aLineNo, 0, OLD_BUNDLE, aKey, aArgs));
  }
}
