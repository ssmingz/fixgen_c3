class PlaceHold {
  public File getFile() {
    if (isReference()) {
      return ((FileResource) (getCheckedRef())).getFile();
    }
    dieOnCircularReference();
    return file;
  }
}
