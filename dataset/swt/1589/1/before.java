class PlaceHold {
  public static boolean test() {
    int fail = 0;
    String pluginPath = System.getProperty("PLUGIN_PATH");
    if (verbose) {
      System.out.println(("PLUGIN_PATH <" + pluginPath) + ">");
    }
    String url;
    if (pluginPath == null) {
      url = Browser7.class.getClassLoader().getResource("browser7.html").toString();
    } else {
      url = pluginPath + "/data/browser7.html";
    }
    String[] urls = new String[] {url};
    for (int i = 0; i < urls.length; i++) {
      boolean result = test(urls[i]);
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
