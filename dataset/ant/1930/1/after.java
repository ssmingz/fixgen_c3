class PlaceHold {
  protected void addBuildListeners(Project project) {
    project.addBuildListener(createLogger());
    for (int i = 0; i < listeners.size(); i++) {
      String className = ((String) (listeners.elementAt(i)));
      try {
        BuildListener listener = ((BuildListener) (Class.forName(className).newInstance()));
        Project.setProjectOnObject(project, listener);
        project.addBuildListener(listener);
      } catch (Throwable exc) {
        throw new BuildException("Unable to instantiate listener " + className, exc);
      }
    }
  }
}
