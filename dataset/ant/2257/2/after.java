class PlaceHold {
  private void moveGeneratedFile(
      File baseDir, File sourceBaseFile, String classname, RmicAdapter adapter)
      throws TaskException {
    String classFileName = classname.replace('.', File.separatorChar) + ".class";
    String[] generatedFiles = adapter.getMapper().mapFileName(classFileName, getContext());
    for (int i = 0; i < generatedFiles.length; i++) {
      String sourceFileName = classFileName.substring(0, classFileName.length() - 6) + ".java";
      File oldFile = new File(baseDir, sourceFileName);
      File newFile = new File(sourceBaseFile, sourceFileName);
      try {
        FileUtil.copyFile(oldFile, newFile);
        oldFile.delete();
      } catch (IOException ioe) {
        String msg =
            (((("Failed to copy " + oldFile) + " to ") + newFile) + " due to ") + ioe.getMessage();
        throw new TaskException(msg, ioe);
      }
    }
  }
}
