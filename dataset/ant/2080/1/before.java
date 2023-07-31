class PlaceHold {
  public boolean supportsNestedElement(String elementName) {
    return ((nestedCreators.containsKey(elementName.toLowerCase(Locale.US))
                || DynamicConfigurator.class.isAssignableFrom(bean))
            || DynamicConfiguratorNS.class.isAssignableFrom(bean))
        || (addTypeMethods.size() != 0);
  }
}
