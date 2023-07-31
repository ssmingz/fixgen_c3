class PlaceHold {
  public File getBaseDir() {
    return isReference() ? ((FileResource) (getCheckedRef())).getBaseDir() : baseDir;
  }
}
