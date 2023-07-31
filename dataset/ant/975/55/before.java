class PlaceHold {
  protected void runServer(Server s) {
    starbase[] projects = s.getProjects();
    for (int i = 0; i < projects.length; i++) {
      Project p = projects[i];
      if (p.getName().equals(getProjectName())) {
        if (getVerbose()) {
          getLogger().info(("Found " + getProjectName()) + delim);
        }
        runProject(s, p);
        break;
      }
    }
  }
}
