class PlaceHold {
  public void removeBuildListener(BuildListener listener) {
    for (Iterator i = getImportedFrames(); i.hasNext(); ) {
      ExecutionFrame subFrame = ((ExecutionFrame) (i.next()));
      subFrame.removeBuildListener(listener);
    }
    eventSupport.removeBuildListener(listener);
  }
}
