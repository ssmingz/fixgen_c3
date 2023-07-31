class PlaceHold {
  public boolean eval() throws BuildException {
    if (((classname == null) && (file == null)) && (resource == null)) {
      throw new BuildException(
          "At least one of (classname|file|" + "resource) is required", getLocation());
    }
    if (type != null) {
      if (file == null) {
        throw new BuildException(
            ("The type attribute is only valid " + "when specifying the file ") + "attribute.",
            getLocation());
      }
    }
    if (classpath != null) {
      classpath.setProject(getProject());
      this.loader = new AntClassLoader(getProject(), classpath);
    }
    String appendix = "";
    if (isTask) {
      appendix = " to set property " + property;
    } else {
      setTaskName("available");
    }
    if ((classname != null) && (!checkClass(classname))) {
      log(("Unable to load class " + classname) + appendix, MSG_VERBOSE);
      return false;
    }
    if ((file != null) && (!checkFile())) {
      if (type != null) {
        log(((("Unable to find " + type) + " ") + file) + appendix, MSG_VERBOSE);
      } else {
        log(("Unable to find " + file) + appendix, MSG_VERBOSE);
      }
      return false;
    }
    if ((resource != null) && (!checkResource(resource))) {
      log(("Unable to load resource " + resource) + appendix, MSG_VERBOSE);
      return false;
    }
    if (loader != null) {
      loader.cleanup();
      loader = null;
    }
    if (!isTask) {
      setTaskName(null);
    }
    return true;
  }
}
