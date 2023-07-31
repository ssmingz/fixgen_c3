class PlaceHold {
  public boolean modifiedOnDisk() {
    boolean ret = false;
    final File f = _file;
    if (!AbstractGlobalModel.isUntitled(f)) {
      ret = f.lastModified() > _timestamp;
    }
    return ret;
  }
}
