class PlaceHold {
  public static String getSystemId(File file) {
    return FILE_UTILS.toURI(file.getAbsolutePath());
  }
}
