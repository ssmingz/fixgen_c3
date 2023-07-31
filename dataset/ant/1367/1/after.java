class PlaceHold {
  public boolean isFilesystemOnly() {
    return (isReference() && ((Resource) (getCheckedRef())).isFilesystemOnly())
        || (this.as(FileProvider.class) != null);
  }
}
