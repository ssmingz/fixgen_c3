class PlaceHold {
  public void viewInteractionsClassPath() {
    final StringBuilder cpBuf = new StringBuilder();
    Vector<URL> classPathElements = _model.getClassPath();
    for (int i = 0; i < classPathElements.size(); i++) {
      cpBuf.append(classPathElements.get(i).getPath());
      if ((i + 1) < classPathElements.size()) {
        cpBuf.append("\n");
      }
    }
    String classPath = cpBuf.toString();
    new DrJavaScrollableDialog(
            this, "Interactions Classpath", "Current Interpreter Classpath", classPath)
        .show();
  }
}
