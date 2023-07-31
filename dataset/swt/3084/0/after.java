class PlaceHold {
  int get_attributes(long pbstrAttributes) {
    AccessibleAttributeEvent event = new AccessibleAttributeEvent(this);
    for (int i = 0; i < accessibleAttributeListenersSize(); i++) {
      AccessibleAttributeListener listener = accessibleAttributeListeners.get(i);
      listener.getAttributes(event);
    }
    String attributes = "";
    attributes += ("margin-left:" + event.leftMargin) + ";";
    attributes += ("margin-top:" + event.topMargin) + ";";
    attributes += ("margin-right:" + event.rightMargin) + ";";
    attributes += ("margin-bottom:" + event.bottomMargin) + ";";
    if (event.tabStops != null) {
      for (int i = 0; i < event.tabStops.length; i++) {
        attributes += ("tab-stop:position=" + event.tabStops[i]) + ";";
      }
    }
    if (event.justify) {
      attributes += "text-align:justify;";
    }
    attributes +=
        ("text-align:"
                + (event.alignment == SWT.LEFT
                    ? "left"
                    : event.alignment == SWT.RIGHT ? "right" : "center"))
            + ";";
    attributes += ("text-indent:" + event.indent) + ";";
    if (event.attributes != null) {
      for (int i = 0; (i + 1) < event.attributes.length; i += 2) {
        attributes += ((event.attributes[i] + ":") + event.attributes[i + 1]) + ";";
      }
    }
    if (getRole() == ACC.ROLE_TEXT) {
      attributes += "text-model:a1;";
    }
    if (DEBUG) {
      print(
          ((this + ".IAccessible2::get_attributes() returning ") + attributes)
              + hresult(attributes.length() == 0 ? COM.S_FALSE : COM.S_OK));
    }
    setString(pbstrAttributes, attributes);
    if (attributes.length() == 0) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
