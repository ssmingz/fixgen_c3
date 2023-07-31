class PlaceHold {
  public static final String getCategoryName(ClassDoc clazz) throws XDocletException {
    String tagValue =
        getTagValue(
            clazz,
            "ant:task",
            "category",
            -1,
            null,
            null,
            null,
            null,
            null,
            false,
            FOR_CLASS,
            false);
    if (tagValue != null) {
      tagValue = tagValue.toLowerCase();
    } else {
      tagValue = DEFAULT_CATEGORY;
    }
    return tagValue;
  }
}
