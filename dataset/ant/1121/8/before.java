class PlaceHold {
  private void moveGeneratedFile(
      File baseDir, File sourceBaseFile, String classname, RmicAdapter adapter)
      throws BuildException {
    String classFileName = classname.replace('.', File.separatorChar) + ".class";
    String[] generatedFiles = adapter.getMapper().mapFileName(classFileName);
    for (int i = 0; i < generatedFiles.length; i++) {
      if (!generatedFiles[i].endsWith(".class")) {
        continue;
      }
      String sourceFileName = generatedFiles[i].substring(0, classFileName.length() - 6) + ".java";
      File oldFile = new File(baseDir, sourceFileName);
      if (!oldFile.exists()) {
        continue;
      }
      File newFile = new File(sourceBaseFile, sourceFileName);
      try {
        if (filtering) {
          fileUtils.copyFile(
              oldFile, newFile, new FilterSetCollection(getProject().getGlobalFilterSet()));
        } else {
          fileUtils.copyFile(oldFile, newFile);
        }
        oldFile.delete();
      } catch (IOException ioe) {
        String msg =
            (((("Failed to copy " + oldFile) + " to ") + newFile) + " due to ") + ioe.getMessage();
        throw new BuildException(msg, ioe, location);
      }
    }
  }
}
