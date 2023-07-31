class PlaceHold {
  public synchronized Class getConverterClass() throws ClassNotFoundException {
    if (converterClass == null) {
      converterClass = converterClassLoader.loadClass(converterClassName);
    }
    return converterClass;
  }
}
