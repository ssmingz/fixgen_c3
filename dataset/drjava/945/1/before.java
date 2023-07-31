class PlaceHold {
  public void forFormalParameter(FormalParameter that) {
    _readAndWriteThroughIndex(
        that.getSourceInfo().getStartLine(), that.getSourceInfo().getStartColumn() - 1);
    if (_isElementaryFile() || _isIntermediateFile()) {
      _writeToFileOut("final ");
    }
  }
}
