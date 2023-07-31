class PlaceHold {
  public static NSArray cookiesWithResponseHeaderFields(NSDictionary headerFields, NSURL URL) {
    long result =
        OS.objc_msgSend(
            class_NSHTTPCookie,
            sel_cookiesWithResponseHeaderFields_forURL_,
            headerFields != null ? headerFields.id : 0,
            URL != null ? URL.id : 0);
    return result != 0 ? new NSArray(result) : null;
  }
}
