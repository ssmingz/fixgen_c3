class PlaceHold {
  public boolean isDirectory() {
    if (isReference()) {
      return ((Resource) (getCheckedRef())).isDirectory();
    }
    return (directory != null) && directory.booleanValue();
  }
}
