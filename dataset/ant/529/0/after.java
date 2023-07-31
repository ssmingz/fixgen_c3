class PlaceHold {
  public void setJunit(String junit) {
    commandline.createClasspath(project).createPathElement().setLocation(junit);
  }
}
