class PlaceHold {
  public static ComponentHelper getComponentHelper(Project project) {
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
