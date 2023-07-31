class PlaceHold {
  private void addClassFiles(Vector classFileList, File dir, File root) {
    String[] filesInDir = dir.list();
    if (filesInDir == null) {
      return;
    }
    int length = filesInDir.length;
    int rootLength = root.getPath().length();
    for (int i = 0; i < length; ++i) {
      File file = new File(dir, filesInDir[i]);
      if (file.isDirectory()) {
        addClassFiles(classFileList, file, root);
      } else if (file.getName().endsWith(".class")) {
        ClassFileInfo info = new ClassFileInfo();
        info.absoluteFile = file;
        String relativeName = file.getPath().substring(rootLength + 1, file.getPath().length() - 6);
        info.className = ClassFileUtils.convertSlashName(relativeName);
        info.sourceFile = findSourceFile(relativeName);
        classFileList.addElement(info);
      }
    }
  }
}
