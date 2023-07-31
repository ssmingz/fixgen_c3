class PlaceHold {
  public void forPackageStatementOnly(PackageStatement that) {
    CompoundWord cWord = that.getCWord();
    Word[] words = cWord.getWords();
    String newPackage;
    String separator = System.getProperty("file.separator");
    if (words.length > 0) {
      _package = words[0].getText();
      newPackage = _package;
      for (int i = 1; i < words.length; i++) {
        String temp = words[i].getText();
        newPackage = (newPackage + separator) + temp;
        _package = (_package + ".") + temp;
      }
      String directory = _file.getParent();
      if ((directory == null) || (!directory.endsWith(newPackage))) {
        _addAndIgnoreError("The package name must mirror your file's directory.", that);
      }
    }
    getSymbolData(_package, that.getSourceInfo(), false, false, false);
    forJExpressionOnly(that);
  }
}
