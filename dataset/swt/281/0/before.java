class PlaceHold {
  public static boolean test() {
    int fail = 0;
    String url;
    String pluginPath = System.getProperty("PLUGIN_PATH");
    if (verbose) {
      System.out.println(("PLUGIN_PATH <" + pluginPath) + ">");
    }
    if (pluginPath == null) {
      url = Browser5.class.getClassLoader().getResource("browser5.html").toString();
    } else {
      url = pluginPath + "/data/browser5.html";
    }
    String[] urls = new String[] {url};
    for (int i = 0; i < urls.length; i++) {
      boolean result = test1(urls[i]);
      if (verbose) {
        System.out.print(result ? "." : "E");
      }
      if (!result) {
        fail++;
      }
    }
    return fail == 0;
  }
}
