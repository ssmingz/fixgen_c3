class PlaceHold {
  public File getSrc() {
    if (src != null) {
      FileProvider fp = ((FileProvider) (src.as(FileProvider.class)));
      if (fp != null) {
        return fp.getFile();
      }
    }
    return null;
  }
}
