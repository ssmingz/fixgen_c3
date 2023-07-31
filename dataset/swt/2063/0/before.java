class PlaceHold {
  int[] createImage(int type) {
    int[] ref = new int[1];
    int result = OS.GetIconRef(kOnSystemDisk, kSystemIconsCreator, type, ref);
    if (result != OS.noErr) {
      return null;
    }
    int[] family = new int[1];
    result = OS.IconRefToIconFamily(ref[0], kSelectorAlLAvailableData, family);
    OS.ReleaseIconRef(ref[0]);
    if (result != OS.noErr) {
      return null;
    }
    int dataHandle = OS.NewHandle(0);
    result = OS.GetIconFamilyData(family[0], kLarge32BitData, dataHandle);
    if (result != OS.noErr) {
      OS.DisposeHandle(dataHandle);
      OS.DisposeHandle(family[0]);
      return null;
    }
    int maskHandle = OS.NewHandle(0);
    result = OS.GetIconFamilyData(family[0], kLarge8BitMask, maskHandle);
    if (result != OS.noErr) {
      OS.DisposeHandle(maskHandle);
      OS.DisposeHandle(dataHandle);
      OS.DisposeHandle(family[0]);
      return null;
    }
    int width = 32;
    int height = 32;
    int bpr = width * 4;
    int dataSize = OS.GetHandleSize(dataHandle);
    int data = OS.NewPtrClear(dataSize);
    if (data == 0) {
      OS.DisposeHandle(maskHandle);
      OS.DisposeHandle(dataHandle);
      OS.DisposeHandle(family[0]);
      return null;
    }
    OS.HLock(dataHandle);
    OS.HLock(maskHandle);
    int[] iconPtr = new int[1];
    int[] maskPtr = new int[1];
    OS.memcpy(iconPtr, dataHandle, 4);
    OS.memcpy(maskPtr, maskHandle, 4);
    OS.memcpy(data, iconPtr[0], dataSize);
    int pixelCount = dataSize / 4;
    for (int i = 0; i < pixelCount; i++) {
      OS.memcpy(data + (i * 4), maskPtr[0] + i, 1);
    }
    OS.HUnlock(maskHandle);
    OS.HUnlock(dataHandle);
    OS.DisposeHandle(maskHandle);
    OS.DisposeHandle(dataHandle);
    OS.DisposeHandle(family[0]);
    int provider = OS.CGDataProviderCreateWithData(0, data, dataSize, 0);
    if (provider == 0) {
      OS.DisposePtr(data);
      return null;
    }
    int colorspace = OS.CGColorSpaceCreateDeviceRGB();
    if (colorspace == 0) {
      OS.CGDataProviderRelease(provider);
      OS.DisposePtr(data);
      return null;
    }
    int cgImage =
        OS.CGImageCreate(
            width, height, 8, 32, bpr, colorspace, kCGImageAlphaFirst, provider, null, false, 0);
    OS.CGColorSpaceRelease(colorspace);
    OS.CGDataProviderRelease(provider);
    return new int[] {cgImage, data};
  }
}
