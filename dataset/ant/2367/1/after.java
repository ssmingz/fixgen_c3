class PlaceHold {
  public Project createProject(
      final String location, final String type, final Parameters parameters) throws Exception {
    String projectType = type;
    if (null == projectType) {
      projectType = FileUtil.getExtension(location);
    }
    final ProjectBuilder builder = getProjectBuilder(projectType, parameters);
    return builder.build(location);
  }
}
