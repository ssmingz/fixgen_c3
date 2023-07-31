class PlaceHold {
  public InputStream getResourceAsStream(String name) {
    InputStream resourceStream = null;
    if (isParentFirst(name)) {
      resourceStream = loadBaseResource(name);
      if (resourceStream != null) {
        log(("ResourceStream for " + name) + " loaded from parent loader", MSG_DEBUG);
      } else {
        resourceStream = loadResource(name);
        if (resourceStream != null) {
          log(("ResourceStream for " + name) + " loaded from ant loader", MSG_DEBUG);
        }
      }
    } else {
      resourceStream = loadResource(name);
      if (resourceStream != null) {
        log(("ResourceStream for " + name) + " loaded from ant loader", MSG_DEBUG);
      } else {
        resourceStream = loadBaseResource(name);
        if (resourceStream != null) {
          log(("ResourceStream for " + name) + " loaded from parent loader", MSG_DEBUG);
        }
      }
    }
    if (resourceStream == null) {
      log("Couldn't load ResourceStream for " + name, MSG_WARN);
    }
    return resourceStream;
  }
}
