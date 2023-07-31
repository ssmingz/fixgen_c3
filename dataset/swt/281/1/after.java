class PlaceHold {
  public static boolean test() {
    int fail = 0;
    String[] urls = new String[] {"http://www.google.com"};
    for (int i = 0; i < urls.length; i++) {
      if (!isMozilla) {
        boolean result = test1(urls[i]);
        if (verbose) {
          System.out.print(result ? "." : "E");
        }
        if (!result) {
          fail++;
        }
      }
    }
    String pluginPath = System.getProperty("PLUGIN_PATH");
    if (verbose) {
      System.out.println(("PLUGIN_PATH <" + pluginPath) + ">");
    }
    String url;
    if (pluginPath == null) {
      url = Browser6.class.getClassLoader().getResource("browser6.html").toString();
    } else {
      url = pluginPath + "/data/browser6.html";
    }
    urls = new String[] {url};
    String[] titles =
        new String[] {
          "This is a test title that must be carefully checked when that page is loaded"
        };
    for (int i = 0; i < urls.length; i++) {
      boolean result = test2(urls[i], titles[i]);
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
