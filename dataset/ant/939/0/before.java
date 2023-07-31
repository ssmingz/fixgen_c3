class PlaceHold {
  private void determineBuildFile() throws ConfigException {
    if (buildFileURL == null) {
      File defaultBuildFile = new File(FrontendUtils.DEFAULT_BUILD_FILENAME);
      if (!defaultBuildFile.exists()) {
        File ant1BuildFile = new File(FrontendUtils.DEFAULT_ANT1_FILENAME);
        if (ant1BuildFile.exists()) {
          defaultBuildFile = ant1BuildFile;
        }
      }
      try {
        buildFileURL = InitUtils.getFileURL(defaultBuildFile);
      } catch (MalformedURLException e) {
        throw new ConfigException("Build file is not valid", e);
      }
    }
  }
}
