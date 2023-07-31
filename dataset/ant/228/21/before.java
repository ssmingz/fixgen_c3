class PlaceHold {
  protected void scanDir(String[] files) {
    long now = new Date().getTime();
    File jspFile = null;
    String parents = null;
    String pack = "";
    for (int i = 0; i < files.length; i++) {
      File srcFile = new File(this.sourceDirectory, files[i]);
      jspFile = new File(files[i]);
      parents = jspFile.getParent();
      int loc = 0;
      if ((parents != null) && (!"".equals(parents))) {
        parents = this.replaceString(parents, File.separator, "_/");
        pack = ((pathToPackage + File.separator) + "_") + parents;
      } else {
        pack = pathToPackage;
      }
      String filePath = (pack + File.separator) + "_";
      int startingIndex =
          (files[i].lastIndexOf(File.separator) != (-1))
              ? files[i].lastIndexOf(File.separator) + 1
              : 0;
      int endingIndex = files[i].indexOf(".jsp");
      if (endingIndex == (-1)) {
        break;
      }
      filePath += files[i].substring(startingIndex, endingIndex);
      filePath += ".class";
      File classFile = new File(this.destinationDirectory, filePath);
      if (srcFile.lastModified() > now) {
        log("Warning: file modified in the future: " + files[i], MSG_WARN);
      }
      if (srcFile.lastModified() > classFile.lastModified()) {
        filesToDo.addElement(files[i]);
        log("Recompiling File " + files[i], MSG_VERBOSE);
      }
    }
  }
}
