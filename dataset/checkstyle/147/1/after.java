class PlaceHold {
  private void removeVariable(DetailAST aAST) {
    for (int i = mScopeStack.size() - 1; i >= 0; i--) {
      final Map<String, DetailAST> state = mScopeStack.peek(i);
      final Object obj = state.remove(aAST.getText());
      if (obj != null) {
        break;
      }
    }
  }
}
