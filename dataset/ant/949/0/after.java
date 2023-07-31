class PlaceHold {
  public void copyFile(String sourceFile, String destFile, FilterSetCollection filters)
      throws IOException, TaskException {
    copyFile(new File(sourceFile), new File(destFile), filters, false, false);
  }
}
