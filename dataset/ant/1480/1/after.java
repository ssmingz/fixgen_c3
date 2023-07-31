class PlaceHold {
  public Iterator iterator() {
    if (isReference()) {
      return ((FileSet) (getRef(getProject()))).iterator();
    }
    return new FileResourceIterator(
        getProject(), getDir(getProject()), getDirectoryScanner(getProject()).getIncludedFiles());
  }
}
