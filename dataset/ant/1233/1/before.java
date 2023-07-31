class PlaceHold {
  private String macroSubs(String s, Map macroMapping) {
    if (template.getAttributeStyle() == AttributeStyle.ANT) {
      return macroSubsAnt(s, macroMapping);
    } else {
      return macroSubsXPath(s, macroMapping);
    }
  }
}
