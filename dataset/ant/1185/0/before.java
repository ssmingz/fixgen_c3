class PlaceHold {
  protected Path getBootClassPath() {
    Path bp = new Path(project);
    if (bootclasspath != null) {
      bp.append(bootclasspath);
    }
    return bp.concatSystemBootClasspath("ignore");
  }
}
