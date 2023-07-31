class Configuration {
  private Configuration() {
    OptionMapLoader loader = OptionMapLoader.DEFAULT;
    try {
      if (PROPERTIES_FILE.exists()) {
        InputStream fis = new FileInputStream(PROPERTIES_FILE);
        loader = OptionMapLoader.makeLoader(fis);
      } else {
        new FileOutputStream(PROPERTIES_FILE).close();
      }
    } catch (IOException e) {
    }
    OptionMap map = new DefaultOptionMap();
    loader.loadInto(map);
    _config = new Configuration(map);
  }
}
