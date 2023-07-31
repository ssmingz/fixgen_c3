class PlaceHold {
  protected void importFileset(FileSet fileset) {
    DirectoryScanner ds = fileset.getDirectoryScanner();
    if (ds.getIncludedFiles().length == 0) {
      return;
    }
    String[] includes = null;
    String[] excludes = null;
    try {
      Class directoryScanner = ds.getClass();
      Field includesField = directoryScanner.getDeclaredField("includes");
      includesField.setAccessible(true);
      includes = ((String[]) (includesField.get(ds)));
      Field excludesField = directoryScanner.getDeclaredField("excludes");
      excludesField.setAccessible(true);
      excludes = ((String[]) (excludesField.get(ds)));
    } catch (NoSuchFieldException nsfe) {
      throw new TaskException("DirectoryScanner.includes or .excludes missing" + nsfe.getMessage());
    } catch (IllegalAccessException iae) {
      throw new TaskException("Access to DirectoryScanner.includes or .excludes not allowed");
    }
    getUtil()
        .importFiles(
            importProject,
            ds.getBasedir(),
            includes,
            excludes,
            importClasses,
            importResources,
            importSources,
            useDefaultExcludes);
  }
}
