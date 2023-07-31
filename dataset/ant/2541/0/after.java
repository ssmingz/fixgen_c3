class PlaceHold {
  public BaseFilterReader() {
    super(new StringReader(""));
    FileUtils.close(this);
  }
}
