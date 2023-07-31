class PlaceHold {
  public void copyFile(
      File sourceFile,
      File destFile,
      FilterSetCollection filters,
      Vector filterChains,
      boolean overwrite,
      boolean preserveLastModified,
      String inputEncoding,
      String outputEncoding,
      Project project)
      throws IOException {
    copyFile(
        sourceFile,
        destFile,
        filters,
        filterChains,
        overwrite,
        preserveLastModified,
        false,
        inputEncoding,
        outputEncoding,
        project);
  }
}
