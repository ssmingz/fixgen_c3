class PlaceHold {
  private boolean isCheckedMethod(DetailAST aAST) {
    final String methodName = aAST.findFirstToken(IDENT).getText();
    return !mIgnoredMethodNames.contains(methodName);
  }
}
