class PlaceHold {
  private File libraryFile(String homeSubDir, String lib) {
    String home = project.getProperty("ant.home");
    if (home == null) {
      throw new BuildException("ANT_HOME not set as required.");
    }
    return new File(new File(home, homeSubDir), lib);
  }
}
