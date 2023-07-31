class PlaceHold {
  public boolean isFilesystemOnly() {
    return (isReference() && ((Resource) (getCheckedRef())).isFilesystemOnly())
        || (this instanceof FileProvider);
  }
}
