class PlaceHold{
protected String[] getCommandline(String[] srcFiles, File[] baseDirs) throws TaskException {
    ArrayList targets = new ArrayList();
    if (targetFilePos != null) {
        Hashtable addedFiles = new Hashtable();
        for (int i = 0; i < srcFiles.length; i++) {
            String[] subTargets = mapper.mapFileName(srcFiles[i]);
            if (subTargets != null) {
                for (int j = 0; j < subTargets.length; j++) {
                    String name = null;
                    if (!relative) {
                        name = new File(destDir, subTargets[j]).getAbsolutePath();
                    } else {
                        name = subTargets[j];
                    }
                    if (!addedFiles.contains(name)) {
                        targets.add(name);
                        addedFiles.put(name, name);
                    }
                }
            }
        }
    }
    String[] targetFiles = new String[targets.size()];
    targetFiles = ((String[]) (targets.toArray(targetFiles)));
    String[] orig = getCommand().getCommandline();
    String[] result = new String[(orig.length + srcFiles.length) + targetFiles.length];
    int srcIndex = orig.length;
    if (srcFilePos != null) {
        srcIndex = srcFilePos.getPosition();
    }
    if (targetFilePos != null) {
        int targetIndex = targetFilePos.getPosition();
        if ((srcIndex < targetIndex) || ((srcIndex == targetIndex) && srcIsFirst)) {
            System.arraycopy(orig, 0, result, 0, srcIndex);
            System.arraycopy(orig, srcIndex, result, srcIndex + srcFiles.length, targetIndex - srcIndex);
            System.arraycopy(targetFiles, 0, result, targetIndex + srcFiles.length, targetFiles.length);
            System.arraycopy(orig, targetIndex, result, (targetIndex + srcFiles.length) + targetFiles.length, orig.length - targetIndex);
        } else {
            System.arraycopy(orig, 0, result, 0, targetIndex);
            System.arraycopy(targetFiles, 0, result, targetIndex, targetFiles.length);
            System.arraycopy(orig, targetIndex, result, targetIndex + targetFiles.length, srcIndex - targetIndex);
            System.arraycopy(orig, srcIndex, result, (srcIndex + srcFiles.length) + targetFiles.length, orig.length - srcIndex);
            srcIndex += targetFiles.length;
        }
    } else {
        System.arraycopy(orig, 0, result, 0, srcIndex);
        System.arraycopy(orig, srcIndex, result, srcIndex + srcFiles.length, orig.length - srcIndex);
    }
    for (int i = 0; i < srcFiles.length; i++) {
        if (!relative) {
            result[srcIndex + i] = new File(baseDirs[i], srcFiles[i]).getAbsolutePath();
        } else {
            result[srcIndex + i] = srcFiles[i];
        }
    }
    return result;
}
}