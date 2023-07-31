class PlaceHold {
  public void setFiltertrace(boolean value) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
      BaseTest test = ((BaseTest) (e.nextElement()));
      test.setFiltertrace(value);
    }
  }
}
