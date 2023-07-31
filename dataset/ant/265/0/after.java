class PlaceHold {
  private void collectProjectHelpers() {
    Constructor<? extends ProjectHelper> projectHelper = getProjectHelperBySystemProperty();
    registerProjectHelper(projectHelper);
    try {
      ClassLoader classLoader = LoaderUtils.getContextClassLoader();
      if (classLoader != null) {
        Enumeration<URL> resources = classLoader.getResources(SERVICE_ID);
        while (resources.hasMoreElements()) {
          URL resource = resources.nextElement();
          URLConnection conn = resource.openConnection();
          conn.setDefaultUseCaches(false);
          projectHelper = getProjectHelperByService(conn.getInputStream());
          registerProjectHelper(projectHelper);
        }
      }
      InputStream systemResource = ClassLoader.getSystemResourceAsStream(SERVICE_ID);
      if (systemResource != null) {
        projectHelper = getProjectHelperByService(systemResource);
        registerProjectHelper(projectHelper);
      }
    } catch (Exception e) {
      System.err.println(
          ((((("Unable to load ProjectHelper from service " + ProjectHelper.SERVICE_ID) + " (")
                          + e.getClass().getName())
                      + ": ")
                  + e.getMessage())
              + ")");
      if (DEBUG) {
        e.printStackTrace(System.err);
      }
    }
  }
}
