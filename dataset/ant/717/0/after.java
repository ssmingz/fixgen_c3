class PlaceHold {
  public final void addProject(final String name, final Project project) {
    if (null != m_projects.get(name)) {
      final String message = REZ.getString("duplicate-project.error", name);
      throw new IllegalArgumentException(message);
    } else {
      m_projects.put(name, project);
    }
  }
}
