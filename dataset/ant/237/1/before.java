class PlaceHold {
  private void doTest(
      String msg,
      boolean keepRoot,
      boolean collapse,
      boolean semantic,
      boolean include,
      boolean localRoot) {
    Enumeration iter = getFiles(new File("src/etc/testcases/taskdefs/xmlproperty/inputs"));
    while (iter.hasMoreElements()) {
      File inputFile = ((File) (iter.nextElement()));
      File workingDir;
      if (localRoot) {
        workingDir = fileUtils.getParentFile(inputFile);
      } else {
        workingDir = fileUtils.resolveFile(new File("."), ".");
      }
      try {
        File propertyFile =
            getGoldfile(inputFile, keepRoot, collapse, semantic, include, localRoot);
        if (!propertyFile.exists()) {
          continue;
        }
        Project project = new Project();
        XmlProperty xmlproperty = new XmlProperty();
        xmlproperty.setProject(project);
        xmlproperty.setFile(inputFile);
        xmlproperty.setKeeproot(keepRoot);
        xmlproperty.setCollapseAttributes(collapse);
        xmlproperty.setSemanticAttributes(semantic);
        xmlproperty.setIncludeSemanticAttribute(include);
        xmlproperty.setRootDirectory(workingDir);
        project.setNewProperty("override.property.test", "foo");
        xmlproperty.execute();
        Properties props = new Properties();
        props.load(new FileInputStream(propertyFile));
        ensureProperties(msg, inputFile, workingDir, project, props);
        ensureReferences(msg, inputFile, project.getReferences());
      } catch (IOException ex) {
        fail(ex.toString());
      }
    }
  }
}
