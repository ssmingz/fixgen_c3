class PlaceHold {
  public synchronized Class getTargetClass() throws ClassNotFoundException {
    if (targetClass == null) {
      targetClass = converterClassLoader.loadClass(targetClassName);
    }
    return targetClass;
  }
}
