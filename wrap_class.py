import os
import sys


def is_constructor(method):
    result = None
    method_sig = method.split('(')[0].strip().split(' ')
    group_size = len(method_sig)
    if 'private' in method_sig or 'public' in method_sig or 'protected' in method_sig:
        group_size -= 1
    if group_size < 2:
        result = method_sig[-1]
    return result


def list_dir_mac(path):
    files = [name for name in os.listdir(path) if name != ".DS_Store"]
    return files


root="/Users/yumeng/JavaProjects/FixGen/codegraph4spoon/out/patch"
pros=["drjava", "ant", "swt"]

for pro in pros:
    for group in list_dir_mac(f"{root}/{pro}"):
        for targetNo in list_dir_mac(f"{root}/{pro}/{group}"):
            for patchNo in list_dir_mac(f"{root}/{pro}/{group}/{targetNo}"):
                patchPath = f"{root}/{pro}/{group}/{targetNo}/{patchNo}"
                content = ""
                with open(patchPath, "r") as f:
                    content = f.read()

                if not content.startswith("class "):
                    clazz_name = is_constructor(content)
                    if clazz_name is None:
                        clazz_name = 'PlaceHold'

                    with open(patchPath, "w", encoding="utf8") as fb:
                        fb.write(f"class {clazz_name}" + "{\n" + content + "\n}")
                        fb.close()
                print(f"[finish] {patchPath}")
