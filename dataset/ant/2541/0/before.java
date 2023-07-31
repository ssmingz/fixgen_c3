class PlaceHold {
  public BaseFilterReader() {
    super(new StringReader(new String()));
    FileUtils.close(this);
  }
}
