class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    Rectangle result = new Rectangle(0, 0, 0, 0);
    if (nsItem.respondsToSelector(sel_accessibilityAttributeValue_)) {
      int posValue =
          OS.objc_msgSend(
              nsItem.id, sel_accessibilityAttributeValue_, OS.NSAccessibilityPositionAttribute());
      int sizeValue =
          OS.objc_msgSend(
              nsItem.id, sel_accessibilityAttributeValue_, OS.NSAccessibilitySizeAttribute());
      NSValue val = new NSValue(posValue);
      NSPoint pt = val.pointValue();
      NSWindow window = parent.view.window();
      pt.y = display.getPrimaryFrame().height - pt.y;
      pt = parent.view.convertPoint_fromView_(pt, null);
      pt = window.convertScreenToBase(pt);
      result.x = ((int) (pt.x));
      result.y = ((int) (pt.y));
      val = new NSValue(sizeValue);
      NSSize size = val.sizeValue();
      result.width = ((int) (Math.ceil(size.width)));
      result.height = ((int) (Math.ceil(size.height)));
    }
    return result;
  }
}
