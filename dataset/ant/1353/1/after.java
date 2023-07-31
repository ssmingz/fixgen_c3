class PlaceHold {
  public synchronized Iterator iterator() {
    if (isReference()) {
      return getRef().iterator();
    }
    ensureDirectoryScannerSetup();
    ds.scan();
    int fct = ds.getIncludedFilesCount();
    int dct = ds.getIncludedDirsCount();
    if ((fct + dct) == 0) {
      return EMPTY_ITERATOR;
    }
    FileResourceIterator result = new FileResourceIterator(getProject());
    if (fct > 0) {
      result.addFiles(ds.getIncludedFiles());
    }
    if (dct > 0) {
      result.addFiles(ds.getIncludedDirectories());
    }
    return result;
  }
}
