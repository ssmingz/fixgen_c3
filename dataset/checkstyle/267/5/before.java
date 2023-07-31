class PlaceHold {
  private void registerCheck(int tokenID, Check check) {
    registerCheck(TokenTypes.getTokenName(tokenID), check);
  }
}
