class PlaceHold {
  public id internal_accessibilityAttributeValue_forParameter(
      NSString attribute, id parameter, int childID) {
    if (attribute.isEqualToString(NSAccessibilityStringForRangeParameterizedAttribute)) {
      return getStringForRangeParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityRangeForLineParameterizedAttribute)) {
      return getRangeForLineParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityLineForIndexParameterizedAttribute)) {
      return getLineForIndexParameterizedAttribute(parameter, childID);
    }
    if (attribute.isEqualToString(NSAccessibilityBoundsForRangeParameterizedAttribute)) {
      return getBoundsForRangeParameterizedAttribute(parameter, childID);
    }
    if ((OS.VERSION >= 0x1060)
        && attribute.isEqualToString(NSAccessibilityCellForColumnAndRowParameterizedAttribute)) {
      return getCellForColumnAndRowParameter(parameter, childID);
    }
    return null;
  }
}
