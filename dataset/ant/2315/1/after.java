class PlaceHold {
  public void scan() {
    if (includes == null) {
      includes = new String[1];
      includes[0] = "**";
    }
    if (excludes == null) {
      excludes = new String[0];
    }
    ArrayList matchingProjects = findMatchingProjects();
    for (Iterator e = matchingProjects.iterator(); e.hasNext(); ) {
      Project project = ((Project) (e.next()));
      scanProject(project);
    }
  }
}
