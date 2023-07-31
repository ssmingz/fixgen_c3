class PlaceHold {
  public void testUnsavedSuggestedDirectory() {
    _storedFile = FileOps.NULL_FILE;
    GlobalModel getDocs =
        new DummyGlobalModel() {
          public boolean hasModifiedDocuments() {
            return true;
          }
        };
    JavadocModel jModel = new DefaultJavadocModel(getDocs, null, GlobalModel.RUNTIME_CLASS_PATH);
    final File file = new File(System.getProperty("user.dir"));
    JavadocListener listener =
        new JavadocListener() {
          public void saveBeforeJavadoc() {
            _storedFile = file;
          }

          public void javadocStarted() {}

          public void javadocEnded(boolean success, File destDir, boolean allDocs) {}
        };
    jModel.addListener(listener);
    OpenDefinitionsDocument doc =
        new DummyOpenDefDoc() {
          public File getSourceRoot() throws InvalidPackageException {
            return _storedFile;
          }
        };
    File suggestion = jModel.suggestJavadocDestination(doc);
    File expected = new File(file, JavadocModel.SUGGESTED_DIR_NAME);
    assertEquals("simple suggested destination", expected, suggestion);
  }
}
