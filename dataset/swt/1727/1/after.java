class PlaceHold {
  static String findErrorText(int code) {
    switch (code) {
      case ERROR_UNSPECIFIED:
        return "Unspecified error";
      case ERROR_NO_HANDLES:
        return "No more handles";
      case ERROR_NO_MORE_CALLBACKS:
        return "No more callbacks";
      case ERROR_NULL_ARGUMENT:
        return "Argument cannot be null";
      case ERROR_INVALID_ARGUMENT:
        return "Argument not valid";
      case ERROR_INVALID_RETURN_VALUE:
        return "Return value not valid";
      case ERROR_INVALID_RANGE:
        return "Index out of bounds";
      case ERROR_CANNOT_BE_ZERO:
        return "Argument cannot be zero";
      case ERROR_CANNOT_GET_ITEM:
        return "Cannot get item";
      case ERROR_CANNOT_GET_SELECTION:
        return "Cannot get selection";
      case ERROR_CANNOT_GET_ITEM_HEIGHT:
        return "Cannot get item height";
      case ERROR_CANNOT_GET_TEXT:
        return "Cannot get text";
      case ERROR_CANNOT_SET_TEXT:
        return "Cannot set text";
      case ERROR_ITEM_NOT_ADDED:
        return "Item not added";
      case ERROR_ITEM_NOT_REMOVED:
        return "Item not removed";
      case ERROR_NOT_IMPLEMENTED:
        return "Not implemented";
      case ERROR_MENU_NOT_DROP_DOWN:
        return "Menu must be a drop down";
      case ERROR_THREAD_INVALID_ACCESS:
        return "Invalid thread access";
      case ERROR_WIDGET_DISPOSED:
        return "Widget is disposed";
      case ERROR_MENUITEM_NOT_CASCADE:
        return "Menu item is not a CASCADE";
      case ERROR_CANNOT_SET_SELECTION:
        return "Cannot set selection";
      case ERROR_CANNOT_SET_MENU:
        return "Cannot set menu";
      case ERROR_CANNOT_SET_ENABLED:
        return "Cannot set the enabled state";
      case ERROR_CANNOT_GET_ENABLED:
        return "Cannot get the enabled state";
      case ERROR_INVALID_PARENT:
        return "Widget has the wrong parent";
      case ERROR_MENU_NOT_BAR:
        return "Menu is not a BAR";
      case ERROR_CANNOT_GET_COUNT:
        return "Cannot get count";
      case ERROR_MENU_NOT_POP_UP:
        return "Menu is not a POP_UP";
      case ERROR_UNSUPPORTED_DEPTH:
        return "Unsupported color depth";
      case ERROR_IO:
        return "i/o error";
      case ERROR_INVALID_IMAGE:
        return "Invalid image";
      case ERROR_UNSUPPORTED_FORMAT:
        return "Unsupported or unrecognized format";
      case ERROR_INVALID_SUBCLASS:
        return "Subclassing not allowed";
      case ERROR_GRAPHIC_DISPOSED:
        return "Graphic is disposed";
      case ERROR_DEVICE_DISPOSED:
        return "Device is disposed";
      case ERROR_FUNCTION_DISPOSED:
        return "BrowserFunction is disposed";
      case ERROR_FAILED_EXEC:
        return "Failed to execute runnable";
      case ERROR_FAILED_EVALUATE:
        return "Failed to evaluate javascript expression";
      case ERROR_FAILED_LOAD_LIBRARY:
        return "Unable to load library";
      case ERROR_CANNOT_INVERT_MATRIX:
        return "Cannot invert matrix";
      case ERROR_NO_GRAPHICS_LIBRARY:
        return "Unable to load graphics library";
      case ERROR_INVALID_FONT:
        return "Font not valid";
    }
    return "Unknown error";
  }
}
