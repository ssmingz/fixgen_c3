class PlaceHold {
  public void setFork(boolean value) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
      BaseTest test = ((BaseTest) (e.nextElement()));
      test.setFork(value);
    }
  }
}
