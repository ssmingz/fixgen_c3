class PlaceHold {
  private Vector getExpandedDescriptions(Vector projectDescs) {
    Vector expandedDescs = new Vector(projectDescs.size());
    try {
      String[] projectNames = getWorkspace().getRepository().getProjectNames();
      for (int i = 0; i < projectNames.length; i++) {
        for (Enumeration e = projectDescs.elements(); e.hasMoreElements(); ) {
          VAJProjectDescription d = ((VAJProjectDescription) (e.nextElement()));
          String pattern = d.getName();
          if (VAJWorkspaceScanner.match(pattern, projectNames[i])) {
            d.setProjectFound();
            expandedDescs.addElement(new VAJProjectDescription(projectNames[i], d.getVersion()));
            break;
          }
        }
      }
    } catch (IvjException e) {
      throw createTaskException("VA Exception occured: ", e);
    }
    return expandedDescs;
  }
}
