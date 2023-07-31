class PlaceHold {
  void copyArea(Image image, int x, int y, int srcImage) {
    if (srcImage == 0) {
      return;
    }
    NSBitmapImageRep rep = image.imageRep;
    int bpc = rep.bitsPerSample();
    int width = rep.pixelsWide();
    int height = rep.pixelsHigh();
    int bpr = rep.bytesPerRow();
    int alphaInfo = (rep.hasAlpha()) ? OS.kCGImageAlphaFirst : OS.kCGImageAlphaNoneSkipFirst;
    int colorspace = OS.CGColorSpaceCreateDeviceRGB();
    int context =
        OS.CGBitmapContextCreate(rep.bitmapData(), width, height, bpc, bpr, colorspace, alphaInfo);
    OS.CGColorSpaceRelease(colorspace);
    if (context != 0) {
      CGRect rect = new CGRect();
      rect.origin.x = -x;
      rect.origin.y = y;
      rect.size.width = OS.CGImageGetWidth(srcImage);
      rect.size.height = OS.CGImageGetHeight(srcImage);
      OS.CGContextTranslateCTM(context, 0, -(rect.size.height - height));
      OS.CGContextDrawImage(context, rect, srcImage);
      OS.CGContextRelease(context);
    }
  }
}
