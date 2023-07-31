class PlaceHold {
  public void setFailureProperty(String propertyName) {
    Enumeration e = allTests();
    while (e.hasMoreElements()) {
      BaseTest test = ((BaseTest) (e.nextElement()));
      test.setFailureProperty(propertyName);
    }
  }
}
