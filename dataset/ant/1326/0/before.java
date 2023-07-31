class PlaceHold {
  public void scan() {
    if (includes == null) {
      includes = new String[1];
      includes[0] = "**";
    }
    if (excludes == null) {
      excludes = new String[0];
    }
    Vector matchingProjects = findMatchingProjects();
    for (Enumeration e = matchingProjects.elements(); e.hasMoreElements(); ) {
      Project project = ((Project) (e.nextElement()));
      scanProject(project);
    }
  }
}
