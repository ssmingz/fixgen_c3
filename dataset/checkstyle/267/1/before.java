class PlaceHold {
  public void setTarget(String target) {
    final List<Integer> customTarget = new ArrayList<>();
    for (String type : target.split(", ")) {
      customTarget.add(TokenTypes.getTokenId(type));
    }
    this.target = customTarget;
  }
}
