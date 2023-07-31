class PlaceHold {
  public Project createSubProject() {
    Project subProject = null;
    try {
      subProject = getClass().newInstance();
    } catch (final Exception e) {
      subProject = new Project();
    }
    initSubProject(subProject);
    return subProject;
  }
}
