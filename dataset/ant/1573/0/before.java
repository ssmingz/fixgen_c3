class PlaceHold {
  public ExecutionManager(InitConfig initConfig, AntConfig config) throws ExecutionException {
    this.config = config;
    this.initConfig = initConfig;
    Map librarySpecs = new HashMap(10);
    try {
      URL standardLibsURL = new URL(initConfig.getLibraryURL(), "antlibs/");
      AntLibManager libManager = new AntLibManager(config.isRemoteLibAllowed());
      libManager.addAntLibraries(librarySpecs, standardLibsURL);
      libManager.configLibraries(initConfig, librarySpecs, antLibraries);
      librarySpecs.clear();
      for (Iterator i = config.getLibraryLocations(); i.hasNext(); ) {
        String libLocation = ((String) (i.next()));
        libManager.loadLib(librarySpecs, libLocation);
      }
      libManager.configLibraries(initConfig, librarySpecs, antLibraries);
      addConfigLibPaths();
      mainFrame = new ExecutionFrame(antLibraries, initConfig, config);
    } catch (MalformedURLException e) {
      throw new ExecutionException("Unable to load Ant libraries", e);
    }
  }
}
