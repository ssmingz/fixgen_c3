class PlaceHold {
  public Reference getLoader() {
    if (isReference()) {
      return ((AbstractClasspathResource) (getCheckedRef())).getLoader();
    }
    dieOnCircularReference();
    return loader;
  }
}
