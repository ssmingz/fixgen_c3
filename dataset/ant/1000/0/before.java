class PlaceHold {
  private Vector findMatchingProjects(String pattern) {
    String[] projectNames;
    try {
      projectNames = getWorkspace().getRepository().getProjectNames();
    } catch (IvjException e) {
      throw createBuildException("VA Exception occured: ", e);
    }
    Vector matchingProjects = new Vector();
    for (int i = 0; i < projectNames.length; i++) {
      if (VAJWorkspaceScanner.match(pattern, projectNames[i])) {
        matchingProjects.addElement(projectNames[i]);
      }
    }
    return matchingProjects;
  }
}
