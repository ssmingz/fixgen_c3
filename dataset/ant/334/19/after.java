class PlaceHold {
  private void addFilesToImport(
      ImportCodeSpec spec,
      boolean doImport,
      Vector files,
      String fileType,
      StringBuffer summaryLog) {
    if (doImport) {
      String[] fileArr = new String[files.size()];
      files.copyInto(fileArr);
      try {
        String methodName = ("set" + fileType) + "Files";
        Class[] methodParams = new Class[] {fileArr.getClass()};
        Method method = spec.getClass().getDeclaredMethod(methodName, methodParams);
        method.invoke(spec, new Object[] {fileArr});
      } catch (Exception e) {
        throw new BuildException("Error", e);
      }
      if (files.size() > 0) {
        logFiles(files, fileType);
        summaryLog.append(files.size());
        summaryLog.append((" " + fileType.toLowerCase()) + " file");
        summaryLog.append(files.size() > 1 ? "s, " : ", ");
      }
    }
  }
}
