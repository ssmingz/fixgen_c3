class PlaceHold {
  protected File getFile(String name) throws FileNotFoundException {
    URL url = getClass().getResource(name);
    if (url == null) {
      throw new FileNotFoundException(("Unable to load '" + name) + "' from classpath");
    }
    return new File(FileUtils.newFileUtils().fromURI(url.toExternalForm()));
  }
}
