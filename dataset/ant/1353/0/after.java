class PlaceHold {
  public Iterator iterator() {
    if (isReference()) {
      return ((FileSet) (getRef(getProject()))).iterator();
    }
    FileResourceIterator result = new FileResourceIterator(getProject(), getDir());
    result.addFiles(getDirectoryScanner().getIncludedFiles());
    result.addFiles(getDirectoryScanner().getIncludedDirectories());
    return result;
  }
}
