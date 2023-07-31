class PlaceHold {
  protected void addClasspathEntry(String resource) {
    if (resource.startsWith("/")) {
      resource = resource.substring(1);
    } else {
      resource = "org/apache/tools/ant/taskdefs/optional/" + resource;
    }
    File f = LoaderUtils.getResourceSource(getClass().getClassLoader(), resource);
    if (f != null) {
      log("Found " + f.getAbsolutePath(), MSG_DEBUG);
      createClasspath().setLocation(f);
    } else {
      log("Couldn\'t find " + resource, MSG_VERBOSE);
    }
  }
}
