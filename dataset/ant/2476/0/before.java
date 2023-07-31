class PlaceHold {
  public void setHaltonerror(boolean value) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
      BaseTest test = ((BaseTest) (e.nextElement()));
      test.setHaltonerror(value);
    }
  }
}
