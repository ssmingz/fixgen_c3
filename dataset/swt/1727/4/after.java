class PlaceHold {
  public static void error(int code, Throwable throwable, String detail) {
    if (code != SWT.ERROR_FAILED_EXEC) {
      if (throwable instanceof SWTError) {
        throw ((SWTError) (throwable));
      }
      if (throwable instanceof SWTException) {
        throw ((SWTException) (throwable));
      }
    }
    String message = findErrorText(code);
    if (detail != null) {
      message += detail;
    }
    switch (code) {
      case ERROR_NULL_ARGUMENT:
      case ERROR_CANNOT_BE_ZERO:
      case ERROR_INVALID_ARGUMENT:
      case ERROR_MENU_NOT_BAR:
      case ERROR_MENU_NOT_DROP_DOWN:
      case ERROR_MENU_NOT_POP_UP:
      case ERROR_MENUITEM_NOT_CASCADE:
      case ERROR_INVALID_PARENT:
      case ERROR_INVALID_RANGE:
        {
          throw new IllegalArgumentException(message);
        }
      case ERROR_INVALID_SUBCLASS:
      case ERROR_THREAD_INVALID_ACCESS:
      case ERROR_WIDGET_DISPOSED:
      case ERROR_GRAPHIC_DISPOSED:
      case ERROR_DEVICE_DISPOSED:
      case ERROR_FUNCTION_DISPOSED:
      case ERROR_INVALID_IMAGE:
      case ERROR_UNSUPPORTED_DEPTH:
      case ERROR_UNSUPPORTED_FORMAT:
      case ERROR_FAILED_EXEC:
      case ERROR_FAILED_EVALUATE:
      case ERROR_CANNOT_INVERT_MATRIX:
      case ERROR_NO_GRAPHICS_LIBRARY:
      case ERROR_INVALID_RETURN_VALUE:
      case ERROR_IO:
        {
          SWTException exception = new SWTException(code, message);
          exception.throwable = throwable;
          throw exception;
        }
      case ERROR_CANNOT_GET_COUNT:
      case ERROR_CANNOT_GET_ENABLED:
      case ERROR_CANNOT_GET_ITEM:
      case ERROR_CANNOT_GET_ITEM_HEIGHT:
      case ERROR_CANNOT_GET_SELECTION:
      case ERROR_CANNOT_GET_TEXT:
      case ERROR_CANNOT_SET_ENABLED:
      case ERROR_CANNOT_SET_MENU:
      case ERROR_CANNOT_SET_SELECTION:
      case ERROR_CANNOT_SET_TEXT:
      case ERROR_ITEM_NOT_ADDED:
      case ERROR_ITEM_NOT_REMOVED:
      case ERROR_NO_HANDLES:
      case ERROR_FAILED_LOAD_LIBRARY:
      case ERROR_NO_MORE_CALLBACKS:
      case ERROR_NOT_IMPLEMENTED:
      case ERROR_UNSPECIFIED:
        {
          SWTError error = new SWTError(code, message);
          error.throwable = throwable;
          throw error;
        }
    }
    SWTError error = new SWTError(code, message);
    error.throwable = throwable;
    throw error;
  }
}
