class PlaceHold {
  public void copyFile(String sourceFile, String destFile, FilterSetCollection filters)
      throws IOException {
    copyFile(new File(sourceFile), new File(destFile), filters, false, false);
  }
}
