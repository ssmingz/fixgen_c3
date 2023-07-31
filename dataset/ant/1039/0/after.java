class PlaceHold {
  public synchronized Class getConverterClass() throws ClassNotFoundException {
    if (converterClass == null) {
      converterClass = Class.forName(converterClassName, true, converterClassLoader);
    }
    return converterClass;
  }
}
