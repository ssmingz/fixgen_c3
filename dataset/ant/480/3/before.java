class PlaceHold {
  public static String getSystemId(File file) {
    return fu.toURI(file.getAbsolutePath());
  }
}
