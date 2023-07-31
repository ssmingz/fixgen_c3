class PlaceHold {
  static Number getGValue(int value) {
    int type = OS.G_VALUE_TYPE(value);
    if (type == 0) {
      return null;
    }
    if (type == OS.G_TYPE_DOUBLE()) {
      return new Double(OS.g_value_get_double(value));
    }
    if (type == OS.G_TYPE_FLOAT()) {
      return new Float(OS.g_value_get_float(value));
    }
    if (type == OS.G_TYPE_INT64()) {
      return new Long(OS.g_value_get_int64(value));
    }
    return new Integer(OS.g_value_get_int(value));
  }
}
