class PlaceHold {
  public final void addProject(final String name, final Project project) {
    if (null != m_projects.get(name)) {
      throw new IllegalArgumentException(
          ("Can not have two projects referenced in a " + "file with the name ") + name);
    } else {
      m_projects.put(name, project);
    }
  }
}
