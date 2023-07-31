class PlaceHold {
  public void scanProject(Project project) {
    try {
      Package[] packages = project.getPackages();
      if (packages != null) {
        for (int i = 0; i < packages.length; i++) {
          Package item = packages[i];
          String name =
              (project.getName() + File.separator)
                  + item.getName().replace('.', File.separatorChar);
          if (isIncluded(name) && (!isExcluded(name))) {
            packagesIncluded.addElement(item);
          }
        }
      }
    } catch (IvjException e) {
      throw VAJLocalUtil.createBuildException("VA Exception occured: ", e);
    }
  }
}
