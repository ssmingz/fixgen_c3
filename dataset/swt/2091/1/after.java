class PlaceHold {
  static long getType(String widgetTypeName, Accessible accessible, long parentType, int childId) {
    AccessibleControlEvent event = new AccessibleControlEvent(accessible);
    event.childID = childId;
    List<AccessibleControlListener> listeners = accessible.accessibleControlListeners;
    for (int i = 0, length = (listeners == null) ? 0 : listeners.size(); i < length; i++) {
      AccessibleControlListener listener = listeners.get(i);
      listener.getRole(event);
    }
    boolean action = false;
    boolean editableText = false;
    boolean hypertext = false;
    boolean selection = false;
    boolean table = false;
    boolean text = false;
    boolean value = false;
    if (event.detail != 0) {
      for (int i = 0; i < AccessibleFactory.actionRoles.length; i++) {
        if (event.detail == actionRoles[i]) {
          action = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.editableTextRoles.length; i++) {
        if (event.detail == editableTextRoles[i]) {
          editableText = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.hypertextRoles.length; i++) {
        if (event.detail == hypertextRoles[i]) {
          hypertext = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.selectionRoles.length; i++) {
        if (event.detail == selectionRoles[i]) {
          selection = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.tableRoles.length; i++) {
        if (event.detail == tableRoles[i]) {
          table = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.textRoles.length; i++) {
        if (event.detail == textRoles[i]) {
          text = true;
          break;
        }
      }
      for (int i = 0; i < AccessibleFactory.valueRoles.length; i++) {
        if (event.detail == valueRoles[i]) {
          value = true;
          break;
        }
      }
    } else {
      action = editableText = hypertext = selection = table = text = value = true;
    }
    String swtTypeName = SWT_TYPE_PREFIX + widgetTypeName;
    if (action) {
      swtTypeName += "Action";
    }
    if (editableText) {
      swtTypeName += "EditableText";
    }
    if (hypertext) {
      swtTypeName += "Hypertext";
    }
    if (selection) {
      swtTypeName += "Selection";
    }
    if (table) {
      swtTypeName += "Table";
    }
    if (text) {
      swtTypeName += "Text";
    }
    if (value) {
      swtTypeName += "Value";
    }
    byte[] nameBytes = Converter.wcsToMbcs(null, swtTypeName, true);
    long type = OS.g_type_from_name(nameBytes);
    if (type == 0) {
      if (AccessibleObject.DEBUG) {
        AccessibleObject.print("-->New Type=" + swtTypeName);
      }
      long queryPtr = OS.g_malloc(sizeof);
      OS.g_type_query(parentType, queryPtr);
      GTypeQuery query = new GTypeQuery();
      OS.memmove(query, queryPtr, sizeof);
      OS.g_free(queryPtr);
      GTypeInfo typeInfo = new GTypeInfo();
      typeInfo.base_init = GTypeInfo_base_init_type.getAddress();
      typeInfo.class_size = ((short) (query.class_size));
      typeInfo.instance_size = ((short) (query.instance_size));
      long definition = OS.g_malloc(sizeof);
      OS.memmove(definition, typeInfo, sizeof);
      type = OS.g_type_register_static(parentType, nameBytes, definition, 0);
      OS.g_type_add_interface_static(type, ATK.ATK_TYPE_COMPONENT(), ComponentIfaceDefinition);
      if (action) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_ACTION(), ActionIfaceDefinition);
      }
      if (editableText) {
        OS.g_type_add_interface_static(
            type, ATK.ATK_TYPE_EDITABLE_TEXT(), EditableTextIfaceDefinition);
      }
      if (hypertext) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_HYPERTEXT(), HypertextIfaceDefinition);
      }
      if (selection) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_SELECTION(), SelectionIfaceDefinition);
      }
      if (table) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_TABLE(), TableIfaceDefinition);
      }
      if (text) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_TEXT(), TextIfaceDefinition);
      }
      if (value) {
        OS.g_type_add_interface_static(type, ATK.ATK_TYPE_VALUE(), ValueIfaceDefinition);
      }
    }
    return type;
  }
}
