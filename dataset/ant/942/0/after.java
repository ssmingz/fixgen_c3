class PlaceHold {
  public static ComponentHelper getComponentHelper(Project project) {
    if (project == null) {
      return null;
    }
    ComponentHelper ph = ((ComponentHelper) (project.getReference(COMPONENT_HELPER_REFERENCE)));
    if (ph != null) {
      return ph;
    }
    ph = new ComponentHelper();
    ph.setProject(project);
    project.addReference(COMPONENT_HELPER_REFERENCE, ph);
    return ph;
  }
}
