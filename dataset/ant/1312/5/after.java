class PlaceHold {
  public FileObject findLocalFile(final String name) throws FileSystemException {
    return findFile(null, "file:" + name);
  }
}
