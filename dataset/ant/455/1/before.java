class PlaceHold {
  protected void scanDir(File srcDir, String[] files) {
    long now = System.currentTimeMillis();
    for (int i = 0; i < files.length; i++) {
      File srcFile = new File(srcDir, files[i]);
      if (files[i].endsWith(".java")) {
        String filePath = srcFile.getPath();
        String className =
            filePath.substring(srcDir.getPath().length() + 1, filePath.length() - ".java".length());
        className = ClassFileUtils.convertSlashName(className);
        ClassFileInfo info = ((ClassFileInfo) (classFileInfoMap.get(className)));
        if (info == null) {
          outOfDateClasses.put(className, className);
        } else if (srcFile.lastModified() > (info.absoluteFile.lastModified() + TIME_TOLERANCE)) {
          outOfDateClasses.put(className, className);
        }
      }
    }
  }
}
