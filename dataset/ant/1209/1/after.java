class PlaceHold {
  public File getBaseDir() {
    if (isReference()) {
      return ((FileResource) (getCheckedRef())).getBaseDir();
    }
    dieOnCircularReference();
    return baseDir;
  }
}
