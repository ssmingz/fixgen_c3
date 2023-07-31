class PlaceHold {
  private void checkTag(
      int lineNo, List<JavadocTag> tags, String tagName, Pattern formatPattern, String format) {
    if (formatPattern == null) {
      return;
    }
    int tagCount = 0;
    for (int i = tags.size() - 1; i >= 0; i--) {
      final JavadocTag tag = tags.get(i);
      if (tag.getTagName().equals(tagName)) {
        tagCount++;
        if (!formatPattern.matcher(tag.getArg1()).find()) {
          log(lineNo, "type.tagFormat", "@" + tagName, format);
        }
      }
    }
    if (tagCount == 0) {
      log(lineNo, "type.missingTag", "@" + tagName);
    }
  }
}
