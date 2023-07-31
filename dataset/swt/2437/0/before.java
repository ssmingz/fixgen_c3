class PlaceHold {
  static long atkText_get_run_attributes(
      long atkObject, long offset, long start_offset, long end_offset) {
    if (DEBUG) {
      print("-->atkText_get_run_attributes");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleAttributeListeners;
      int length = size(listeners);
      if (length > 0) {
        AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(accessible);
        event.offset = ((int) (offset));
        for (int i = 0; i < length; i++) {
          AccessibleAttributeListener listener =
              ((AccessibleAttributeListener) (listeners.elementAt(i)));
          listener.getTextAttributes(event);
        }
        OS.memmove(start_offset, new int[] {event.start}, 4);
        OS.memmove(end_offset, new int[] {event.end}, 4);
        TextStyle style = event.textStyle;
        long result = 0;
        AtkAttribute attr = new AtkAttribute();
        if (style != null) {
          if (style.rise != 0) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_RISE));
            attr.value = getStringPtr(String.valueOf(style.rise));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
          if (style.underline) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_UNDERLINE));
            String str = "none";
            switch (style.underlineStyle) {
              case SWT.UNDERLINE_DOUBLE:
                str = "double";
                break;
              case SWT.UNDERLINE_SINGLE:
                str = "single";
                break;
              case SWT.UNDERLINE_ERROR:
                str = "error";
                break;
              case SWT.UNDERLINE_SQUIGGLE:
                str = "squiggle";
                break;
            }
            attr.value = getStringPtr(str);
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
          if (style.strikeout) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_STRIKETHROUGH));
            attr.value = getStringPtr("1");
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
          Font font = style.font;
          if ((font != null) && (!font.isDisposed())) {
            long attrPtr;
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_FAMILY_NAME));
            attr.value = ATK.g_strdup(OS.pango_font_description_get_family(font.handle));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_SIZE));
            attr.value =
                getStringPtr(
                    String.valueOf(
                        OS.pango_font_description_get_size(font.handle) / OS.PANGO_SCALE));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_STYLE));
            attr.value =
                ATK.g_strdup(
                    ATK.atk_text_attribute_get_value(
                        ATK_TEXT_ATTR_STYLE, OS.pango_font_description_get_style(font.handle)));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_VARIANT));
            attr.value =
                ATK.g_strdup(
                    ATK.atk_text_attribute_get_value(
                        ATK_TEXT_ATTR_VARIANT, OS.pango_font_description_get_variant(font.handle)));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_STRETCH));
            attr.value =
                ATK.g_strdup(
                    ATK.atk_text_attribute_get_value(
                        ATK_TEXT_ATTR_STRETCH, OS.pango_font_description_get_stretch(font.handle)));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
            attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_WEIGHT));
            attr.value =
                getStringPtr(String.valueOf(OS.pango_font_description_get_weight(font.handle)));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
          Color color = style.foreground;
          if ((color != null) && (!color.isDisposed())) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_FG_COLOR));
            attr.value =
                getStringPtr(
                    ((((color.handle.red & 0xffff) + ",") + (color.handle.blue & 0xffff)) + ",")
                        + (color.handle.blue & 0xffff));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
          color = style.background;
          if ((color != null) && (!color.isDisposed())) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = ATK.g_strdup(ATK.atk_text_attribute_get_name(ATK_TEXT_ATTR_BG_COLOR));
            attr.value =
                getStringPtr(
                    ((((color.handle.red & 0xffff) + ",") + (color.handle.blue & 0xffff)) + ",")
                        + (color.handle.blue & 0xffff));
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
        }
        if (event.attributes != null) {
          int end = (event.attributes.length / 2) * 2;
          for (int i = 0; i < end; i += 2) {
            long attrPtr = OS.g_malloc(sizeof);
            attr.name = getStringPtr(event.attributes[i]);
            attr.value = getStringPtr(event.attributes[i + 1]);
            ATK.memmove(attrPtr, attr, sizeof);
            result = OS.g_slist_append(result, attrPtr);
          }
        }
        return result;
      }
    }
    long parentResult = 0;
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_run_attributes != 0)) {
      parentResult =
          ATK.call(iface.get_run_attributes, atkObject, offset, start_offset, end_offset);
    }
    return parentResult;
  }
}
