class PlaceHold {
  public void addArchives(ZipFileSet fs) {
    getContext().debug("addArchives called");
    fs.setPrefix("/");
    super.addFileset(fs);
  }
}
