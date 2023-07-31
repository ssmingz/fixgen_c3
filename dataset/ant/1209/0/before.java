class PlaceHold {
  public File getFile() {
    return isReference() ? ((FileResource) (getCheckedRef())).getFile() : file;
  }
}
