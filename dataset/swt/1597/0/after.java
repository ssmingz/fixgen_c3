class PlaceHold {
  public void test_getBounds() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getBounds(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_TreeItem)");
      }
      return;
    }
    Image image = images[0];
    Rectangle bounds;
    Rectangle bounds2;
    String string = "hello";
    bounds = treeItem.getBounds();
    assertTrue(":1a:", (bounds.x > 0) && (bounds.height > 0));
    treeItem.setText(string);
    GC gc = new GC(tree);
    Point extent = gc.stringExtent(string);
    gc.dispose();
    bounds = treeItem.getBounds();
    assertTrue(":1b:", ((bounds.x > 0) && (bounds.height > extent.y)) && (bounds.width > extent.x));
    makeCleanEnvironment();
    Rectangle rect = image.getBounds();
    treeItem.setImage(image);
    bounds = treeItem.getBounds();
    assertTrue(":1c:", (bounds.x > 0) && (bounds.height >= rect.height));
    bounds2 = treeItem.getImageBounds(0);
    assertTrue(":1d:", bounds.x >= (bounds2.x + bounds2.width));
    makeCleanEnvironment();
    TreeItem subItem = new TreeItem(treeItem, SWT.NONE);
    bounds = subItem.getBounds();
    assertTrue(":1e:", bounds.equals(new Rectangle(0, 0, 0, 0)));
    treeItem.setExpanded(true);
    bounds = subItem.getBounds();
    bounds2 = treeItem.getBounds();
    assertTrue(
        ":1f:",
        ((bounds.x > bounds2.x) && (bounds.y >= (bounds2.y + bounds2.height)))
            && (bounds.height > 0));
    treeItem.setExpanded(false);
    bounds = subItem.getBounds();
    assertTrue(":1g:", bounds.equals(new Rectangle(0, 0, 0, 0)));
    treeItem.setExpanded(true);
    subItem.setText(string);
    bounds = subItem.getBounds();
    bounds2 = treeItem.getBounds();
    assertTrue(
        ":1h:",
        (((bounds.x > bounds2.x) && (bounds.y >= (bounds2.y + bounds2.height)))
                && (bounds.height > extent.y))
            && (bounds.width > extent.x));
    makeCleanEnvironment();
    subItem = new TreeItem(treeItem, SWT.NONE);
    treeItem.setExpanded(true);
    subItem.setImage(image);
    bounds = subItem.getBounds();
    assertTrue(":1i:", (bounds.x > 0) && (bounds.height >= rect.height));
    bounds2 = subItem.getImageBounds(0);
    assertTrue(":1j:", bounds.x >= (bounds2.x + bounds2.width));
  }
}
