class PlaceHold {
  public void testSuccessful() {
    File[] testFiles =
        directory.listFiles(
            new FileFilter() {
              public boolean accept(File pathName) {
                return pathName.getAbsolutePath().endsWith(".dj2");
              }
            });
    LanguageLevelConverter llc = new LanguageLevelConverter(JavaVersion.JAVA_5);
    Pair<LinkedList<JExprParseException>, LinkedList<Pair<String, JExpressionIF>>> result;
    result = llc.convert(testFiles);
    assertEquals(
        "should be no parse exceptions", new LinkedList<JExprParseException>(), result.getFirst());
    assertEquals(
        "should be no visitor exceptions",
        new LinkedList<Pair<String, JExpressionIF>>(),
        result.getSecond());
    for (int i = 0; i < testFiles.length; i++) {
      File currFile = testFiles[i];
      String fileName = currFile.getAbsolutePath();
      fileName = fileName.substring(0, fileName.length() - 4);
      File resultingFile = new File(fileName + ".java");
      File correctFile = new File(fileName + ".expected");
      if (correctFile.exists()) {
        try {
          assertEquals(
              ("File " + currFile.getName()) + " should have been parsed and augmented correctly.",
              readFileAsString(correctFile),
              readFileAsString(resultingFile));
        } catch (IOException ioe) {
          fail(ioe.getMessage());
        }
      }
    }
    File newDirectory =
        new File(
            (directory.getAbsolutePath() + System.getProperty("file.separator")) + "importedFiles");
    testFiles =
        newDirectory.listFiles(
            new FileFilter() {
              public boolean accept(File pathName) {
                String name = pathName.getAbsolutePath();
                return name.endsWith("IsItPackageAndImport.dj1")
                    || name.endsWith("ToReference.dj1");
              }
            });
    for (int i = 0; i < testFiles.length; i++) {
      File currFile = testFiles[i];
      String fileName = currFile.getAbsolutePath();
      fileName = fileName.substring(0, fileName.length() - 4);
      File resultingFile = new File(fileName + ".java");
      File correctFile = new File(fileName + ".expected");
      if (correctFile.exists()) {
        try {
          assertEquals(
              ("File " + currFile.getName()) + " should have been parsed and augmented correctly.",
              readFileAsString(correctFile),
              readFileAsString(resultingFile));
        } catch (IOException ioe) {
          fail(ioe.getMessage());
        }
      }
    }
    File f = new File(newDirectory, "ToReference2.java");
    assertFalse("ToReference2.java should not exist", f.exists());
    newDirectory =
        new File(
            (directory.getAbsolutePath() + System.getProperty("file.separator"))
                + "importedFiles2");
    testFiles =
        newDirectory.listFiles(
            new FileFilter() {
              public boolean accept(File pathName) {
                return pathName.getAbsolutePath().endsWith("AlsoReferenced.dj1");
              }
            });
    for (int i = 0; i < testFiles.length; i++) {
      File currFile = testFiles[i];
      String fileName = currFile.getAbsolutePath();
      fileName = fileName.substring(0, fileName.length() - 4);
      File resultingFile = new File(fileName + ".java");
      File correctFile = new File(fileName + ".expected");
      if (correctFile.exists()) {
        try {
          assertEquals(
              ("File " + currFile.getName()) + " should have been parsed and augmented correctly.",
              readFileAsString(correctFile),
              readFileAsString(resultingFile));
        } catch (IOException ioe) {
          fail(ioe.getMessage());
        }
      }
    }
    f = new File(newDirectory, "ToReference.java");
    assertFalse("ToReference.java should not exist", f.exists());
  }
}
