class PlaceHold {
  public File getSrc() {
    if (isReference()) {
      return ((ArchiveFileSet) (getCheckedRef())).getSrc();
    }
    dieOnCircularReference();
    if (src != null) {
      FileProvider fp = ((FileProvider) (src.as(FileProvider.class)));
      if (fp != null) {
        return fp.getFile();
      }
    }
    return null;
  }
}
