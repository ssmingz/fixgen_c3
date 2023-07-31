class PlaceHold {
  private Iterable<JDKToolsLibrary> findLibraries() {
    Map<JavaVersion, JDKToolsLibrary> results = new TreeMap<JavaVersion, JDKToolsLibrary>();
    File configTools = DrJava.getConfig().getSetting(JAVAC_LOCATION);
    if (configTools != FileOption.NULL_FILE) {
      JDKToolsLibrary fromConfig = JarJDKToolsLibrary.makeFromFile(configTools, this);
      if (fromConfig.isValid()) {
        results.put(fromConfig.version().majorVersion(), fromConfig);
      }
    }
    JDKToolsLibrary fromRuntime = JDKToolsLibrary.makeFromRuntime(this);
    JavaVersion runtimeVersion = fromRuntime.version().majorVersion();
    if (fromRuntime.isValid() && (!results.containsKey(runtimeVersion))) {
      results.put(runtimeVersion, fromRuntime);
    }
    Iterable<JarJDKToolsLibrary> fromSearch = JarJDKToolsLibrary.search(this);
    for (JDKToolsLibrary t : fromSearch) {
      JavaVersion tVersion = t.version().majorVersion();
      if (!results.containsKey(tVersion)) {
        results.put(tVersion, t);
      }
    }
    return IterUtil.reverse(results.values());
  }
}
