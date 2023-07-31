class PlaceHold {
  public void execute() throws BuildException {
    ClassLoader al = createLoader();
    if (!definerSet) {
      if (getURI() == null) {
        throw new BuildException(
            ("name, file or resource attribute of " + getTaskName()) + " is undefined",
            getLocation());
      }
      if (getURI().startsWith(ANTLIB_PREFIX)) {
        String uri1 = getURI();
        setResource(makeResourceFromURI(uri1));
      } else {
        throw new BuildException(
            ("Only antlib URIs can be located from the URI alone," + "not the URI ") + getURI());
      }
    }
    if (name != null) {
      if (classname == null) {
        throw new BuildException(
            (("classname attribute of " + getTaskName()) + " element ") + "is undefined",
            getLocation());
      }
      addDefinition(al, name, classname);
    } else {
      if (classname != null) {
        String msg = "You must not specify classname " + "together with file or resource.";
        throw new BuildException(msg, getLocation());
      }
      Enumeration urls = null;
      if (file != null) {
        final URL url = fileToURL();
        if (url == null) {
          return;
        }
        urls =
            new Enumeration() {
              private boolean more = true;

              public boolean hasMoreElements() {
                return more;
              }

              public Object nextElement() throws NoSuchElementException {
                if (more) {
                  more = false;
                  return url;
                } else {
                  throw new NoSuchElementException();
                }
              }
            };
      } else {
        urls = resourceToURLs(al);
      }
      while (urls.hasMoreElements()) {
        URL url = ((URL) (urls.nextElement()));
        int fmt = this.format;
        if (url.toString().toLowerCase(Locale.ENGLISH).endsWith(".xml")) {
          fmt = Format.XML;
        }
        if (fmt == Format.PROPERTIES) {
          loadProperties(al, url);
          break;
        } else if (resourceStack.getStack().get(url) != null) {
          log(
              ((((("Warning: Recursive loading of " + url) + " ignored") + " at ") + getLocation())
                      + " originally loaded at ")
                  + resourceStack.getStack().get(url),
              MSG_WARN);
        } else {
          try {
            resourceStack.getStack().put(url, getLocation());
            loadAntlib(al, url);
          } finally {
            resourceStack.getStack().remove(url);
          }
        }
      }
    }
  }
}
