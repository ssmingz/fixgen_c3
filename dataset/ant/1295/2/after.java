class PlaceHold {
  public void importFiles(
      String importProject,
      File srcDir,
      String[] includePatterns,
      String[] excludePatterns,
      boolean importClasses,
      boolean importResources,
      boolean importSources,
      boolean useDefaultExcludes) {
    try {
      String request =
          (((((("http://" + remoteServer) + "/servlet/vajimport?")
                              + VAJImportServlet.PROJECT_NAME_PARAM)
                          + "=")
                      + importProject)
                  + "&")
              + assembleImportExportParams(
                  srcDir,
                  includePatterns,
                  excludePatterns,
                  importClasses,
                  importResources,
                  importSources,
                  useDefaultExcludes);
      sendRequest(request);
    } catch (Exception ex) {
      throw new BuildException("Error", ex);
    }
  }
}
