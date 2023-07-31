class PlaceHold {
  public void forClassDefDoFirst(ClassDef that) {
    String name = that.getName().getText();
    Iterator<String> iter = _importedFiles.iterator();
    while (iter.hasNext()) {
      String s = iter.next();
      if (s.endsWith(name) && (!s.equals(getQualifiedClassName(name)))) {
        _addAndIgnoreError(("The class " + name) + " was already imported.", that);
      }
    }
    String[] mavStrings = that.getMav().getModifiers();
    if (!(that instanceof InnerClassDef)) {
      for (int i = 0; i < mavStrings.length; i++) {
        if (mavStrings[i].equals("private")) {
          _addAndIgnoreError("Top level classes cannot be private", that);
        }
      }
    }
    forTypeDefBaseDoFirst(that);
  }
}
