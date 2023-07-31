class PlaceHold {
  public Iterator iterator() {
    if (isReference()) {
      return ((DirSet) (getRef(getProject()))).iterator();
    }
    return new FileResourceIterator(
        getProject(),
        getDir(getProject()),
        getDirectoryScanner(getProject()).getIncludedDirectories());
  }
}
