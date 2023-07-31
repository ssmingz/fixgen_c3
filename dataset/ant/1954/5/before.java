class PlaceHold {
  public void exportPackages(
      File destDir,
      String[] includePatterns,
      String[] excludePatterns,
      boolean exportClasses,
      boolean exportDebugInfo,
      boolean exportResources,
      boolean exportSources,
      boolean useDefaultExcludes,
      boolean overwrite) {
    try {
      String request =
          (((((((((("http://" + remoteServer) + "/servlet/vajexport?")
                                              + VAJExportServlet.WITH_DEBUG_INFO)
                                          + "=")
                                      + exportDebugInfo)
                                  + "&")
                              + VAJExportServlet.OVERWRITE_PARAM)
                          + "=")
                      + overwrite)
                  + "&")
              + assembleImportExportParams(
                  destDir,
                  includePatterns,
                  excludePatterns,
                  exportClasses,
                  exportResources,
                  exportSources,
                  useDefaultExcludes);
      sendRequest(request);
    } catch (Exception ex) {
      throw new BuildException(ex);
    }
  }
}
