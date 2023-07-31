class PlaceHold {
  public boolean modifiedOnDisk() {
    boolean ret = false;
    final File f = _file;
    if (f != null) {
      ret = f.lastModified() > _timestamp;
    }
    return ret;
  }
}
