class PlaceHold {
  public void addArchives(ZipFileSet fs) {
    log("addArchives called", MSG_DEBUG);
    fs.setPrefix("/");
    super.addFileset(fs);
  }
}
