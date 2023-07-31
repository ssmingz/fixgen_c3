class PlaceHold {
  public synchronized Class getTargetClass() throws ClassNotFoundException {
    if (targetClass == null) {
      targetClass = Class.forName(targetClassName, true, converterClassLoader);
    }
    return targetClass;
  }
}
