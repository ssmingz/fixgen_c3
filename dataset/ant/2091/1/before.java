class PlaceHold {
  public void setErrorProperty(String propertyName) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
      BaseTest test = ((BaseTest) (e.nextElement()));
      test.setErrorProperty(propertyName);
    }
  }
}
