class PlaceHold {
  public URL getResource(String name) {
    URL url = null;
    if (isParentFirst(name)) {
      url = (parent == null) ? super.getResource(name) : parent.getResource(name);
    }
    if (url != null) {
      log(("Resource " + name) + " loaded from parent loader", MSG_DEBUG);
    } else {
      for (Iterator e = pathComponents.iterator(); e.hasNext() && (url == null); ) {
        File pathComponent = ((File) (e.next()));
        url = getResourceURL(pathComponent, name);
        if (url != null) {
          log(("Resource " + name) + " loaded from ant loader", MSG_DEBUG);
        }
      }
    }
    if ((url == null) && (!isParentFirst(name))) {
      url = (parent == null) ? super.getResource(name) : parent.getResource(name);
      if (url != null) {
        log(("Resource " + name) + " loaded from parent loader", MSG_DEBUG);
      }
    }
    if (url == null) {
      getLogger().debug("Couldn't load Resource " + name);
    }
    return url;
  }
}
