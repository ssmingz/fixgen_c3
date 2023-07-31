class PlaceHold {
  public void setOutputdirectory(File outputDirectory) {
    getLogger().debug("Setting output directory to: " + outputDirectory.toString());
    this.outputDirectory = outputDirectory;
  }
}
