class PlaceHold {
  protected Path getBootClassPath() {
    final Path bp = new Path(project);
    if (bootclasspath != null) {
      bp.append(bootclasspath);
    }
    return bp.concatSystemBootClasspath("ignore");
  }
}
