class PlaceHold {
  protected Frame createFrame(Project project) throws ExecutionException {
    Frame newFrame = new Frame(standardLibs, initConfig, config);
    newFrame.setProject(project);
    for (Iterator j = eventSupport.getListeners(); j.hasNext(); ) {
      BuildListener listener = ((BuildListener) (j.next()));
      newFrame.addBuildListener(listener);
    }
    return newFrame;
  }
}
