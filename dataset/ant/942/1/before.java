class PlaceHold {
  public synchronized void add(FileSet fs) {
    filesets = (filesets == null) ? new Vector() : filesets;
    filesets.add(fs);
  }
}
