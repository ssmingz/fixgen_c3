class PlaceHold {
  public boolean isIgnored() {
    return fMethod.getAnnotation(Ignore.class) != null;
  }
}
