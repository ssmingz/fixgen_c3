class PlaceHold {
  public boolean eventPosted(EventObject event) {
    ProjectProxy project = getAppContext().getProject();
    if (project == null) {
      _tree.setModel(null);
      _tree.setSelectionModel(null);
    } else {
      _tree.setModel(project.getTreeModel());
      _tree.setSelectionModel(project.getTreeSelectionModel());
    }
    return true;
  }
}
