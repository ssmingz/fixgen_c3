class PlaceHold {
  public boolean supportsNestedElement(String elementName) {
    return (nestedCreators.containsKey(elementName.toLowerCase(Locale.US)) || isDynamic())
        || (addTypeMethods.size() != 0);
  }
}
