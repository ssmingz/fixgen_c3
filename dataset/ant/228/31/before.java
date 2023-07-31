class PlaceHold {
  public void addPathElement(String pathElement) throws TaskException {
    File pathComponent =
        (project != null)
            ? FileUtil.resolveFile(project.getBaseDir(), pathElement)
            : new File(pathElement);
    pathComponents.addElement(pathComponent);
  }
}
