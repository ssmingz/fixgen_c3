class PlaceHold {
  public void copyFile(
      File sourceFile,
      File destFile,
      FilterSetCollection filters,
      Vector filterChains,
      boolean overwrite,
      boolean preserveLastModified,
      boolean append,
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
        append,
        inputEncoding,
        outputEncoding,
        project,
        false);
  }
}
