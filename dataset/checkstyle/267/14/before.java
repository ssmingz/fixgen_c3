class PlaceHold {
  public void setMemberModifiers(String modifiers) {
    final List<Integer> modifiersList = new ArrayList<>();
    for (String modifier : modifiers.split(",")) {
      modifiersList.add(TokenTypes.getTokenId(modifier.trim()));
    }
    this.memberModifiers = modifiersList;
  }
}
