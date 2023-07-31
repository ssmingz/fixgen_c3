class PlaceHold {
  public boolean isFilesystemOnly() {
    if (isReference()) {
      return ((ArchiveFileSet) (getCheckedRef())).isFilesystemOnly();
    }
    dieOnCircularReference();
    return src == null;
  }
}
