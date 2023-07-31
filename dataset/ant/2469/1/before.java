class PlaceHold {
  private void collectArgumentProcessors() {
    try {
      ClassLoader classLoader = LoaderUtils.getContextClassLoader();
      if (classLoader != null) {
        Enumeration<URL> resources = classLoader.getResources(SERVICE_ID);
        while (resources.hasMoreElements()) {
          URL resource = resources.nextElement();
          ArgumentProcessor processor = getProcessorByService(resource.openStream());
          registerArgumentProcessor(processor);
        }
      }
      InputStream systemResource = ClassLoader.getSystemResourceAsStream(SERVICE_ID);
      if (systemResource != null) {
        ArgumentProcessor processor = getProcessorByService(systemResource);
        registerArgumentProcessor(processor);
      }
    } catch (Exception e) {
      System.err.println(
          ((((("Unable to load ArgumentProcessor from service " + SERVICE_ID) + " (")
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
