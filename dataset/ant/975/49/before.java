class PlaceHold {
  public void addArchives(ZipFileSet fs) {
    getLogger().debug("addArchives called");
    fs.setPrefix("/");
    super.addFileset(fs);
  }
}
