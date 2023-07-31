class PlaceHold {
  public Project createSubProject() {
    Project subProject = null;
    try {
      subProject = ((Project) (getClass().newInstance()));
    } catch (Exception e) {
      subProject = new Project();
    }
    initSubProject(subProject);
    return subProject;
  }
}
