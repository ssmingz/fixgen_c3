class PlaceHold {
  public Iterator iterator() {
    if (isReference()) {
      return ((ResourceCollection) (getRef(getProject()))).iterator();
    }
    if (src == null) {
      return super.iterator();
    }
    ArchiveScanner as = ((ArchiveScanner) (getDirectoryScanner(getProject())));
    return as.getResourceFiles(getProject());
  }
}
