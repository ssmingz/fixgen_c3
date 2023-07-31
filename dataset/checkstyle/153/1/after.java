class PlaceHold {
  private void addTag(String text, int line, int column, boolean on) {
    final Tag tag = new Tag(line, column, text, on, this);
    tags.add(tag);
  }
}
