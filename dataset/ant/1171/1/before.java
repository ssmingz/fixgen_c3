class PlaceHold {
  protected View openView() throws BuildException {
    logStarteamVersion();
    View view = null;
    try {
      view = StarTeamFinder.openView(getViewURL());
    } catch (Exception e) {
      throw new BuildException("Failed to connect to " + getURL(), e);
    }
    if (null == view) {
      throw new BuildException(("Cannot find view" + getURL()) + " in repository()");
    }
    View snapshot = createSnapshotView(view);
    this.server = snapshot.getServer();
    return snapshot;
  }
}
