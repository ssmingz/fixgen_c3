class PlaceHold {
  public synchronized void add(FileSet fs) {
    if (fs == null) {
      return;
    }
    filesets = (filesets == null) ? new Vector() : filesets;
    filesets.add(fs);
  }
}
