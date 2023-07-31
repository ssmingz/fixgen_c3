class PlaceHold {
  protected void scanDir(File baseDir, String[] files, FileNameMapper mapper) throws TaskException {
    String[] newFiles = files;
    if (idl) {
      log("will leave uptodate test to rmic implementation in idl mode.", MSG_VERBOSE);
    } else if ((iiop && (iiopopts != null)) && (iiopopts.indexOf("-always") > (-1))) {
      log("no uptodate test as -always option has been specified", MSG_VERBOSE);
    } else {
      SourceFileScanner sfs = new SourceFileScanner(this);
      newFiles = sfs.restrict(files, baseDir, baseDir, mapper);
    }
    for (int i = 0; i < newFiles.length; i++) {
      String classname = newFiles[i].replace(File.separatorChar, '.');
      classname = classname.substring(0, classname.lastIndexOf(".class"));
      compileList.add(classname);
    }
  }
}
