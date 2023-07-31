class PlaceHold {
  private String guessTypeFor(final String location) {
    return FileUtil.getExtension(location);
  }
}
